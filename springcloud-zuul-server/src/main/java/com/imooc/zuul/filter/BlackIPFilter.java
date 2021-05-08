package com.imooc.zuul.filter;

import com.imooc.utils.IPUtil;
import com.imooc.utils.RedisOperator;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RefreshScope
public class BlackIPFilter extends ZuulFilter {

    @Autowired
    private RedisOperator redis;

    @Value("${blackIp.limitTimes}")
    private Integer limitTimes;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("IP Filter");

        System.out.println("limitTimes: " + limitTimes);

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        String ip = IPUtil.getRequestIp(request);

        final String ipRedis = "zuul-ip:" + ip;
        final String ipRedisLimit = "zuul-ip-limit:" + ip;

        Long limitLeftTime = redis.ttl(ipRedisLimit);
        if (limitLeftTime > 0) {
            stopRequest(context);
            return null;
        }

        long requestCounts = redis.increment(ipRedis, 1);
        if (requestCounts == 1) {
            redis.expire(ipRedis, limitTimes);
        }

        if (requestCounts > 5) {
            redis.set(ipRedisLimit, ipRedisLimit, limitTimes);
            stopRequest(context);
        }

        return null;
    }

    private void stopRequest(RequestContext context) {
        context.setSendZuulResponse(false);
        context.setResponseStatusCode(200);
        context.setResponseBody("limit");

    }
}

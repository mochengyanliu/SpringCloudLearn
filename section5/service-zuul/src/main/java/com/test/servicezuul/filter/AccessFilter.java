package com.test.servicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AccessFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    // 返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型
    @Override
    public String filterType() {
//        1.pre:可以在请求被路由之前调用
//        2.routing:在请求时被调用
//        3.post:在routing和error过滤之后调用。
//        4.error：处理请求时发生错误是被调用。
        return "pre";
    }

    // 过滤的顺序
    @Override
    public int filterOrder() {
        return 0;
    }

    // 这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // 过滤器的具体逻辑。可以很复杂，包括查sql，nosql去判断该请求到底有没有权限访问
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(String.format("请求方式：%s -----请求url： %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            logger.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){

            }
            return null;
        }
        logger.info("ok");
        return null;
    }
}

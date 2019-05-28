package com.tca.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class TokenFilter extends ZuulFilter{
	
	private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
	
	@Override
	public boolean shouldFilter() {
//		return true;
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		String token = (String)request.getParameter("token");
		logger.info("请求token为:{}, {}", token, request.getRequestURI());
		if ("tca".equals(token)) {
			logger.info("token验证通过");
			requestContext.setSendZuulResponse(true); //对请求进行路由
			requestContext.setResponseStatusCode(200);
			requestContext.set("isSuccess", true);
            return null;
		}
		logger.info("token验证失败");
		requestContext.setSendZuulResponse(false); //不对其进行路由
		requestContext.setResponseStatusCode(400);
		requestContext.set("isSuccess", false);
		return "token失效";
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}

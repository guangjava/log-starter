package com.guang.springboot.log.aop;

import java.util.Arrays;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogMethodInterceptor implements MethodInterceptor{
	private Logger logger = LoggerFactory.getLogger(LogMethodInterceptor.class);
	private List<String> exclude;
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String methodName = invocation.getMethod().getName();
		if (exclude.contains(methodName)) {
			return invocation.proceed();
		}
		long start = System.currentTimeMillis();
		Object result = invocation.proceed();
		long end = System.currentTimeMillis();
		logger.info("====method({}),cost({})",methodName,end-start);
		return result;
	}
	public LogMethodInterceptor(String[] exclude){
		this.exclude = Arrays.asList(exclude);
	}
}

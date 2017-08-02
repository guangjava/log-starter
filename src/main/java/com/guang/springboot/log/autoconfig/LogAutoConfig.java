package com.guang.springboot.log.autoconfig;

import javax.annotation.PostConstruct;

import org.aopalliance.aop.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.guang.springboot.log.annotation.Log;
import com.guang.springboot.log.aop.LogMethodInterceptor;
@Configuration
@EnableConfigurationProperties(LogProperties.class)
public class LogAutoConfig extends AbstractPointcutAdvisor{
	private Logger logger = LoggerFactory.getLogger(LogAutoConfig.class);
	private Pointcut pointcut;
	private Advice advice;
	@Autowired
	private LogProperties logProperties;
	@PostConstruct
	public void init() {
		logger.info("init LogAutoConfiguration start");
		pointcut = new AnnotationMatchingPointcut(null,Log.class);
		advice = new LogMethodInterceptor(logProperties.getExclusionArr());
		logger.info("init LogAutoConfiguration end");
	}
	@Override
	public Pointcut getPointcut() {
		return pointcut;
	}

	@Override
	public Advice getAdvice() {
		return advice;
	}

}

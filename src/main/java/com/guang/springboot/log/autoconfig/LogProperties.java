package com.guang.springboot.log.autoconfig;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

@ConfigurationProperties(prefix="mylog")
public class LogProperties {
	String exclusion;
	String[] exclusionArr;
	@PostConstruct
	public void init() {
		exclusionArr = StringUtils.split(exclusion, ",");
	}
	public String getExclusion() {
		return exclusion;
	}
	public void setExclusion(String exclusion) {
		this.exclusion = exclusion;
	}
	public String[] getExclusionArr() {
		return exclusionArr;
	}
}

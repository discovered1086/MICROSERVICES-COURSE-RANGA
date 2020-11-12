package com.kingshuk.springcloudprojects.limits.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "limits-service")
@Component
@Getter
@Setter
public class LimitServiceConfiguration {
	
	private int minLimit;
	
	private int maxLimit;

}

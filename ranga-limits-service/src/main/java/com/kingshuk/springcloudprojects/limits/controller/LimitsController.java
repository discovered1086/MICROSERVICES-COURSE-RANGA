package com.kingshuk.springcloudprojects.limits.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kingshuk.springcloudprojects.limits.model.LimitConfiguration;
import com.kingshuk.springcloudprojects.limits.model.LimitServiceConfiguration;

@RestController
@RequestMapping("/limits")
public class LimitsController {

	@Autowired
	private LimitServiceConfiguration configurationProperties;

	@GetMapping
	public LimitConfiguration getLimitsFromBean() {
		return new LimitConfiguration(configurationProperties.getMinLimit(), configurationProperties.getMaxLimit());
	}

	@GetMapping("/fault-tolerance-test")
	@HystrixCommand(fallbackMethod = "fallBackLimitsConfiguration")
	public LimitConfiguration getLimitsFromConfiguration() {
		throw new RuntimeException("Something went wrong in a dependent service");
	}

	public LimitConfiguration fallBackLimitsConfiguration(){
		return new LimitConfiguration(88, 8888);
	}

}

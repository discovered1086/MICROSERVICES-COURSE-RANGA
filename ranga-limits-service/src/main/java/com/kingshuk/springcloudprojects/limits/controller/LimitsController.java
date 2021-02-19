package com.kingshuk.springcloudprojects.limits.controller;

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
	public LimitConfiguration getLimitsFromConfiguration() {
		return new LimitConfiguration(configurationProperties.getMinLimit(), configurationProperties.getMaxLimit());
	}

}

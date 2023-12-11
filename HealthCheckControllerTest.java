package com.albertsons.iot.fsa.management.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.albertsons.iot.fsa.management.controller.HealthCheckController;

@ExtendWith(MockitoExtension.class)
class HealthCheckControllerTest {

	@InjectMocks
	private HealthCheckController healthCheckController;

	@Test
	void getHealthCheckTest() {
		assertEquals(200, healthCheckController.getHealthCheckStatus().getStatusCode().value());
	}

}

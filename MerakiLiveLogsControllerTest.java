package com.albertsons.iot.fsa.management.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.albertsons.iot.fsa.management.controller.MerakiLiveLogsController;
import com.albertsons.iot.fsa.management.service.MerakiLiveLogsService;

@ExtendWith(MockitoExtension.class)
class MerakiLiveLogsControllerTest {
	
	@Mock
	private MerakiLiveLogsService merakiLiveLogsService;
	
	@InjectMocks
	private MerakiLiveLogsController merakiLiveLogsController;
	
	@Test
	void constructMerakiLiveLogURLSuccesTest() {
        String response = null;
		when(merakiLiveLogsService.constructMerakiLiveLogURL("https://earpprpaqast01/RPA/Merakifirewall/RITM1234567.log")).thenReturn(response);
		assertEquals(200, merakiLiveLogsController.constructMerakiLiveLogURL("https://earpprpaqast01/RPA/Merakifirewall/RITM1234567.log").getStatusCode().value());
	}
	
	@Test
	void constructMerakiLiveLogURLFailureTest() {
		assertEquals(400, merakiLiveLogsController.constructMerakiLiveLogURL("").getStatusCode().value());
	}


}

package com.albertsons.iot.fsa.management.test.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.albertsons.iot.fsa.management.controller.CpStoreUpdateController;
import com.albertsons.iot.fsa.management.exception.NoDataFoundException;
import com.albertsons.iot.fsa.management.model.CpStoreUpdateEventsResponse;
import com.albertsons.iot.fsa.management.service.CpStoreUpdateService;

@ExtendWith(MockitoExtension.class)
class CpStoreUpdateControllerTest {

	@Mock
	private CpStoreUpdateService cpStoreUpdateService;

	@InjectMocks
	private CpStoreUpdateController cpStoreUpdateController;
	
	@Mock
	private HttpServletRequest request;

	@Test
	void getAllCpStoreUpdateEventsTest() {
		CpStoreUpdateEventsResponse cpStoreUpdateEventsResponse = new CpStoreUpdateEventsResponse();	
		cpStoreUpdateEventsResponse.setStoreCode("storecode");
		cpStoreUpdateEventsResponse.setMessage("merakievents");
		cpStoreUpdateEventsResponse.setCorrelationId("123");
		cpStoreUpdateEventsResponse.setWan("123");
		cpStoreUpdateEventsResponse.setEventTimestamp(null);
		
		cpStoreUpdateEventsResponse.getStoreCode();
		cpStoreUpdateEventsResponse.getMessage();
		cpStoreUpdateEventsResponse.getWan();
		cpStoreUpdateEventsResponse.getCorrelationId();
		cpStoreUpdateEventsResponse.getEventTimestamp();

		cpStoreUpdateEventsResponse.toString();
		
		CpStoreUpdateEventsResponse cpStoreUpdateEventsResponses=new CpStoreUpdateEventsResponse("storecode");
		cpStoreUpdateEventsResponses.toString();
		List<String> storecodes = new ArrayList<>();
		storecodes.add("1");
		List<CpStoreUpdateEventsResponse> eventsList = new ArrayList<>();
		eventsList.add(cpStoreUpdateEventsResponse);
		when(cpStoreUpdateService.getAllCpStoreUpdateEvents(Mockito.anyList())).thenReturn(eventsList);

		assertDoesNotThrow(() -> cpStoreUpdateController.getAllCpStoreUpdateEvents(storecodes));
		
		
	}

	@Test
	void getAllCpStoreUpdateEventsExceptionTest() {
		List<String> storecodes = new ArrayList<>();
		storecodes.add("1");
		List<CpStoreUpdateEventsResponse> eventsList = new ArrayList<>();
		eventsList.clear();
		when(cpStoreUpdateService.getAllCpStoreUpdateEvents(Mockito.anyList())).thenReturn(eventsList);

		assertThrows(NoDataFoundException.class, () -> cpStoreUpdateController.getAllCpStoreUpdateEvents(storecodes));
	}
}

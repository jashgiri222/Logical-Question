package com.albertsons.iot.fsa.management.test.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.albertsons.iot.fsa.management.controller.ServiceMainAndReRepertoireServerController;
import com.albertsons.iot.fsa.management.model.CommandCenterRpaEventLogs;
import com.albertsons.iot.fsa.management.model.CommandCenterRpaEvents;
import com.albertsons.iot.fsa.management.service.ServiceMainAndReRepertoireServerServiceImpl;

@ExtendWith(MockitoExtension.class)
class ServiceMainAndReRepertoireServerControllerTest {

	@Mock
	private ServiceMainAndReRepertoireServerServiceImpl serviceMainAndReRepertoireServerServiceImpl;
	
	@InjectMocks
	private ServiceMainAndReRepertoireServerController serviceMainAndReRepertoireServerController;
	
	@Test
	void getUsecaseTest() {
		
		List<String> useCaseList = new ArrayList<>();
		useCaseList.add("ServiceMain");
		
		when(serviceMainAndReRepertoireServerServiceImpl.getAllUsecase()).thenReturn(useCaseList);
		assertDoesNotThrow(() -> serviceMainAndReRepertoireServerController.getUsecase());
		
	}
	
	@Test
	void getEventDetailsTest() {
		String usecaseType = "UseCase";
		String startDate = "Sdate";
		String endDate = "Edate";
		int offset = 330;
		
		List<CommandCenterRpaEvents> rpaDetailChangeList = new ArrayList<>();
		CommandCenterRpaEvents commandCenterRpaEvents = new CommandCenterRpaEvents();
		rpaDetailChangeList.add(commandCenterRpaEvents);
		when(serviceMainAndReRepertoireServerServiceImpl.getCcRpaEventDetails(Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).thenReturn(rpaDetailChangeList);
		assertDoesNotThrow(() -> serviceMainAndReRepertoireServerController.getEventDetails(usecaseType,startDate,endDate,offset ));
	}
	
	@Test
	void getEventMessagesTest() {
		String incident = "ctask";
		List<CommandCenterRpaEventLogs> rpaDetailChangeList = new ArrayList<>();
		CommandCenterRpaEventLogs commandCenterRpaEventLogs = new CommandCenterRpaEventLogs();
		rpaDetailChangeList.add(commandCenterRpaEventLogs);
		when(serviceMainAndReRepertoireServerServiceImpl.getCcRpaEventMessages(Mockito.anyString())).thenReturn(rpaDetailChangeList);
		assertDoesNotThrow(() -> serviceMainAndReRepertoireServerController.getEventMessages(incident));
	}
}

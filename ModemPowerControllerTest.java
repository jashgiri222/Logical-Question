package com.albertsons.iot.fsa.management.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.albertsons.iot.fsa.management.controller.ModemPowerController;
import com.albertsons.iot.fsa.management.exception.NoDataFoundException;
import com.albertsons.iot.fsa.management.model.CommandHistoryRequest;
import com.albertsons.iot.fsa.management.model.CommandRequest;
import com.albertsons.iot.fsa.management.model.CommandResponse;
import com.albertsons.iot.fsa.management.model.Device;
import com.albertsons.iot.fsa.management.service.DeviceService;
import com.albertsons.iot.fsa.management.service.MerakiRebootService;
import com.albertsons.iot.fsa.management.util.CommonConstants;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
class ModemPowerControllerTest {

	@Mock
	private MerakiRebootService merakiRebootService;

	@Mock
	private DeviceService deviceService;

	@InjectMocks
	private ModemPowerController modemPowerController;

	@Mock
	private HttpServletRequest request;

	@Test
	void getAllDeviceTest() {
		List<String> stores = new LinkedList<>();
		stores.add(CommonConstants.STORE_ID);

		List<Device> deviceResponses = new LinkedList<>();
		Device deviceResponse = new Device();
		deviceResponses.add(deviceResponse);

		when(deviceService.getAllModemDevices(stores)).thenReturn(deviceResponses);
		assertEquals(200, modemPowerController.getAllDevices(stores).getStatusCode().value());
	}

	@Test
	void getAllDeviceTestEmptyResponse() {
		List<String> stores = new LinkedList<>();
		stores.add(CommonConstants.STORE_ID);

		when(deviceService.getAllModemDevices(stores)).thenReturn(new LinkedList<>());
		assertThrows(NoDataFoundException.class, () -> modemPowerController.getAllDevices(stores));
	}

	@Test
	void togglePowerTest() {
		CommandRequest commandRequest = new CommandRequest();
		assertEquals(200, modemPowerController.togglePower(commandRequest).getStatusCode().value());
	}

	@Test
	void getCommandHistoryTestNegative() {
		CommandHistoryRequest commandHistoryRequest = new CommandHistoryRequest();
		assertThrows(NoDataFoundException.class, () -> modemPowerController.getCommandHistory(commandHistoryRequest));
	}

	@Test
	void getCommandHistoryTest() {
		CommandHistoryRequest commandHistoryRequest = new CommandHistoryRequest();
		commandHistoryRequest.setDeviceCode("1234");
		CommandResponse commandResponse = new CommandResponse();
		commandResponse.setCommand("ON");
		commandResponse.setCommandStatus("ON");
		commandResponse.setCreatedBy("createdbby");
		commandResponse.setCreatedDateTime(null);

		commandResponse.getCommand();
		commandResponse.getCommandStatus();
		commandResponse.getCreatedBy();
		commandResponse.getCreatedDateTime();

		when(merakiRebootService.getCommandHistory(commandHistoryRequest.getDeviceCode()))
				.thenReturn(Collections.singletonList(commandResponse));
		assertEquals(200, modemPowerController.getCommandHistory(commandHistoryRequest).getStatusCode().value());
	}
}

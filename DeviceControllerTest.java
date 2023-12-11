package com.albertsons.iot.fsa.management.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.util.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.albertsons.iot.fsa.management.controller.DeviceController;
import com.albertsons.iot.fsa.management.entity.ModemTelematicMessageDAO;
import com.albertsons.iot.fsa.management.entity.PlumTelematicMessageDAO;
import com.albertsons.iot.fsa.management.entity.TelematicMessageDAO;
import com.albertsons.iot.fsa.management.exception.NoDataFoundException;
import com.albertsons.iot.fsa.management.exception.RecordNotFoundException;
import com.albertsons.iot.fsa.management.log.LogDetail;
import com.albertsons.iot.fsa.management.model.DeviceRequest;
import com.albertsons.iot.fsa.management.model.DeviceResponse;
import com.albertsons.iot.fsa.management.model.DeviceType;
import com.albertsons.iot.fsa.management.model.EventMessage;
import com.albertsons.iot.fsa.management.repository.DeviceRepository;
import com.albertsons.iot.fsa.management.service.DeviceService;
import com.albertsons.iot.fsa.management.service.EventMessageService;
import com.albertsons.iot.fsa.management.service.ExcelParser;
import com.albertsons.iot.fsa.management.service.MerakiDeviceService;
import com.albertsons.iot.fsa.management.service.OnboardTemplateService;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
class DeviceControllerTest {

	@Mock
	private DeviceService deviceService;

	@Mock
	private ExcelParser excelHelper;

	@InjectMocks
	private DeviceController deviceController;

	@Mock
	private OnboardTemplateService onboardTemplateService;

	@Mock
	private EventMessageService eventMessageService;

	@Mock
	private HttpServletRequest request;

	@Mock
	private MerakiDeviceService merakiDeviceService;

	@Mock
	private DeviceRepository deviceRepository;

	@Test
	void getDeviceByIdTest() {
		DeviceResponse deviceResponse = new DeviceResponse(1);
		when(deviceService.getDeviceById(Mockito.anyInt())).thenReturn(deviceResponse);
		assertEquals(deviceResponse, deviceController.getDeviceById(1).getBody());
	}

	@Test
	void getAllDevicesTest() {
		DeviceResponse device = new DeviceResponse();
		device.setDeviceCode("devicecode");
		device.setDeviceId(123);
		device.setDeviceName("deviename");
		device.setIotPrimarykey("iot");
		device.setStatus("status");
		device.setLastUpdateTime(null);
		device.setDevicePropertyList(null);
		device.setLastReportedDate(null);
		device.setDeviceType("devicetype");
		device.getDeviceCode();
		device.getDeviceId();
		device.getDeviceName();
		device.getIotPrimarykey();
		device.getStatus();
		device.getLastUpdateTime();
		device.getDevicePropertyList();
		device.getLastReportedDate();
		device.getDeviceType();

		List<String> stores = new ArrayList<>();
		stores.add("1");
		List<DeviceResponse> deviceList = Collections.singletonList(device);

		LogDetail logDetail = new LogDetail("status", null, null, null, "logdetail", 0);
		logDetail.toString();
		when(deviceService.getAllDevices(Mockito.anyList())).thenReturn(deviceList);
		assertEquals(200, deviceController.getAllDevices(stores).getStatusCode().value());
	}

	@Test
	void getDeviceByIdTestNegative() {
		when(deviceService.getDeviceById(Mockito.anyInt())).thenReturn(null);
		assertThrows(RecordNotFoundException.class, () -> deviceController.getDeviceById(1));
	}

	@Test
	void getAllDevicesTestNegative() {
		List<DeviceResponse> deviceResponseList = new LinkedList<>();
		List<String> stores = new ArrayList<>();
		stores.add("1");
		when(deviceService.getAllDevices(Mockito.anyList())).thenReturn(deviceResponseList);
		assertThrows(NoDataFoundException.class, () -> deviceController.getAllDevices(stores));
	}

	@Test
	void getDeviceTypes() {
		DeviceType deviceType = new DeviceType(1);
		deviceType.getDeviceTypeId();
		deviceType.getName();
		deviceType.getDescription();
		when(deviceService.getDeviceTypes()).thenReturn(Collections.singletonList(deviceType));
		assertThat(deviceController.getDeviceTypes().getStatusCode().value()).isEqualTo(200);
	}

	@Test
	void getDeviceTypesNegative() {
		when(deviceService.getDeviceTypes()).thenReturn(new LinkedList<DeviceType>());
		assertThrows(NoDataFoundException.class, () -> deviceController.getDeviceTypes());
	}

	@Test
	void uploadExcelFileDevice() throws IllegalArgumentException, IOException {
		File file = new File("src/test/resources/Sample data.xlsx");
		FileInputStream input = new FileInputStream(file);
		MultipartFile multipartFile = new MockMultipartFile("file", file.getName(),
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", IOUtils.toByteArray(input));
		when(excelHelper.uploadExcelFileDevice(multipartFile, "test", 1, Optional.of("test"), Optional.of("test")))
				.thenReturn("test");
		assertThat(deviceController
				.uploadExcelFileDevice(multipartFile, "test", 1, Optional.of("test"), Optional.of("test"))
				.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	void getOnboardingTemplateUrlTest() {
		when(onboardTemplateService.getOnboardingTemplateUrl(Mockito.anyString())).thenReturn("url");
		assertThat(deviceController.getOnboardingTemplateUrl("deviceType").getBody()).isEqualTo("url");
	}

	@Test
	void getOnboardingTemplateUrlTestNegative() {
		when(onboardTemplateService.getOnboardingTemplateUrl(Mockito.anyString())).thenReturn(null);
		assertThrows(NoDataFoundException.class, () -> deviceController.getOnboardingTemplateUrl("deviceType"));
	}

	@Test
	void getEventMessagesByIdTest() {
		EventMessage eventMessage = new EventMessage();
		eventMessage.getCode();
		eventMessage.getDeviceId();
		eventMessage.getEventTimestamp();
		eventMessage.getMessage();
		eventMessage.getSeverity();
		DeviceRequest deviceRequest = new DeviceRequest("1231");
		deviceRequest.getAssetId();
		List<EventMessage> eventMessages = Collections.singletonList(eventMessage);
		when(eventMessageService.getEventMessagesById(Mockito.anyInt())).thenReturn(eventMessages);
		assertEquals(eventMessages, deviceController.getEventMessagesById(Mockito.anyInt()).getBody());
	}

	@Test
	void getEventMessagesByDeviceCodeTest() {
		EventMessage eventMessage = new EventMessage();
		eventMessage.getCode();
		eventMessage.getDeviceId();
		eventMessage.getEventTimestamp();
		eventMessage.getMessage();
		eventMessage.getSeverity();
		DeviceRequest deviceRequest = new DeviceRequest("1231");
		deviceRequest.getAssetId();
		assertDoesNotThrow(() -> deviceController.getEventMessagesByDeviceCode("deviceCode"));
	}

	@Test
	void getTelematicMessagesByIdForPlumTest() {
		PlumTelematicMessageDAO plumTelematicMessage = new PlumTelematicMessageDAO();
		List<PlumTelematicMessageDAO> plumTelematicMessages = Collections.singletonList(plumTelematicMessage);
		String deviceCode = "9611-ISP-01";
		String deviceId = "ISP";
		when(deviceRepository.findDeviceCodeByDeviceId(Mockito.anyInt())).thenReturn(deviceCode);
		when(deviceRepository.findDeviceTypeByDeviceId(Mockito.anyInt())).thenReturn(deviceId);
		when(eventMessageService.getPlumTelematicMessagesById(Mockito.any())).thenReturn(plumTelematicMessages);
		assertEquals(plumTelematicMessages, deviceController.getTelematicMessagesById(Mockito.anyInt()).getBody());
	}

	@Test
	void getTelematicMessagesByIdForModemTest() {
		ModemTelematicMessageDAO modemTelematicMessage = new ModemTelematicMessageDAO();
		List<ModemTelematicMessageDAO> modemTelematicMessages = Collections.singletonList(modemTelematicMessage);
		String deviceCode = "AA6BD2";
		String deviceId = "Modem Smart Switch";
		when(deviceRepository.findDeviceCodeByDeviceId(Mockito.anyInt())).thenReturn(deviceCode);
		when(deviceRepository.findDeviceTypeByDeviceId(Mockito.anyInt())).thenReturn(deviceId);
		when(eventMessageService.getModemTelematicMessagesById(Mockito.any())).thenReturn(modemTelematicMessages);
		assertEquals(modemTelematicMessages, deviceController.getTelematicMessagesById(Mockito.anyInt()).getBody());
	}

	@Test
	void getTelematicMessagesByIdTest() {
		TelematicMessageDAO telematicMessage = new TelematicMessageDAO();
		List<TelematicMessageDAO> telematicMessages = Collections.singletonList(telematicMessage);
		String deviceCode = "9611-Printer-01";
		String deviceId = "Printer";
		when(deviceRepository.findDeviceCodeByDeviceId(Mockito.anyInt())).thenReturn(deviceCode);
		when(deviceRepository.findDeviceTypeByDeviceId(Mockito.anyInt())).thenReturn(deviceId);
		when(eventMessageService.getTelematicMessagesById(Mockito.any())).thenReturn(telematicMessages);
		assertEquals(telematicMessages, deviceController.getTelematicMessagesById(Mockito.anyInt()).getBody());
	}

	@Test
	void updateMerakiDeviceDetailsForGivenStoreTest() {
		EventMessage eventMessage = new EventMessage();
		eventMessage.getCode();
		eventMessage.getDeviceId();
		eventMessage.getEventTimestamp();
		eventMessage.getMessage();
		eventMessage.getSeverity();
		DeviceRequest deviceRequest = new DeviceRequest("1231");
		deviceRequest.getAssetId();
		assertDoesNotThrow(() -> deviceController.updateMerakiDeviceDetailsForGivenStore("deviceCode"));
	}

}

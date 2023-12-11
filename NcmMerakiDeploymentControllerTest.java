package com.albertsons.iot.fsa.management.test.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.albertsons.iot.fsa.management.controller.NcmMerakiDeploymentController;
import com.albertsons.iot.fsa.management.exception.NoDataFoundException;
import com.albertsons.iot.fsa.management.model.CPUpdateRequest;
import com.albertsons.iot.fsa.management.model.CpGroupResponse;
import com.albertsons.iot.fsa.management.model.StaticIPDetail;
import com.albertsons.iot.fsa.management.service.CpGroupService;
import com.albertsons.iot.fsa.management.service.NcmStoreUpdateService;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
class NcmMerakiDeploymentControllerTest {

	@Mock
	private NcmStoreUpdateService ncmStoreUpdateService;

	@InjectMocks
	private NcmMerakiDeploymentController ncmMerakiDeploymentController;

	@Mock
	private HttpServletRequest request;

	@Mock
	private CpGroupService cpGroupService;

	@Test
	void handleCradlepointManagementRequestTest() {
		CPUpdateRequest cpUpdateRequest = new CPUpdateRequest();
		assertDoesNotThrow(() -> ncmMerakiDeploymentController.handleCradlepointManagementRequest(cpUpdateRequest));
	}

	@Test
	void getStaticDetailsTest() {
		StaticIPDetail staticIPDetails = new StaticIPDetail();
		staticIPDetails.setGatewayIp("gateway");
		staticIPDetails.setPrimaryDns("primdns");
		staticIPDetails.setPublicIp("publicip");
		staticIPDetails.setSecondaryDns("secdns");

		staticIPDetails.getGatewayIp();
		staticIPDetails.getPrimaryDns();
		staticIPDetails.getPublicIp();
		staticIPDetails.getSecondaryDns();

		StaticIPDetail staticIPDetail = new StaticIPDetail("1", "1", "1", "1");
		when(ncmStoreUpdateService.getStaticDetailsForTheGivenStoreAndMacId("1234", "wan1")).thenReturn(staticIPDetail);
		assertEquals(200, ncmMerakiDeploymentController.getStaticDetails("1234", "wan1").getStatusCode().value());
	}

	@Test
	void getStaticDetailsTestNegative() {
		when(ncmStoreUpdateService.getStaticDetailsForTheGivenStoreAndMacId("1234", "wan1")).thenReturn(null);
		assertThrows(NoDataFoundException.class, () -> ncmMerakiDeploymentController.getStaticDetails("1234", "wan1"));
	}

	@Test
	void getCPGroupsTest() {
		CpGroupResponse cpGroupResponse = new CpGroupResponse();
		when(cpGroupService.getAllGroups()).thenReturn(cpGroupResponse);
		assertDoesNotThrow(() -> ncmMerakiDeploymentController.getCradlePointGroups());

	}

	@Test
	void getCradlePointGroupTest() {
		when(cpGroupService.getCpGroup("macid", "type")).thenReturn(25897);
		assertDoesNotThrow(() -> ncmMerakiDeploymentController.getCradlePointGroup("macid", "type"));

	}
}

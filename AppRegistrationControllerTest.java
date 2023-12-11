
package com.albertsons.iot.fsa.management.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.albertsons.iot.fsa.management.controller.AppRegistrationController;
import com.albertsons.iot.fsa.management.exception.NoDataFoundException;
import com.albertsons.iot.fsa.management.log.LogDetail;
import com.albertsons.iot.fsa.management.model.AppIncident;
import com.albertsons.iot.fsa.management.service.AppRegistrationServiceImpl;

@ExtendWith(MockitoExtension.class)
class AppRegistrationControllerTest {

	@Mock
	AppRegistrationServiceImpl appRegistrationServiceImpl;

	@InjectMocks
	private AppRegistrationController appRegistrationController;

	@Test
	void getAppIncidentsExceptionTest() {

		List<AppIncident> appIncident = new ArrayList<>();

		LogDetail logDetail = new LogDetail("status", null, null, null, "logdetail", 0);
		logDetail.toString();
		String appNames = "app01";
		String startDate = "2022-10-01";
		String endDate = "2022-11-11";
		int offset = 330;

		when(appRegistrationServiceImpl.findAppIncident(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
				Mockito.anyInt())).thenReturn(appIncident);
		// assertEquals(200,
		// appRegistrationController.getAppIncidents().getStatusCodeValue());
		assertThrows(NoDataFoundException.class,
				() -> appRegistrationController.getAppIncidents(appNames, startDate, endDate, offset));
	}

	@Test
	void getAppIncidentsPositveTest() {

		List<AppIncident> appIncidentList = new ArrayList<>();
		AppIncident appIncident = new AppIncident();
		appIncident.setAppName("AppOne");
		appIncident.setRequest(
				"{\"assignment_group\":\"Command Center Operations\",\"correlation_id\":\"test_19-08-2022_40\",\"generic_ci\":\"Modem\",\"impact\":\"3\",\"location\":\"9878\",\"long_desc\":\"Modem not reporting at 9878\",\"resolved\":\"false\",\"short_desc\":\"Modem is not reporting\",\"source_ci\":\"MX9878A\",\"timestamp\":\"2022-08-19T20:09:45+00:00\",\"urgency\":\"2\"}");
		appIncident.setResponse(
				"{\"status\":\"201\",\"ID\":\"INC8333900\",\"Shortdesc\":\"Store #9878 Modem is not reporting\",\"state\":\"In Progress\",\"inc_assignmentgroup\":\"Command Center Operations\"}");
		appIncident.setRequestId("test_19-08-2022_40_AppOne");
		appIncident.setQueueStatus("Processed");
		appIncident.setRequestReceived("2022-08-22 07:08:50.945491+00");
		appIncident.setRequestQueued("2022-08-22 07:08:51.273614+00");
		appIncident.setRequestProcessed("2022-08-22 07:09:31.649146+00");

		String appNames = "app01";
		String startDate = "2022-10-01";
		String endDate = "2022-11-11";
		int offset = 330;

		appIncidentList.add(appIncident);

		LogDetail logDetail = new LogDetail("status", null, null, null, "logdetail", 0);
		logDetail.toString();

		when(appRegistrationServiceImpl.findAppIncident(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
				Mockito.anyInt())).thenReturn(appIncidentList);
		assertEquals(200, appRegistrationController.getAppIncidents(appNames, startDate, endDate, offset)
				.getStatusCode().value());

	}

	@Test
	void getDistinctAppNameTest() {

		List<String> appNames = new ArrayList<>();
		appNames.add("devApp");

		when(appRegistrationServiceImpl.getAllApps()).thenReturn(appNames);
		assertEquals(200, appRegistrationController.getDistinctAppName().getStatusCode().value());

	}

}

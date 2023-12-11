package com.albertsons.iot.fsa.management.test.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import com.albertsons.iot.fsa.management.controller.CpBillingController;
import com.albertsons.iot.fsa.management.model.CpBillingUsageExceededResponse;
import com.albertsons.iot.fsa.management.service.CpBillingServiceImpl;

@ExtendWith(MockitoExtension.class)
class CpBillingControllerTest {

	@Mock
	private CpBillingServiceImpl cradlepointService;

	@InjectMocks
	private CpBillingController cpBillingController;

	@Test
	void retrieveOutputFileUrlAndDataTest() {
		String fromDate = "fromdate";
		String toDate = "todate";
		assertDoesNotThrow(() -> cpBillingController.retrieveOutputFileUrlAndData(fromDate, toDate));
	}

	@Test
	void uploadSrcFileAndInsertThresholdTestNegative() {
		MultipartFile multipartFile = null;
		String cradlepointFile = "cradlepointFile";
		assertThrows(IOException.class,
				() -> cpBillingController.uploadSrcFileAndInsertThreshold(multipartFile, cradlepointFile));
	}

	@Test
	void retrieveExceededUsageDataTest() {
		int reportId = 123;

		assertDoesNotThrow(() -> cpBillingController.retrieveExceededUsageData(reportId));
	}

	@Test
	void sendDataForIncidentCreationTest() {
		CpBillingUsageExceededResponse cradlepointExceededResponse = new CpBillingUsageExceededResponse();

		assertDoesNotThrow(() -> cpBillingController.sendDataForIncidentCreation(cradlepointExceededResponse));
	}

}

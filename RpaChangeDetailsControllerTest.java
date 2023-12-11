package com.albertsons.iot.fsa.management.test.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.albertsons.iot.fsa.management.controller.RpaChangeDetailsController;
import com.albertsons.iot.fsa.management.service.RpaChangeDetailsServiceImpl;

@ExtendWith(MockitoExtension.class)
 class RpaChangeDetailsControllerTest {

	@Mock
	private RpaChangeDetailsServiceImpl rpaChangeDetailsServiceImpl;

	@InjectMocks
	private RpaChangeDetailsController rpaChangeDetailsController;

	@Test
	void getRpaSummaryDetailsTest() {
		String ctaskId = "ctask";
		assertDoesNotThrow(() -> rpaChangeDetailsController.getRpaSummaryDetails(ctaskId));
	}

	
	@Test
	void getRpaSummaryDetailsTest1() {
		String usecaseType = "UseCase";
		String startDate = "Sdate";
		String endDate = "Edate";
		int offset = 330;
		assertDoesNotThrow(() -> rpaChangeDetailsController.getRpaSummaryDetails(usecaseType, startDate, endDate, offset));
	}
	 

	@Test
	void getRpaUsecaseTest() {
		assertDoesNotThrow(() -> rpaChangeDetailsController.getRpaUsecase());
	}
}

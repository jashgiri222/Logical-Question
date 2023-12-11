package com.albertsons.iot.fsa.management.test.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import jakarta.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.albertsons.iot.fsa.management.controller.ExclusionController;
import com.albertsons.iot.fsa.management.exception.InvalidDataException;
import com.albertsons.iot.fsa.management.model.DivisionStoreDetails;
import com.albertsons.iot.fsa.management.model.ExclusionRules;
import com.albertsons.iot.fsa.management.service.ExclusionService;

@ExtendWith(MockitoExtension.class)
class ExclusionControllerTest {

	@Mock
	private ExclusionService exclusionService;
	
	@InjectMocks
	private ExclusionController exclusionController;
	
	@Mock
	private HttpServletRequest request;
	
	
	@Test
	void getAllDivisionStoresTest() {
		
		assertDoesNotThrow(() -> exclusionController.getAllDivisionStores());
	}
	
	@Test
	void updateExclusionRulesTest() {
		String division ="division";
		String assetType = "assettype"; 
		
		DivisionStoreDetails details=new DivisionStoreDetails();
		details.getAssetTypes();
		details.getDivisionStores();
		details.toString();
		
		ExclusionRules exclusionRules=new ExclusionRules();
		
		assertDoesNotThrow(() -> exclusionController.updateExclusionRules(division,assetType,exclusionRules));
	}
	
	@Test
	void updateExclusionRulesTestNegative() {
		String division ="division";
		String assetType = "assettype"; 
		
		DivisionStoreDetails details=new DivisionStoreDetails();
		details.getAssetTypes();
		details.getDivisionStores();
		details.toString();
		
		ExclusionRules exclusionRules=null;
		
		assertThrows(InvalidDataException.class,() -> exclusionController.updateExclusionRules(division,assetType,exclusionRules));
	}

	
	@Test
	void getExclusionRulesTest() {
		String division ="division";
		String assetType = "assettype"; 
		
		assertDoesNotThrow(() -> exclusionController.getExclusionRules(division,assetType));
	}
}

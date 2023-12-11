package com.albertsons.iot.fsa.management.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.albertsons.iot.fsa.management.controller.StoreController;
import com.albertsons.iot.fsa.management.exception.NoDataFoundException;
import com.albertsons.iot.fsa.management.model.StoreAssetDeviceDetailsResponse;
import com.albertsons.iot.fsa.management.model.StoreResponse;
import com.albertsons.iot.fsa.management.service.StoreService;
import com.albertsons.iot.fsa.management.util.CommonConstants;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
class StoreControllerTest {

	@Mock
	private StoreService storeService;

	@Mock
	private HttpServletRequest request;

	@InjectMocks
	private StoreController storeController;

	@Test
	void getAllStoresTest() {
		StoreResponse store = new StoreResponse(1);
		StoreResponse storeResponse = new StoreResponse();
		storeResponse.setStoreCode("2345");
		storeResponse.setStoreId(123);
		storeResponse.getStoreCode();
		storeResponse.getStoreId();

		List<StoreResponse> stores = Collections.singletonList(store);
		when(storeService.getAllStores("key")).thenReturn(stores);
//		when()
		assertEquals(200, storeController.getAllStores().getStatusCode().value());
	}

	@Test
	void getAllStoresTestException() {
		StoreResponse storeResponse = new StoreResponse();
		storeResponse.getStoreCode();
		storeResponse.getStoreId();
		List<StoreResponse> stores = new ArrayList<>();
		when(storeService.getAllStores("key")).thenReturn(stores);
		assertThrows(NoDataFoundException.class, () -> storeController.getAllStores());
	}

	@Test
	void getStoreDetailsTest() {
		StoreAssetDeviceDetailsResponse completeDetailsResponse = new StoreAssetDeviceDetailsResponse(1);
		List<StoreAssetDeviceDetailsResponse> completeDetailsResponses = Collections
				.singletonList(completeDetailsResponse);
		List<String> stores = new LinkedList<>();
		stores.add(CommonConstants.STORE_ID);
		when(storeService.getAllStoreAssetDeviceDetails(stores)).thenReturn(completeDetailsResponses);
		assertEquals(completeDetailsResponses, storeController.getAllStoreAssetDeviceDetails(stores).getBody());
	}

	@Test
	void getAllStoresTestNegative() {
		when(storeService.getAllStores("key")).thenReturn(new LinkedList<StoreResponse>());
		assertThrows(NoDataFoundException.class, () -> storeController.getAllStores());
	}

}

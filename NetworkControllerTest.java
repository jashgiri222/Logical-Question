package com.albertsons.iot.fsa.management.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.albertsons.iot.fsa.management.controller.NetworkController;
import com.albertsons.iot.fsa.management.model.CancelRitOrSubritResponse;
import com.albertsons.iot.fsa.management.model.RitCancelOrModifySchedule;
import com.albertsons.iot.fsa.management.repository.NetworkRepository;
import com.albertsons.iot.fsa.management.service.NetworkServiceImpl;
import com.albertsons.iot.fsa.management.service.UserDetailsService;
import com.albertsons.iot.fsa.management.util.OffsetTimeMgmtUtil;

@ExtendWith(MockitoExtension.class)

class NetworkControllerTest {

	@InjectMocks
	NetworkController networkController;

	@Mock
	NetworkServiceImpl networkServiceImpl;

	@Mock
	NetworkRepository networkRepository;

	@Mock
	private OffsetTimeMgmtUtil offsetTimeMgmtUtil;

	@Mock
	private UserDetailsService userDetailsService;

	@Test
	void getMerakifirewallRitDetails() {

		String startDate = "Sdate";
		String endDate = "Edate ";
		int offset = 330;
		when(networkServiceImpl.getMerakifirewallRitDetails(startDate, endDate, offset)).thenReturn("success");
		assertEquals(200,
				networkController.getMerakifirewallRitDetails(startDate, endDate, offset).getStatusCode().value());
	}

	@Test
	void modifyScheduleDateAndTime() {

		RitCancelOrModifySchedule request = new RitCancelOrModifySchedule();
		request.setChildId("RIT1234-12-QA");
		request.setTimeStamp("2023-12-07T01:27:00.000Z");
		request.setOldTimeStamp("2023-12-06T01:27:00.000Z");
		request.setOffset(330);
		when(networkServiceImpl.modifyScheduleDateAndTime("RIT1234-12-QA", "2023-12-07T01:27:00.000Z", null, 330))
				.thenReturn("400");
		assertEquals(400, networkController.modifyScheduleDateAndTime(request).getStatusCode().value());
	}

	@Test
	void modifyScheduleDateAndTimeFailure() {

		RitCancelOrModifySchedule request = new RitCancelOrModifySchedule();
		request.setChildId("RIT1234-12-QA");
		request.setTimeStamp("2023-12-07T01:27:00.000Z");
		request.setOldTimeStamp("2023-12-06T01:27:00.000Z");
		request.setOffset(330);
		when(networkServiceImpl.modifyScheduleDateAndTime("RIT1234-12-QA", "2023-12-07T01:27:00.000Z", null, 330))
				.thenReturn(null);
		assertEquals(500, networkController.modifyScheduleDateAndTime(request).getStatusCode().value());
	}

	@Test
	void cancelRitOrSubrit() {
		RitCancelOrModifySchedule request = new RitCancelOrModifySchedule();
		request.setTask("RIT1234-12-QA");
		request.setLevel("parent");
		assertThrows(Exception.class, () -> networkController.cancelRitOrSubrit(request));
	}

	@Test
	void cancelRitOrSubritFailure() {
		CancelRitOrSubritResponse cancelRit = new CancelRitOrSubritResponse();
		cancelRit.setErrorMsg(null);
		RitCancelOrModifySchedule request = new RitCancelOrModifySchedule();
		request.setTask("RIT1234-12-QA");
		request.setLevel("parent");
		when(userDetailsService.getAuthenticatedUser()).thenReturn("test");
		when(networkServiceImpl.cancelRitOrSubrit(request.getTask(), request.getLevel(), "test")).thenReturn(cancelRit);
		assertEquals(500, networkController.cancelRitOrSubrit(request).getStatusCode().value());
	}

}

package com.albertsons.iot.fsa.management.test.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.albertsons.iot.fsa.management.controller.UserProfileController;
import com.albertsons.iot.fsa.management.exception.NoDataFoundException;
import com.albertsons.iot.fsa.management.model.UserProfileResponse;
import com.albertsons.iot.fsa.management.service.UserProfileService;

@ExtendWith(MockitoExtension.class)
class UserProfileControllerTest {

	@Mock
	private UserProfileService userProfileService;

	@InjectMocks
	private UserProfileController userProfileController;
	
	@Mock
	private HttpServletRequest request;

	@Test
	void getSecurityRoleClaimTest() {

		when(userProfileService.getAllSecurityRoleClaimForUser()).thenReturn(new UserProfileResponse());
		assertDoesNotThrow(() -> userProfileController.getSecurityRoleClaim());

	}

	@Test
	void getSecurityRoleClaimThrowsExceptionTest() {

		when(userProfileService.getAllSecurityRoleClaimForUser()).thenReturn(null);
		assertThrows(NoDataFoundException.class, () -> userProfileController.getSecurityRoleClaim());

	}

}

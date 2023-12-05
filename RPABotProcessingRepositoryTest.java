package com.albertsons.iot.fsa.management.test.repository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.albertsons.iot.fsa.management.kafka.model.RPABotAssignment;
import com.albertsons.iot.fsa.management.kafka.model.RPAGenericResponsePayload;
import com.albertsons.iot.fsa.management.kafka.model.RPAServiceNowUpdateDetails;
import com.albertsons.iot.fsa.management.kafka.repository.RPABotProcessingRepository;

@ExtendWith(MockitoExtension.class)
class RPABotProcessingRepositoryTest {

	@Mock
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private RPABotProcessingRepository rpaBotProcessingRepository;

	@Test
	void getConfigValue() {
		String ctaskid = "id";
		String usecaseName="de";
		String inputPayload="de";
		String requester = "de";
		
		//when(jdbcTemplate.getJdbcTemplate().queryForObject(CommonConstants.RPA_CONFIG_DETIAIL,mockito;
		Assertions.assertDoesNotThrow(
				() -> rpaBotProcessingRepository.insertReceivedUsecasePayload(requester, inputPayload, usecaseName, ctaskid));
	}

	@Test
	void updateSummaryStatus() {
		String ctaskid = "id";
		String status = "active";
		String deploymentid = "1";
	    String inpayload = "123";
	    String request_id = "i";
	    String ctaskType  = "Type";
		
		//when(jdbcTemplate.getJdbcTemplate().queryForObject(CommonConstants.RPA_CONFIG_DETIAIL,mockito;
		Assertions.assertDoesNotThrow(
				() -> rpaBotProcessingRepository.insertRPADetails(ctaskType, inpayload, deploymentid, status, ctaskid, request_id));
	}
	
	@Test
	void insertReceivedMerakiUsecasePayload() {
		String ctaskId = "ctaskId";
		String ctaskType = "ctaskType";
		String inputPayload = "Inputpayload";
		String requester = "requester";
		String merakiScheduleTime = "scheduletime";
		
		Assertions.assertDoesNotThrow(
				() -> rpaBotProcessingRepository.insertReceivedMerakiUsecasePayload(merakiScheduleTime, requester, inputPayload, ctaskType, ctaskId));
	}
	
	
	@Test
	void insertRPADetailsTest() {
		String ctaskId = "ctaskId";
		String ctaskType = "ctaskType";
		String request_id = "id";
	    String deploymentid = "DeployId";
		String inpayload ="inpayload";
		String status = "Active";
		Assertions.assertDoesNotThrow(
				() -> rpaBotProcessingRepository.insertRPADetails(status, inpayload, deploymentid, request_id, ctaskType, ctaskId));
	}
	
	
	/*
	 * @Test void updateDetailStatusTest() { String ctaskId = "ctaskId"; String
	 * responsePayload = "Payload"; String reqId = "reqId"; String status =
	 * "Active"; Assertions.assertDoesNotThrow( () ->
	 * rpaBotProcessingRepository.updateDetailStatus(status, reqId, responsePayload,
	 * ctaskId)); }
	 */

	@Test
	void updateScheduleDateTest() {
		String ctaskId = "ctaskId";
		String schedule = "schedule";
		Assertions.assertDoesNotThrow(
				() -> rpaBotProcessingRepository.updateScheduleDate(schedule, ctaskId));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void getRpaBotDetailsTest() {
		String usecaseName ="Usecase";
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(180270);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase("VoiceNameChange");
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");
		JdbcTemplate mockJdbcTemplate = Mockito.mock(JdbcTemplate.class);

		when(jdbcTemplate.getJdbcTemplate()).thenReturn(mockJdbcTemplate);
		when(mockJdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(BeanPropertyRowMapper.class),
				Mockito.anyString())).thenReturn(botAssignment);
		Assertions.assertDoesNotThrow(() -> rpaBotProcessingRepository.getRpaBotDetails(usecaseName));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void getRpaBotDetailsTest1() {
		String usecaseName ="Usecase";
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(180270);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase("VoiceNameChange");
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");
		JdbcTemplate mockJdbcTemplate = Mockito.mock(JdbcTemplate.class);

		when(jdbcTemplate.getJdbcTemplate()).thenReturn(mockJdbcTemplate);
		when(mockJdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(BeanPropertyRowMapper.class),
				Mockito.anyString())).thenThrow(NullPointerException.class);
		Assertions.assertDoesNotThrow(() -> rpaBotProcessingRepository.getRpaBotDetails(usecaseName));
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	void toGetCtaskidTest() {
		RPAGenericResponsePayload rPAGenericResponsePayload = new RPAGenericResponsePayload();
		RPAServiceNowUpdateDetails snowDetails = new RPAServiceNowUpdateDetails();
		snowDetails.setCtaskId("phoneadd3006");
		snowDetails.setPushRetryCount(1);
	    when(jdbcTemplate.query(Mockito.anyString(),Mockito.anyMap(), Mockito.any(BeanPropertyRowMapper.class))).thenReturn(Collections.singletonList(snowDetails));
		Assertions.assertDoesNotThrow(() -> rpaBotProcessingRepository.toGetCtaskid(rPAGenericResponsePayload));
	}
	
	@Test
	void updateServiceNowFlagTest() {
		String ctaskId = "taskid";
		boolean flag = true;
		
		Assertions.assertDoesNotThrow(
				() -> rpaBotProcessingRepository.updateServiceNowFlag(ctaskId, flag));
	}
	
	@Test
	void updateSummaryStatusTest() {
		
		String ctaskid ="ctaskid"; 
		String status ="Failure";
		int retryCount = 1;
		Assertions.assertDoesNotThrow(() -> rpaBotProcessingRepository.updateSummaryStatus(ctaskid, status, retryCount));
	}
	
	@Test
	void getConfigValueTest() {
		
		String key = "key";
		JdbcTemplate mockJdbcTemplate = Mockito.mock(JdbcTemplate.class);

		when(jdbcTemplate.getJdbcTemplate()).thenReturn(mockJdbcTemplate);
		Assertions.assertDoesNotThrow(() -> rpaBotProcessingRepository.getConfigValue(key));
	}
	
	@Test
	void getUsecaseTypeTest() {
		
		String key = "key";
		JdbcTemplate mockJdbcTemplate = Mockito.mock(JdbcTemplate.class);

		when(jdbcTemplate.getJdbcTemplate()).thenReturn(mockJdbcTemplate);
		Assertions.assertDoesNotThrow(() -> rpaBotProcessingRepository.getUsecaseType(key));
	}
	
	@Test
	void getRequestIdTest() {
		
		String key = "key";
		JdbcTemplate mockJdbcTemplate = Mockito.mock(JdbcTemplate.class);

		when(jdbcTemplate.getJdbcTemplate()).thenReturn(mockJdbcTemplate);
		Assertions.assertDoesNotThrow(() -> rpaBotProcessingRepository.getRequestId(key));
	}
	
	@Test
	void updateServiceNowFlagTest1() {
		String ctaskId = "taskid";
		boolean flag = true;
		JdbcTemplate mockJdbc=mock(JdbcTemplate.class);
		when(jdbcTemplate.getJdbcTemplate()).thenReturn(mockJdbc);
		when(mockJdbc.update(Mockito.anyString(),Mockito.anyBoolean(),Mockito.anyString())).thenReturn(5);
		Assertions.assertDoesNotThrow(() -> rpaBotProcessingRepository.updateServiceNowFlag(ctaskId, flag));
	}
	
	@Test
	void updateDetailStatusTest() {
		JdbcTemplate mockJdbc=mock(JdbcTemplate.class);
		when(jdbcTemplate.getJdbcTemplate()).thenReturn(mockJdbc);
		when(mockJdbc.update(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(5);
		Assertions.assertDoesNotThrow(() -> rpaBotProcessingRepository.updateDetailStatus("a","a","a","a"));
	}
	
	@Test
	void updateSummaryStatusTest1() {
		
		String ctaskid ="ctaskid"; 
		String status ="Failure";
		int retryCount = 5;
		JdbcTemplate mockJdbc=mock(JdbcTemplate.class);
		when(jdbcTemplate.getJdbcTemplate()).thenReturn(mockJdbc);
		when(mockJdbc.update(Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(5);
		Assertions.assertDoesNotThrow(() -> rpaBotProcessingRepository.updateSummaryStatus(ctaskid, status, retryCount));
	}
	
	@Test
	void insertReceivedMerakiUsecasePayloadTest1() {
		String ctaskId = "ctaskId";
		String ctaskType = "ctaskType";
		String inputPayload = "Inputpayload";
		String requester = "requester";
		String merakiScheduleTime = "scheduletime";
		when(jdbcTemplate.update(Mockito.anyString(),Mockito.anyMap())).thenThrow(NullPointerException.class);
		Assertions.assertDoesNotThrow(
				() -> rpaBotProcessingRepository.insertReceivedMerakiUsecasePayload(merakiScheduleTime, requester, inputPayload, ctaskType, ctaskId));
	}
	
	@Test
	void insertRPADetailsTest1() {
		String ctaskId = "ctaskId";
		String ctaskType = "ctaskType";
		String request_id = "id";
	    String deploymentid = "DeployId";
		String inpayload ="inpayload";
		String status = "Active";
		when(jdbcTemplate.update(Mockito.anyString(),Mockito.anyMap())).thenThrow(NullPointerException.class);
		Assertions.assertDoesNotThrow(() -> rpaBotProcessingRepository.insertRPADetails(status, inpayload, deploymentid, request_id, ctaskType, ctaskId));
	}
	
	@Test
	void updateScheduleDateTest1() {
		String ctaskId = "ctaskId";
		String schedule = "schedule";
		JdbcTemplate mockJdbc=mock(JdbcTemplate.class);
		when(jdbcTemplate.getJdbcTemplate()).thenReturn(mockJdbc);
		when(mockJdbc.update(Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(5);
		Assertions.assertDoesNotThrow(() -> rpaBotProcessingRepository.updateScheduleDate(schedule, ctaskId));
	}

	@Test
	void insertReceivedUsecasePayloadTest1() {
		String ctaskid = "id";
		String usecaseName="de";
		String inputPayload="de";
		String requester = "de";
		when(jdbcTemplate.update(Mockito.anyString(),Mockito.anyMap())).thenThrow(NullPointerException.class);
		Assertions.assertDoesNotThrow(() -> rpaBotProcessingRepository.insertReceivedUsecasePayload(requester, inputPayload, usecaseName, ctaskid));
	}
}

package com.albertsons.iot.fsa.management.test.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.albertsons.iot.fsa.management.kafka.constant.CommonConstants;
import com.albertsons.iot.fsa.management.kafka.model.RPABotAssignment;
import com.albertsons.iot.fsa.management.kafka.model.RPAGenericBody;
import com.albertsons.iot.fsa.management.kafka.model.RPAHeader;
import com.albertsons.iot.fsa.management.kafka.model.RPAServiceNowUpdateDetails;
import com.albertsons.iot.fsa.management.kafka.model.RPATaskSummary;
import com.albertsons.iot.fsa.management.kafka.repository.RPABotProcessingRepository;
import com.albertsons.iot.fsa.management.kafka.service.ConnectionServiceImpl;
import com.albertsons.iot.fsa.management.kafka.service.RpaServiceImpl;
import com.google.gson.Gson;

@ExtendWith(MockitoExtension.class)
class RpaServiceImplTest {

	@Mock	
	private RPABotProcessingRepository rPABotProcessingRepository;
	
	@Mock
	private ConnectionServiceImpl connectionServiceImpl;
	
	@InjectMocks
	private RpaServiceImpl rpaServiceImpl;

	@Test
	void handleRpaChangeEvent() {
		String rpaPayload = "{\"headers\":{\"requestId\":\"voicenamelatest1005\",\"ctaskRequester\":\"pbell48\",\"ctaskType\":\"VoiceNameChange\"},\"nameChange\":{\"nameChangeTo\":{\"type\":\"STRING\",\"string\":\"EIOT Automation\"},\"nameChangeFrom\":{\"type\":\"STRING\",\"string\":\"Test User\"},\"nameChangeToUserID\":{\"type\":\"STRING\",\"string\":\"pvUser\"},\"nameChangeFromUserID\":{\"type\":\"STRING\",\"string\":\"monaBSE6\"},\"phoneNumber\":{\"type\":\"STRING\",\"string\":\"6320081234\"},\"voicemailResetOption\":{\"type\":\"STRING\",\"string\":\"reset voice mail\"},\"voicemailUserTemplate\":{\"type\":\"STRING\",\"string\":\"BackstageUserTemplate\"},\"officeCode\":{\"type\":\"STRING\",\"string\":\"BSTG\"},\"emailNameChangeTo\":{\"type\":\"STRING\",\"string\":\"automation@albertsons.com\"}}}";
		char runVia = 'I';
		
		String url = "https://albertsons-dev.my.automationanywhere.digital/v1/authentication";
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(180270);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase("VoiceNameChange");
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");
		when(rPABotProcessingRepository.getRpaBotDetails(Mockito.anyString())).thenReturn(botAssignment);
		when(rPABotProcessingRepository.getConfigValue(CommonConstants.RPA_AUTHORIZATION_URL_KEY)).thenReturn(url);
		
	    assertDoesNotThrow(()->rpaServiceImpl.handleRpaChangeEvent(rpaPayload, runVia));
	}
	
	@Test
	void handleRpaChangeEventTestSchedule() {
		String payLoad = "{\"ctaskId\":\"phoneadd3006\",\"status\":\"Failure\",\"inputPayload\":\"{\\\"deviceMACAddress\\\":{\\\"type\\\":\\\"STRING\\\",\\\"string\\\":\\\"9D876B7A3410\\\"},\\\"agentID\\\":{\\\"type\\\":\\\"STRING\\\",\\\"string\\\":\\\"54532\\\"},\\\"voicemailOption\\\":{\\\"type\\\":\\\"STRING\\\",\\\"string\\\":\\\"true\\\"},\\\"requesterFullName\\\":{\\\"type\\\":\\\"STRING\\\",\\\"string\\\":\\\"sivamani 80\\\"},\\\"deviceModelNumber\\\":{\\\"type\\\":\\\"STRING\\\",\\\"string\\\":\\\"8851\\\"},\\\"voicemailUserTemplate\\\":{\\\"type\\\":\\\"STRING\\\",\\\"string\\\":\\\"voicemailusertemplate\\\"},\\\"requesterEmail\\\":{\\\"type\\\":\\\"STRING\\\",\\\"string\\\":\\\"sbala45\\\"}}\",\"pushRetryCount\":0,\"usecaseType\":\"PhoneAddition\",\"ctaskRequester\":\"sbala45\"}";
		char runVia = 'S';
		
		String url = "https://albertsons-dev.my.automationanywhere.digital/v1/authentication";
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(180270);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase("VoiceNameChange");
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");
		when(rPABotProcessingRepository.getRpaBotDetails(Mockito.anyString())).thenReturn(botAssignment);
		when(rPABotProcessingRepository.getConfigValue(CommonConstants.RPA_AUTHORIZATION_URL_KEY)).thenReturn(url);
		
	    assertDoesNotThrow(()->rpaServiceImpl.handleRpaChangeEvent(payLoad, runVia));
	}
	
	@Test
	void handleRpaChangeEventTestResponse() {
		String payLoad = "{\"requestId\":\"383a5ad745454e61\",\"usecaseName\":\"VoiceNameChange\",\"executionStatus\":\"Failure\",\"artifacts\":[\"\\\\\\\\earpprpaqast01.file.core.windows.net\\\\fileshare-rpa-coe\\\\RPA\\\\A360\\\\EIoT\\\\NameChange\\\\Logs\\\\12-01-2022\\\\ExecutionLog\\\\RITM1401377_383a5ad745454e61_10h19m00s.log\",\"\\\\\\\\earpprpaqast01.file.core.windows.net\\\\fileshare-rpa-coe\\\\RPA\\\\A360\\\\EIoT\\\\NameChange\\\\Logs\\\\12-01-2022\\\\ErrorLog\\\\RITM1401377_383a5ad745454e61_10h19m00s.log\"],\"responsePayload\":\"Failed to update details in Call Manager. Error: Cannot find window or application titled \\u0027Cisco Unified CM Console\\u0027 that was open during recording. \"}";
		char runVia = 'R';
		
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(180270);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase("VoiceNameChange");
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");
		
		RPAServiceNowUpdateDetails snowDetails = new RPAServiceNowUpdateDetails();
		snowDetails.setCtaskId("phoneadd3006");
		snowDetails.setPushRetryCount(1);
		
		when(rPABotProcessingRepository.toGetCtaskid(Mockito.any())).thenReturn(snowDetails);
		
	    assertDoesNotThrow(()->rpaServiceImpl.handleRpaChangeEvent(payLoad, runVia));
	}
	
	@Test
	void processRpaRequest() {
		RPAGenericBody rPAGenericBody = new RPAGenericBody();
		rPAGenericBody.setSchedule("scheduled");
		rPAGenericBody.setHeaders(null);

		RPAHeader rpaHeader = new RPAHeader();
		rpaHeader.setRequestId("task");
		assertThrows(NullPointerException.class,()->rpaServiceImpl.processRpaRequest(rPAGenericBody));
	}
	
	@Test
	void processRpaRequest1() {
		RPAGenericBody rPAGenericBody = new RPAGenericBody();
		rPAGenericBody.setSchedule("scheduled");
		RPAHeader rpaHeader = new RPAHeader();
		rpaHeader.setRequestId("task");
		rPAGenericBody.setHeaders(rpaHeader);
		
		when(rPABotProcessingRepository.updateScheduleDate(Mockito.anyString(), Mockito.anyString())).thenReturn(1);
		
		assertDoesNotThrow(()-> rpaServiceImpl.processRpaRequest(rPAGenericBody));
	}
	
	@Test
	void handleRpaChangeEventTestResponse1() {
		String payload = "{\"headers\":{\"requestId\":\"voicenamelatest1005\",\"ctaskRequester\":\"pbell48\",\"ctaskType\":\"VoiceNameChange\"},\"nameChange\":{\"nameChangeTo\":{\"type\":\"STRING\",\"string\":\"EIOT Automation\"},\"nameChangeFrom\":{\"type\":\"STRING\",\"string\":\"Test User\"},\"nameChangeToUserID\":{\"type\":\"STRING\",\"string\":\"pvUser\"},\"nameChangeFromUserID\":{\"type\":\"STRING\",\"string\":\"monaBSE6\"},\"phoneNumber\":{\"type\":\"STRING\",\"string\":\"6320081234\"},\"voicemailResetOption\":{\"type\":\"STRING\",\"string\":\"reset voice mail\"},\"voicemailUserTemplate\":{\"type\":\"STRING\",\"string\":\"BackstageUserTemplate\"},\"officeCode\":{\"type\":\"STRING\",\"string\":\"BSTG\"},\"emailNameChangeTo\":{\"type\":\"STRING\",\"string\":\"automation@albertsons.com\"}}}";
		char runVia = 'I';
		
		String url = "https://albertsons-dev.my.automationanywhere.digital/v1/authentication";
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(180270);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase("VoiceNameChange");
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");
		
	
		
		RPAServiceNowUpdateDetails snowDetails = new RPAServiceNowUpdateDetails();
		snowDetails.setCtaskId("phoneadd3006");
		snowDetails.setPushRetryCount(1);
		when(rPABotProcessingRepository.getRpaBotDetails(Mockito.any())).thenReturn(botAssignment);
		when(rPABotProcessingRepository.getConfigValue(CommonConstants.RPA_AUTHORIZATION_URL_KEY)).thenReturn(url);
		ResponseEntity<String> expectedResponseEntity2 = ResponseEntity.ok().body("{\"token\":\"token\"}");
		Mockito.lenient().when(connectionServiceImpl.getResponseFromPostAPI(Mockito.any(), Mockito.any())).thenReturn(expectedResponseEntity2);
		
	    assertDoesNotThrow(()->rpaServiceImpl.handleRpaChangeEvent(payload, runVia));
	}
	
	@Test
	void handleRpaChangeEventTestResponse2() {
		String payload = "{\"headers\":{\"requestId\":\"voicenamelatest1005\",\"ctaskRequester\":\"pbell48\",\"ctaskType\":\"VoiceNameChange\"},\"nameChange\":{\"nameChangeTo\":{\"type\":\"STRING\",\"string\":\"EIOT Automation\"},\"nameChangeFrom\":{\"type\":\"STRING\",\"string\":\"Test User\"},\"nameChangeToUserID\":{\"type\":\"STRING\",\"string\":\"pvUser\"},\"nameChangeFromUserID\":{\"type\":\"STRING\",\"string\":\"monaBSE6\"},\"phoneNumber\":{\"type\":\"STRING\",\"string\":\"6320081234\"},\"voicemailResetOption\":{\"type\":\"STRING\",\"string\":\"reset voice mail\"},\"voicemailUserTemplate\":{\"type\":\"STRING\",\"string\":\"BackstageUserTemplate\"},\"officeCode\":{\"type\":\"STRING\",\"string\":\"BSTG\"},\"emailNameChangeTo\":{\"type\":\"STRING\",\"string\":\"automation@albertsons.com\"}}}";
		char runVia = 'I';
		
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(0);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase("VoiceNameChange");
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");
		
	
		
		RPAServiceNowUpdateDetails snowDetails = new RPAServiceNowUpdateDetails();
		snowDetails.setCtaskId("phoneadd3006");
		snowDetails.setPushRetryCount(1);
		when(rPABotProcessingRepository.getRpaBotDetails(Mockito.any())).thenReturn(botAssignment);
		
	    assertDoesNotThrow(()->rpaServiceImpl.handleRpaChangeEvent(payload, runVia));
	}
	
	@Test
	void handleRpaChangeEventTestResponse3() {
		RPATaskSummary summaryDetails = new RPATaskSummary();
		summaryDetails.setCtaskId("ctaskid");
		summaryDetails.setCtaskRequester("ctaskRequester");
		summaryDetails.setInputPayload("inputPayload");
		summaryDetails.setPushRetryCount(0);
		summaryDetails.setScheduledTime("scheduledTime");
		summaryDetails.setStatus("Schedule");
		summaryDetails.setUsecaseType("usecase");
		summaryDetails.setUsecaseType("usecasetype");
		
		String payload=new Gson().toJson(summaryDetails);
		
		char runVia = 'S';
		
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(123);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase("MacWhiteListing");
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");
		
	
		
		RPAServiceNowUpdateDetails snowDetails = new RPAServiceNowUpdateDetails();
		snowDetails.setCtaskId("phoneadd3006");
		snowDetails.setPushRetryCount(1);
		when(rPABotProcessingRepository.getRpaBotDetails(Mockito.any())).thenReturn(botAssignment);
		
	    assertDoesNotThrow(()->rpaServiceImpl.handleRpaChangeEvent(payload, runVia));
	}
	
	

	@Test
	void handleRpaChangeEventTestResponse4() {
		RPATaskSummary summaryDetails = new RPATaskSummary();
		summaryDetails.setCtaskId("ctaskid");
		summaryDetails.setCtaskRequester("ctaskRequester");
		summaryDetails.setInputPayload("inputPayload");
		summaryDetails.setPushRetryCount(0);
		summaryDetails.setScheduledTime("scheduledTime");
		summaryDetails.setStatus("Schedule");
		summaryDetails.setUsecaseType("usecase");
		summaryDetails.setUsecaseType("usecasetype");
		
		String payload=new Gson().toJson(summaryDetails);
		
		char runVia = 'S';
		
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(0);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase("MacWhiteListing");
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");
		
	
		
		RPAServiceNowUpdateDetails snowDetails = new RPAServiceNowUpdateDetails();
		snowDetails.setCtaskId("phoneadd3006");
		snowDetails.setPushRetryCount(1);
		when(rPABotProcessingRepository.getRpaBotDetails(Mockito.any())).thenReturn(botAssignment);
		
	    assertDoesNotThrow(()->rpaServiceImpl.handleRpaChangeEvent(payload, runVia));
	}
	

	
	@Test
	void handleRpaChangeEventTestResponse5() {
		String payload = "{\"schedular\":\"abc\",\"headers\":{\"requestId\":\"voicenamelatest1005\",\"ctaskRequester\":\"pbell48\",\"ctaskType\":\"VoiceNameChange\"},\"nameChange\":{\"nameChangeTo\":{\"type\":\"STRING\",\"string\":\"EIOT Automation\"},\"nameChangeFrom\":{\"type\":\"STRING\",\"string\":\"Test User\"},\"nameChangeToUserID\":{\"type\":\"STRING\",\"string\":\"pvUser\"},\"nameChangeFromUserID\":{\"type\":\"STRING\",\"string\":\"monaBSE6\"},\"phoneNumber\":{\"type\":\"STRING\",\"string\":\"6320081234\"},\"voicemailResetOption\":{\"type\":\"STRING\",\"string\":\"reset voice mail\"},\"voicemailUserTemplate\":{\"type\":\"STRING\",\"string\":\"BackstageUserTemplate\"},\"officeCode\":{\"type\":\"STRING\",\"string\":\"BSTG\"},\"emailNameChangeTo\":{\"type\":\"STRING\",\"string\":\"automation@albertsons.com\"}}}";
		char runVia = 'I';
		
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(180270);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase("VoiceNameChange");
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");
		
	
		
		RPAServiceNowUpdateDetails snowDetails = new RPAServiceNowUpdateDetails();
		snowDetails.setCtaskId("phoneadd3006");
		snowDetails.setPushRetryCount(1);
		Mockito.lenient().when(rPABotProcessingRepository.updateScheduleDate(Mockito.any(),Mockito.any())).thenReturn(1);
		
	    assertDoesNotThrow(()->rpaServiceImpl.handleRpaChangeEvent(payload, runVia));
	}
	
	@Test
	void handleRpaChangeEventTestResponse6() {
		String payload = "{\"headers\":{\"requestId\":\"voicenamelatest1005\",\"ctaskRequester\":\"pbell48\",\"ctaskType\":\"MacWhiteListing\"},\"mwInput\":{}}";
		char runVia = 'I';
		
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(180270);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase("MacWhiteListing");
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");
		
	
		
		RPAServiceNowUpdateDetails snowDetails = new RPAServiceNowUpdateDetails();
		snowDetails.setCtaskId("phoneadd3006");
		snowDetails.setPushRetryCount(1);
		when(rPABotProcessingRepository.getRpaBotDetails(Mockito.any())).thenReturn(botAssignment);
		when(rPABotProcessingRepository.getRpaBotDetails(Mockito.anyString())).thenReturn(botAssignment);
		
	    assertDoesNotThrow(()->rpaServiceImpl.handleRpaChangeEvent(payload, runVia));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"FaxToMail","VoiceJabber","PhoneAddition","PhoneDeletion","VoicemailAddDelete"})
	void handleRpaChangeEventTestResponse7(String usecase) {
		String payload = "{\"headers\":{\"requestId\":\"voicenamelatest1005\",\"ctaskRequester\":\"pbell48\",\"ctaskType\":"+usecase+"},"+usecase+":{\"nameChangeTo\":{\"type\":\"STRING\",\"string\":\"EIOT Automation\"},\"nameChangeFrom\":{\"type\":\"STRING\",\"string\":\"Test User\"},\"nameChangeToUserID\":{\"type\":\"STRING\",\"string\":\"pvUser\"},\"nameChangeFromUserID\":{\"type\":\"STRING\",\"string\":\"monaBSE6\"},\"phoneNumber\":{\"type\":\"STRING\",\"string\":\"6320081234\"},\"voicemailResetOption\":{\"type\":\"STRING\",\"string\":\"reset voice mail\"},\"voicemailUserTemplate\":{\"type\":\"STRING\",\"string\":\"BackstageUserTemplate\"},\"officeCode\":{\"type\":\"STRING\",\"string\":\"BSTG\"},\"emailNameChangeTo\":{\"type\":\"STRING\",\"string\":\"automation@albertsons.com\"}}}";
		char runVia = 'I';
		
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(180270);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase(usecase);
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");

	
		
		RPAServiceNowUpdateDetails snowDetails = new RPAServiceNowUpdateDetails();
		snowDetails.setCtaskId("phoneadd3006");
		snowDetails.setPushRetryCount(1);
		when(rPABotProcessingRepository.getRpaBotDetails(Mockito.any())).thenReturn(botAssignment);
		
	    assertDoesNotThrow(()->rpaServiceImpl.handleRpaChangeEvent(payload, runVia));
	}
	

	@Test
	void handleRpaChangeEventTestResponse8() {
		char runVia = 'I';
		
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(180270);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase("MerakiFirewall");
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");
		String payload = "{\"headers\":{\"ctaskId\":\"string\",\"ctaskRequester\":\"string\",\"ctaskType\":\"MerakiFirewall\"},\"merakiFirewallPayload\":{\"l3Ecert\":{\"filePath\":\"string\",\"schedule\":[{\"list\":[\"org1\",\"org2\"],\"time\":\"2022-11-0104:51:49.851038+00\"}]},\"l3Prod\":{\"filePath\":\"string\",\"schedule\":[{\"list\":[\"string\"],\"time\":\"string\"}]},\"s2sEcert\":{\"filePath\":\"string\",\"schedule\":[{\"list\":[\"string\"],\"time\":\"string\"}]},\"s2sProd\":{\"filePath\":\"string\",\"schedule\":[{\"list\":[\"string\"],\"time\":\"string\"}]}}}";

	
		
		RPAServiceNowUpdateDetails snowDetails = new RPAServiceNowUpdateDetails();
		snowDetails.setCtaskId("phoneadd3006");
		snowDetails.setPushRetryCount(1);
		when(rPABotProcessingRepository.getRpaBotDetails(Mockito.any())).thenReturn(botAssignment);
	    assertDoesNotThrow(()->rpaServiceImpl.handleRpaChangeEvent(payload, runVia));
	}
	
	
	@Test
	void handleRpaChangeEventTestResponse9() {
		char runVia = 'L';
		
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(180270);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase("MerakiFirewall1");
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");

		String payload="{\"requestId\":\"MerakiFirewall1\",\"usecaseName\":\"MerakiFirewall1\",\"executionStatus\":\"MerakiFirewall1\"}";
		
		RPAServiceNowUpdateDetails snowDetails = new RPAServiceNowUpdateDetails();
		snowDetails.setCtaskId("phoneadd3006");
		snowDetails.setPushRetryCount(1);
//		when(rPABotProcessingRepository.getRpaBotDetails(Mockito.any())).thenReturn(botAssignment);
		when(rPABotProcessingRepository.toGetCtaskid(Mockito.any())).thenReturn(snowDetails);
		
	    assertDoesNotThrow(()->rpaServiceImpl.handleRpaChangeEvent(payload, runVia));
	}
	

	@SuppressWarnings("unchecked")
	@Test
	void handleRpaChangeEventTestResponse10() {
		RPATaskSummary summaryDetails = new RPATaskSummary();
		summaryDetails.setCtaskId("ctaskid");
		summaryDetails.setCtaskRequester("ctaskRequester");
		summaryDetails.setInputPayload("inputPayload");
		summaryDetails.setPushRetryCount(0);
		summaryDetails.setScheduledTime("scheduledTime");
		summaryDetails.setStatus("Schedule");
		summaryDetails.setUsecaseType("usecase");
		summaryDetails.setUsecaseType("usecasetype");
		
		String payload=new Gson().toJson(summaryDetails);
		
		char runVia = 'S';
		
		String url = "https://albertsons-dev.my.automationanywhere.digital/v1/authentication";
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(10);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase("MacWhiteListing");
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");
		
	
		
		RPAServiceNowUpdateDetails snowDetails = new RPAServiceNowUpdateDetails();
		snowDetails.setCtaskId("phoneadd3006");
		snowDetails.setPushRetryCount(1);
		when(rPABotProcessingRepository.getRpaBotDetails(Mockito.any())).thenReturn(botAssignment);
		when(rPABotProcessingRepository.getRpaBotDetails(Mockito.anyString())).thenReturn(botAssignment);
		Mockito.lenient().when(rPABotProcessingRepository.getConfigValue(CommonConstants.RPA_AUTHORIZATION_URL_KEY)).thenReturn(url);
		ResponseEntity<String> expectedResponseEntity2 = ResponseEntity.ok().body("{\"token\":\"token\"}");
		ResponseEntity<String> expectedResponseEntity1 = ResponseEntity.ok().body("{\"deploymentId\":\"token\"}");
		Mockito.lenient().when(connectionServiceImpl.getResponseFromPostAPI(Mockito.any(), Mockito.any())).thenReturn(expectedResponseEntity2,expectedResponseEntity1);
		Mockito.lenient().when(rPABotProcessingRepository.updateSummaryStatus(Mockito.any(), Mockito.any(), Mockito.anyInt())).thenReturn(1);
		Mockito.lenient().doNothing().when(rPABotProcessingRepository).insertRPADetails(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
				Mockito.any(), Mockito.any());
	    assertDoesNotThrow(()->rpaServiceImpl.handleRpaChangeEvent(payload, runVia));
	}
	
	@Test
	void handleRpaChangeEventTestResponse11() {
		RPATaskSummary summaryDetails = new RPATaskSummary();
		summaryDetails.setCtaskId("ctaskid");
		summaryDetails.setCtaskRequester("ctaskRequester");
		summaryDetails.setInputPayload("inputPayload");
		summaryDetails.setPushRetryCount(0);
		summaryDetails.setScheduledTime("scheduledTime");
		summaryDetails.setStatus("Schedule");
		summaryDetails.setUsecaseType("usecase");
		summaryDetails.setUsecaseType("usecasetype");
		
		String payload=new Gson().toJson(summaryDetails);
		
		char runVia = 'S';
		
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(10);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase("MacWhiteListing");
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");
		
	
		
		RPAServiceNowUpdateDetails snowDetails = new RPAServiceNowUpdateDetails();
		snowDetails.setCtaskId("phoneadd3006");
		snowDetails.setPushRetryCount(1);
		when(rPABotProcessingRepository.getRpaBotDetails(Mockito.any())).thenReturn(botAssignment);
		when(rPABotProcessingRepository.getRpaBotDetails(Mockito.anyString())).thenReturn(botAssignment);
		Mockito.lenient().when(rPABotProcessingRepository.getConfigValue(CommonConstants.RPA_AUTHORIZATION_URL_KEY)).thenThrow(NullPointerException.class);
	    assertDoesNotThrow(()->rpaServiceImpl.handleRpaChangeEvent(payload, runVia));
	}
	
	@Test
	void handleRpaChangeEventTestResponse12() {
		char runVia = 'L';
		RPABotAssignment botAssignment = new RPABotAssignment();
		botAssignment.setApiGenericKey("K8{S4nlbHf|8wt<<oi{NInP:Cd^NB5~7X6uZ85si");
		botAssignment.setBotId(180270);
		botAssignment.setDeleted(false);
		botAssignment.setUseCase("MerakiFirewall1");
		botAssignment.setUserId("2843");
		botAssignment.setUserKey("asang23");

		String payload="{\"requestId\":\"MerakiFirewall1\",\"usecaseName\":\"MerakiFirewall1\",\"executionStatus\":\"MerakiFirewall1\"}";
		
		RPAServiceNowUpdateDetails snowDetails = new RPAServiceNowUpdateDetails();
		snowDetails.setCtaskId("phoneadd3006");
		snowDetails.setPushRetryCount(1);
//		when(rPABotProcessingRepository.getRpaBotDetails(Mockito.any())).thenReturn(botAssignment);
		when(rPABotProcessingRepository.toGetCtaskid(Mockito.any())).thenReturn(snowDetails);
		when(connectionServiceImpl.updateServiceNowCtask(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
		
	    assertDoesNotThrow(()->rpaServiceImpl.handleRpaChangeEvent(payload, runVia));
	}
	

	@Test
	void handleRpaChangeEventTestResponse13() {
		char runVia = 'I';
		String payload = "}{\"headers\":{\"ctaskId\":\"string\",\"ctaskRequester\":\"string\",\"ctaskType\":\"MerakiFirewall\"},\"merakiFirewallPayload\":{\"l3Ecert\":{\"filePath\":\"string\",\"schedule\":[{\"list\":[\"org1\",\"org2\"],\"time\":\"2022-11-0104:51:49.851038+00\"}]},\"l3Prod\":{\"filePath\":\"string\",\"schedule\":[{\"list\":[\"string\"],\"time\":\"string\"}]},\"s2sEcert\":{\"filePath\":\"string\",\"schedule\":[{\"list\":[\"string\"],\"time\":\"string\"}]},\"s2sProd\":{\"filePath\":\"string\",\"schedule\":[{\"list\":[\"string\"],\"time\":\"string\"}]}}}";
		assertDoesNotThrow(()->rpaServiceImpl.handleRpaChangeEvent(payload, runVia));
		
	}
		
	    
	
}

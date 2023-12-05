package com.albertsons.iot.fsa.management.kafka.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.albertsons.iot.fsa.management.kafka.constant.CommonConstants;
import com.albertsons.iot.fsa.management.kafka.model.RPABotAssignment;
import com.albertsons.iot.fsa.management.kafka.model.RPAGenericResponsePayload;
import com.albertsons.iot.fsa.management.kafka.model.RPAServiceNowUpdateDetails;
import com.albertsons.iot.fsa.management.kafka.model.RpaMerakiBotStatus;
import com.albertsons.iot.fsa.management.kafka.model.RpaMerakiSubtaskStatus;

import lombok.extern.slf4j.Slf4j;

/**
 * To perform database operation for RPA related use case.
 * 
 * @author 2187949
 *
 */
@Slf4j
@Repository
public class RPABotProcessingRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public String getConfigValue(String key) {

		log.info("RPA getting config value for key= " + key);
		try {
			return jdbcTemplate.getJdbcTemplate().queryForObject(CommonConstants.RPA_CONFIG_DETIAIL, String.class, key);
		} catch (Exception e) {
			log.error("Error, RPA getting config value for key= " + e.getMessage());
			return "";
		}

	}

	public void insertReceivedUsecasePayload(String ctaskid, String usecaseName, String inputPayload,
			String requester) {

		log.info("insert received data into rpa table started");
		try {
			Map<String, String> namedParameters = new HashMap<>();
			namedParameters.put(CommonConstants.RPA_CTASK_ID, ctaskid);
			namedParameters.put("usecase", usecaseName);
			namedParameters.put("inputpayload", inputPayload);
			namedParameters.put("requester", requester);
			jdbcTemplate.update(CommonConstants.RPA_INSERT_INTO_SUMMARY, namedParameters);

			log.info("Inserted into rpa table successfully");
		} catch (Exception e) {
			log.error("RPA Error while inserting data into summary table= " + e.getMessage());
		}

	}

	public int updateSummaryStatus(String ctaskid, String status, int retryCount) {

		log.info("update status for summary table started ");
		int updateCount = 0;
		try {
			// count 5 indicate keep retry count as it is, don't increment.In response we
			// are not getting count to update so 5 indicate response call.
			if (retryCount == 5) {
				updateCount = jdbcTemplate.getJdbcTemplate()
						.update(CommonConstants.RPA_UPDATE_BOT_RESPONSE_IN_SUMMARY_TABLE, status, ctaskid);
				return updateCount;
			}

			updateCount = jdbcTemplate.getJdbcTemplate().update(CommonConstants.RPA_UPDATE_STATUS_IN_SUMMARY_TABLE,
					status, retryCount, ctaskid);
		} catch (Exception e) {
			log.error("RPA Exception occurred while updating summary table= " + e.getMessage());
		}

		return updateCount;
	}

	public void insertRPADetails(String ctaskid, String status, String requestid, String deploymentid, String inpayload,
			String ctaskType) {

		log.info("inserting data into rpa detail table started");
		try {

			Map<String, String> namedParameters = new HashMap<>();
			namedParameters.put(CommonConstants.RPA_CTASK_ID, ctaskid);
			namedParameters.put("status", status);
			namedParameters.put("requestid", requestid);
			namedParameters.put("deploymentid", deploymentid);
			namedParameters.put("inputpayload", inpayload);
			namedParameters.put("ctasktype", ctaskType);
			jdbcTemplate.update(CommonConstants.RPA_INSERT_INTO_DETAIL, namedParameters);
		} catch (Exception e) {
			log.error("RPA Exception occurred while inserting data into detail table= " + e.getMessage());
		}

	}

	public int updateDetailStatus(String ctaskid, String status, String responsePayload, String reqId) {

		log.info("updating response in rpa detail table started");
		return jdbcTemplate.getJdbcTemplate().update(CommonConstants.RPA_UPDATE_BOT_RESPONSE_IN_DETAIL_TABLE, status,
				responsePayload, ctaskid, reqId);

	}

	public int updateScheduleDate(String ctaskid, String schedule) {

		log.info("updating scheduling date and time in rpa summary table started");
		int count = 0;
		try {
			count = jdbcTemplate.getJdbcTemplate().update(CommonConstants.RPA_UPDATE_SCHEDULE_TIMESTAMP, schedule,
					ctaskid);

			log.info("updating scheduling date and time in rpa summary table completed");
		} catch (Exception e) {
			log.error("RPA Exception occurred while updating scheule timesamp in table= " + e.getMessage());
		}

		return count;

	}

	public RPABotAssignment getRpaBotDetails(String usecaseName) {

		log.info("bot assignmnet details started to fetch for usecase name = " + usecaseName);
		RPABotAssignment rpaBotAssignment = new RPABotAssignment();

		try {
			rpaBotAssignment = jdbcTemplate.getJdbcTemplate().queryForObject(
					CommonConstants.RPA_GET_BOT_ASSIGNMENT_FOR_USECASE,
					new BeanPropertyRowMapper<RPABotAssignment>(RPABotAssignment.class), usecaseName);

			log.info("details for bot assignment for usecase  " + usecaseName + " is fetched");
		} catch (Exception e) {
			log.error("RPA Exception occurred when getting RPATaskSummary object from table= " + e.getMessage());
		}
		return rpaBotAssignment;
	}

	public void insertReceivedMerakiUsecasePayload(String ctaskId, String ctaskType, String inputPayload,
			String requester, String merakiScheduleTime) {

		log.info("insert received RPA Meraki data into rpa table started");
		try {
			Map<String, String> namedParameters = new HashMap<>();
			namedParameters.put("ctaskid1", ctaskId);
			namedParameters.put("usecase", ctaskType);
			namedParameters.put("inputpayload1", inputPayload);
			namedParameters.put("requester", requester);
			namedParameters.put("scheduletime", merakiScheduleTime);
			jdbcTemplate.update(CommonConstants.RPA_INSERT_MERAKI_DETAILS_INTO_SUMMARY, namedParameters);

			log.info("Inserted into rpa table successfully");
		} catch (Exception e) {
			log.error("RPA Error while inserting RPA Meraki data into summary table= " + e.getMessage());
		}

	}

	public RPAServiceNowUpdateDetails toGetCtaskid(RPAGenericResponsePayload rPAGenericResponsePayload) {
		log.info("to get ctaskid from table started");
		RPAServiceNowUpdateDetails ctaskId = new RPAServiceNowUpdateDetails();
		try {
				Map<String, String> namedParameters = new HashMap<>();
				namedParameters.put("requestId", rPAGenericResponsePayload.getRequestId());
				List<RPAServiceNowUpdateDetails> ctaskIdList = jdbcTemplate.query(CommonConstants.RPA_GET_CTASK_ID, namedParameters,BeanPropertyRowMapper.newInstance(RPAServiceNowUpdateDetails.class));
			if(!ctaskIdList.isEmpty()) {
				ctaskId = ctaskIdList.get(0);
			}
		} catch (Exception e) {
			log.error("RPA Exception occurred while getting ctask from table= "+e.getMessage());
		}
		return ctaskId;
	}

	public int updateServiceNowFlag(String ctaskId, boolean flag) {
		log.info("updating service flag in rpa summary table started");
		int count = 0;
		try {
			count = jdbcTemplate.getJdbcTemplate()
					.update("update rpa_task_summary set service_update_flag = ? where ctaskid = ?", flag, ctaskId);

			log.info("updating sservice flag in rpa summary table completed");
		} catch (Exception e) {
			log.error("RPA Exception occurred while updating service flag in table= " + e.getMessage());
		}

		return count;
	}

	public String getUsecaseType(String key) {

		log.info("RPA get use case type from deployment id");
		try {
			return jdbcTemplate.getJdbcTemplate().queryForObject(CommonConstants.RPA_GET_USECASE_TYPE, String.class,
					key);
		} catch (Exception e) {
			log.error("Error, RPA get use case type from deployment id= " + e.getMessage());
			return "";
		}

	}

	public String getRequestId(String key) {

		log.info("RPA get Request id from deployment id");
		try {
			return jdbcTemplate.getJdbcTemplate().queryForObject(CommonConstants.RPA_GET_REQUEST_ID, String.class, key);
		} catch (Exception e) {
			log.error("Error,RPA get Request id from deployment id= " + e.getMessage());
			return "";
		}

	}

	public RpaMerakiBotStatus getRpaMerakiBotStatus(String subTask) {
		log.info("RPA to get Meraki RIT Progress started");
		RpaMerakiBotStatus rpaMerakiBotStatus = new RpaMerakiBotStatus();
		try {

			rpaMerakiBotStatus = jdbcTemplate.getJdbcTemplate().queryForObject(
					CommonConstants.RPA_GET_MERAKI_RPA_RIT_PROGRESS,
					new BeanPropertyRowMapper<RpaMerakiBotStatus>(RpaMerakiBotStatus.class), subTask);
		} catch (Exception e) {
			log.error("RPA Exception occurred while getting Meraki RIT Progress");
		}
		return rpaMerakiBotStatus;
	}

	public List<RpaMerakiSubtaskStatus> getMerakiSubtaskStatus(String subTask) {

		log.info("Rpa Meraki Firewall process to get all subtask status for = " + subTask);
		List<RpaMerakiSubtaskStatus> getMerakiSubtaskStatus = new ArrayList<>();
		String ctaskId = subTask + "%";
		Map<String, String> param = new HashMap<>();
		param.put(CommonConstants.RPA_CTASK_ID, ctaskId);
		try {
			getMerakiSubtaskStatus = jdbcTemplate.query(CommonConstants.GET_RPA_MERAKI_SUBTASK_STATUS, param,
					BeanPropertyRowMapper.newInstance(RpaMerakiSubtaskStatus.class));
		} catch (Exception e) {
			log.error("RPA exception occured while fetching Meraki Firewall all subtasks status from table "
					+ e.getMessage());

		}
		return getMerakiSubtaskStatus;
	}

}

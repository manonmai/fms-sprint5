package com.cg.fmssprint5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fmssprint5.bean.ScheduleDTO;
import com.cg.fmssprint5.exception.FRSException;
import com.cg.fmssprint5.service.FlightScheduleOperations;
import com.cg.fmssprint5.utility.ExceptionMessages;

/**
 * Controller for schedule operations
 * 
 * @author seeta
 *
 */
@RestController
@RequestMapping(value = "/schedule")
public class SheduleDetailController {
	@Autowired
	FlightScheduleOperations scheduleOperations;

	public void setScheduleOperations(FlightScheduleOperations scheduleOperations) {
		this.scheduleOperations = scheduleOperations;
	}

	/**
	 * to modify departure date
	 * 
	 * @param scheduledto
	 * @return boolean
	 */
	@RequestMapping(value = "/modifyDDate", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public boolean modifyDepartureDate(@Valid @RequestBody ScheduleDTO dto) throws FRSException {
		boolean flag = false;
			flag = scheduleOperations.modifyDepartureDate(dto);
			if(dto.getDepartureDate()==null)
			{
				throw new FRSException(ExceptionMessages.NODATE);
			}
		
		return flag;
	}

	/**
	 * to modify arrival date
	 * 
	 * @param scheduledto
	 * @return boolean
	 */
	@RequestMapping(value = "/modifyADate", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public boolean modifyArrivalDate(@Valid @DateTimeFormat(pattern = "dd-MM-YYYY") @RequestBody ScheduleDTO dto) {
		boolean flag = false;
			flag = scheduleOperations.modifyArrivalDate(dto);
			if(dto.getArrivalDate()==null)
			{
				throw new FRSException(ExceptionMessages.NODATE);
			}
		return flag;
	}

	/**
	 * to modify arrival time
	 * 
	 * @param scheduledto
	 * @return boolean
	 */
	@RequestMapping(value = "/modifyATime", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public boolean modifyArrivalTime(@Valid @RequestBody ScheduleDTO dto) {
		boolean flag;
		
			flag = scheduleOperations.modifyArrivalTime(dto);
			if(dto.getArrivalDate()==null)
			{
				throw new FRSException(ExceptionMessages.NOTIME);
			}
		return flag;

	}

	/**
	 * to modify departure time
	 * 
	 * @param scheduledto
	 * @return boolean
	 */
	@RequestMapping(value = "/modifyDTime", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public boolean modifyDepartureTime(@Valid @RequestBody ScheduleDTO dto) {
		boolean flag;
			flag = scheduleOperations.modifyDepartureTime(dto);
			if(dto.getDepartureDate()==null)
			{
				throw new FRSException(ExceptionMessages.NODATE);
			}
			
		
		return flag;

	}
	@RequestMapping(value = "/modifyPrice", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public boolean modifyPrice(@Valid @RequestBody ScheduleDTO dto) throws FRSException {
		boolean flag = false;
			flag = scheduleOperations.modifyPrice(dto);
			if(dto.getPrice()<1000)
			{
				throw new FRSException(ExceptionMessages.CORRECTPRICE);
			}
		
		return flag;
	}

	/**
	 * to view schedule details
	 * 
	 * @return list
	 */
	@RequestMapping(value = "/viewSchedule", method = RequestMethod.GET, produces = "application/json")
	public List<ScheduleDTO> viewScheduleDetails() {
		List<ScheduleDTO> scheduleList = new ArrayList<ScheduleDTO>();
			scheduleList = scheduleOperations.viewScheduleDetails();
			if(scheduleList==null)
			{
				throw new FRSException(ExceptionMessages.LISTEMPTY);
			}
		return scheduleList;
	}

}

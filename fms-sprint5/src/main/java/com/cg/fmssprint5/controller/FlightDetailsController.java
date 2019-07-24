package com.cg.fmssprint5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fmssprint5.bean.FlightDTO;
import com.cg.fmssprint5.exception.FRSException;
import com.cg.fmssprint5.service.FlightDetailOperations;
import com.cg.fmssprint5.utility.ExceptionMessages;


@RestController
@RequestMapping(value = "/flight")
public class FlightDetailsController {
	@Autowired
	FlightDetailOperations flightDetailOperations;

	/**
	 * to view list of flights
	 * 
	 * @return flightList
	 */
	@GetMapping(value = "/viewDetails", produces = "application/json")
	public List<FlightDTO> viewFlightDetails() throws FRSException{
		List<FlightDTO> flightList = new ArrayList<FlightDTO>();
		flightList = flightDetailOperations.viewFlightDetails();
		if(flightList==null)
		{
			throw new FRSException(ExceptionMessages.LISTEMPTY);
		}
		return flightList;

	}

	/**
	 * to modify capacity
	 * 
	 * @param Flightdto
	 * @return boolean
	 */
	@RequestMapping(value = "/modifyCapacity", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public boolean modifyCapacity(@Valid @RequestBody FlightDTO dto)throws FRSException {
		boolean flag;
			flag = flightDetailOperations.modifyFlightDetails(dto);
			if(dto.getCapacity()==0)
			{
				throw new FRSException(ExceptionMessages.CAPACITY);
			}
		return flag;

	}

}

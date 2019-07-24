package com.cg.fmssprint5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fmssprint5.FmsSprint5Application;
import com.cg.fmssprint5.bean.RouteDTO;
import com.cg.fmssprint5.exception.FRSException;
import com.cg.fmssprint5.service.FlightRouteOperations;
import com.cg.fmssprint5.utility.ExceptionMessages;

@RestController
@RequestMapping(value = "/route")
public class RouteController {
	private final Logger LOGGER = LoggerFactory.getLogger(FmsSprint5Application.class);

	@Autowired
	FlightRouteOperations routeOperations;

	/**
	 * to view routes
	 * 
	 * @return boolean
	 */
	@GetMapping(value = "/viewlist", produces = "application/json")
	public List<RouteDTO> viewRouteDetails() throws FRSException {
		List<RouteDTO> routeList = new ArrayList<RouteDTO>();
		LOGGER.info("viewlist method has started");
			routeList = routeOperations.viewRouteDetails();
			if(routeList==null)
			{
			throw new FRSException(ExceptionMessages.LISTEMPTY);
			}
			LOGGER.info("viewlist method has ended");
		return routeList;
	}

	/**
	 * to modify source
	 * 
	 * @param dto
	 * @return boolean
	 */
	@PutMapping(value = "/modifySource", consumes = "application/json", produces = "application/json")
	public boolean modifySource(@Valid @RequestBody RouteDTO dto) {

		boolean flag;
		LOGGER.info("viewlist method has started");
			flag = routeOperations.modifySource(dto);
		if(dto.getSource()==null)
		{
			throw new FRSException(ExceptionMessages.NOPLACE);
		}
		return flag;
	}
	/**
	 * to modify destination
	 * 
	 * @param dto
	 * @return boolean
	 */
	@PutMapping( value = "/modifyDestination", consumes = "application/json", produces = "application/json")
	public boolean modifyDestination(@Valid @RequestBody RouteDTO dto) {
		boolean flag;
		LOGGER.info("modify destination method has started");
			flag = routeOperations.modifyDestination(dto);
		if(dto.getDestination()==null)
		{
			throw new FRSException(ExceptionMessages.NOPLACE);
		}
		LOGGER.info("modify destination method has ended");
		return flag;
	}
	
	@PutMapping(value="/getLeastPrice",consumes = "application/json", produces = "application/json")
	public Object[] getMinPrice(@Valid @RequestBody RouteDTO dto) throws FRSException{
		LOGGER.info("getMinPrice method has started");
		if(dto.getSource()==null || dto.getDestination()==null)
		{
			throw new FRSException(ExceptionMessages.NOVALUE);
		}
		return routeOperations.getMinPrice(dto);
	}
}

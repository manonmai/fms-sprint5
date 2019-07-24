package com.cg.fmssprint5.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cg.fmssprint5.bean.FlightDTO;
import com.cg.fmssprint5.bean.RouteDTO;
import com.cg.fmssprint5.bean.ScheduleDTO;
import com.cg.fmssprint5.dao.FlightDetailDao;
import com.cg.fmssprint5.exception.FRSException;
import com.cg.fmssprint5.utility.Queries;

/**
 * daoImpl class of flight details
 * 
 * @author seeta
 *
 */
@Repository
@Transactional
public class FlightDetailDaoImpl implements FlightDetailDao {
	/**
	 * method to get flight details
	 */
	@PersistenceContext
	private EntityManager entityManager;

	public List<FlightDTO> getFlightList() throws FRSException {
		String query1 = Queries.GETFLIGHTLIST;
	 Query query = entityManager.createQuery(query1);
		List<FlightDTO> flightlist = query.getResultList();
		return flightlist;
	}

	/**
	 * method to modify capacity
	 */

	public boolean modifyCapacity(FlightDTO flight) throws FRSException {

		int flightId;
		int capacity;
		flightId = flight.getFlightId();
		capacity = flight.getCapacity();
		boolean flag = false;
		FlightDTO flight1 = entityManager.find(FlightDTO.class, flightId);
		if (flight1 != null) {
			flight1.setCapacity(capacity);
			flag = true;
		}
		return flag;
	}
	
}
//	public int getMinPrice(FlightDTO flight) throws FRSException {
//		// TODO Auto-generated method stub
//		ScheduleDTO dto = new ScheduleDTO();
//		RouteDTO route = new RouteDTO();
//		int price;
//		RouteDTO route1 = flight.getRouteDTO();
//		String source = route.getSource();
//		String destination = route.getDestination();
//		String sql = Queries.GETMINPRICE;
//		Query query = entityManager.createQuery(sql);
//		price= (int) query.getSingleResult();
//		System.out.println(price);
	//	return 0;
//	}

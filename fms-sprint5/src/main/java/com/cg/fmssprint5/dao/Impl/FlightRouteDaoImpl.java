package com.cg.fmssprint5.dao.Impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.cg.fmssprint5.dao.FlightRouteDao;
import com.cg.fmssprint5.exception.FRSException;
import com.cg.fmssprint5.utility.Queries;

/**
 * DaoImpl class for flight routes
 * 
 * @author trainee
 *
 */
@Repository
@Transactional
public class FlightRouteDaoImpl implements FlightRouteDao {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 
	 * method to get route details
	 *
	 */

	public List<RouteDTO> getRouteList() throws FRSException {

		String query1 = Queries.GETROUTELIST;
		// Query route=entityManager.createQuery("Select route from RouteDTO route");
		TypedQuery<RouteDTO> route = (TypedQuery<RouteDTO>) entityManager.createQuery(query1);
		List<RouteDTO> routelist = route.getResultList();
		System.out.println(routelist);
		return routelist;
	}

	/**
	 * method to modify source
	 */

	public boolean modifySource(RouteDTO route) throws FRSException {
		int routeId;
		String source;
		routeId = route.getRouteId();
		source = route.getSource();
		// TODO Auto-generated method stub
		boolean flag = false;
		RouteDTO route1 = entityManager.find(RouteDTO.class, routeId);
		
	if (route1 != null && route.getSource()!=null)
	{
			route1.setSource(source);
			flag = true;
	}
		return flag;
	}

	/**
	 * method to modify destination
	 */

	public boolean modifyDestination(RouteDTO route) throws FRSException {
		int routeId;
		String destination;
		routeId = route.getRouteId();
		destination = route.getDestination();
		boolean flag = false;
		RouteDTO route1 = entityManager.find(RouteDTO.class, routeId);
		if (route1 != null) {
			route1.setDestination(destination);
			flag = true;
		}
		return flag;
	}

	public Object[] getMinPrice(RouteDTO dto) throws FRSException {
		System.out.println(dto.getSource());
		System.out.println(dto.getDestination());
		TypedQuery<Object[]> query =  entityManager.createQuery(" select schedule.departureDate, schedule.price from ScheduleDTO schedule  where schedule.price=(select min(s.price) from ScheduleDTO s where s.scheduleId = (SELECT f.scheduleDTO.scheduleId FROM FlightDTO f WHERE f.routeDTO.routeId =(SELECT r.routeId from RouteDTO r WHERE r.source =: source1 AND r.destination =: destination1)))",Object[].class);
		//Query query = entityManager.createQuery(" select schedule.departureDate, schedule.price from ScheduleDTO schedule  where schedule.price=(select min(s.price) from ScheduleDTO s where s.scheduleId = (SELECT f.scheduleDTO.scheduleId FROM FlightDTO f WHERE f.routeDTO.routeId =(SELECT r.routeId from RouteDTO r WHERE r.source =: source1 AND r.destination =: destination1))) ");
		query.setParameter("source1", dto.getSource());
		query.setParameter("destination1", dto.getDestination());
	    Object[] result =   query.getSingleResult();
		//List result = query.getResultList();
	    System.out.println(result[0].toString());
		return result;
		}
}
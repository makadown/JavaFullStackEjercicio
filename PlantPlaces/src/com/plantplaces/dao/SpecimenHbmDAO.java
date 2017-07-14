package com.plantplaces.dao;

import java.util.Collections;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.plantplaces.dto.Specimen;

/**
 * A Hibernate persistence implementation of our specimen DAO.
 * @author Win7
 *
 */
@SuppressWarnings("all")
@Named
public class SpecimenHbmDAO /*extends PlantPlacesHbmDAO<Specimen>*/ implements ISpecimenDAO {
	

	@Override 
	public List<Specimen> fetchByPlantId(int plantId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		// create the query 
		Query query = session.createQuery("from Specimen where plantId = :plantId");
		query.setParameter("plantId", plantId);
		// query.setProperties(plant); 
		List<Specimen> list = query.list();
		
		
		List<Specimen> specimens = Collections.checkedList(list, Specimen.class);
		
		return specimens;
	}

	public void insert(Specimen specimen) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(specimen);
		
		session.getTransaction().commit();
	}
	
	/*
	@Override
	public void insert(Session session, Specimen dto) throws Exception {
		// TODO Auto-generated method stub
		session.save(dto);
	}*/

	@Override
	public void save(Specimen specimen) throws Exception {
		insert(specimen);
		
	}

}
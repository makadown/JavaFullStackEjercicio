package com.plantplaces.dao;


import java.util.List;

import org.hibernate.Session;

import com.plantplaces.dto.Specimen;

public interface ISpecimenDAO {
	void insert(Session session, Specimen specimen) throws Exception;	 
	List<Specimen> fetchByPlantId(int plantId);
	public void save(Specimen specimen) throws Exception;
}

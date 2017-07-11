package com.plantplaces.dao;

import java.util.List;

import org.hibernate.Session;

import com.plantplaces.dto.Plant;

public class PlantHbmDAO implements IPlantDAO {

	@Override
	public List<Plant> fetchPlants() {

		return null;
	}

	@Override
	public void insert(Plant plant) throws Exception {
		// guardar planta en BD
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(plant);
		
		session.getTransaction().commit();
	}

	@Override
	public void update(Plant plant) throws Exception {

	}

	@Override
	public void delete(Plant plant) throws Exception {

	}

}

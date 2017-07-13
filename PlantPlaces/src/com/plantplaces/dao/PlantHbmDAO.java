package com.plantplaces.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.plantplaces.dto.Plant;

@SuppressWarnings({"rawtypes","unchecked"})
public class PlantHbmDAO implements IPlantDAO {

	private Query query;

	@Override
	public List<Plant> fetchPlants() {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 query = session.createQuery("from Plant");
		 List list = query.list();
	     List<Plant> plantas = Collections.checkedList( list, Plant.class);
	        
	     return plantas;
	}

	@Override
	public List<Plant> fetchPlants(Plant plant) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
	        /* Crea el query. El parámetro está en dos puntos (:) .
	         * OJO. En "from Plant", NO usamos el nombre de la tabla 'plant' en MySql!
	         *    usamos el nombre de la clase en Plant.hbm.xml 
	         *    en este caso com.plantplaces.dto.Plant (Plant)  
	         */
	        query = session.createQuery("from Plant where common like :common");
	        query.setParameter("common", "%" + plant.getCommon() + "%");
	        //query.setProperties(plant); 
	        /* NOTA:  query.setProperties(plant); asocia directamente la clase con 
	        el session porque automaticamente identifica el campo requerido (common), 
	        haciendo una mejora a la instruccion que esta arriba comentada (createQuery.setParameter) 
	        PERO al hacer una busqueda de tipo LIKE necesitamos los caracteres necesarios (%) en 
	        setParameter(), lo que no se puede hacer en setProperties.
	        */
	        
			List list = query.list();
	        List<Plant> plantas = Collections.checkedList( list, Plant.class);
	        
	        return plantas;
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

package com.plantplaces.dao;

import java.util.List;

import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.plantplaces.dto.Photo;
import com.plantplaces.dto.Specimen;

@Named
public class PhotoHbmDAO extends PlantPlacesHbmDAO<Photo> implements IPhotoDAO {

	
	@Override
	public void insert(Session session, Photo dto) throws Exception {
	 
		session.save(dto);
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<Photo> fetchPhotos(Specimen specimen) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		// create the query
		Query<Photo> query = session.createQuery("from Photo where specimenId = :specimenId");
		query.setParameter("specimenId", specimen.getId());
		// query.setProperties(plant);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		
		List<Photo> photos = list; //Collections.checkedList(list, Plant.class);
		
		return photos;
	}
	
}

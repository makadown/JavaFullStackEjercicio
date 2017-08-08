package com.plantplaces.service;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.plantplaces.dao.IFileDAO;
import com.plantplaces.dao.IPhotoDAO;
import com.plantplaces.dao.IPlantDAO;
import com.plantplaces.dao.ISpecimenDAO;
import com.plantplaces.dto.Photo;
import com.plantplaces.dto.Plant;
import com.plantplaces.dto.Specimen;

import net.coobird.thumbnailator.Thumbnails;

@Named
@ManagedBean
@Scope(value = "session",  proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PlantService implements IPlantService {
	
	/* 
	 * Importante! 
	 * Sobre una clase: 
	 * 
	 * @Named 
		es para darle u nnombre a la clase inyectada, si no se le especifica, tomara el nombre en lowercase (ej, plantService)
	   @ManagedBean 
	    es para registrar automaticamente la clase en el runtime como una clase bean manejada. Al tener esta anotación, la clase es
	    escaneada antes de hacer cualquier tipo de petición.
	   @Scope
	   	indica el ciclo de vida que tiene la instancia 
	   	
	   	Sobre un atributo:
	   	@Inject
	   	Identifica constructores inyectables, métodos y campos. 
	   	Puede aplicarse tanto a miembros estáticos como a instancias. 
	   	Un miembro inyectable puede tener cualquier modificador de acceso (privado, paquete-privado, protegido, público).
	    Los constructores se inyectan primero, seguido por los campos, y luego los métodos. 
	    Los campos y métodos en las superclases se inyectan antes que los de las subclases. 
	    No se especifica el orden de inyección entre campos y entre métodos en la misma clase.
	 * */

	@Inject
	private IPlantDAO plantDAO;	
	private List<Plant> allPlants;
	
	@Inject
	private ISpecimenDAO specimenDAO;
	 
	@Inject
	private IFileDAO fileDAO;
	
	@Inject
	private IPhotoDAO photoDAO;
	 
	@Inject
	private JMSBean jmsBean; 
	
	@Override
	public List<Plant> filterPlants(String filter) 
	{
		if(allPlants==null)
		{
		  allPlants = plantDAO.fetchPlants();
		}
		//filtrar la lista
		List<Plant> returnPlants = new ArrayList<Plant>();
		 
		for(Plant plant: allPlants )
		{
			
			if( plant.toString().toUpperCase().contains( filter.toUpperCase() ))
			{
				//esta planta hace match con el criterio
				returnPlants.add( plant );				
			}
		}
		return returnPlants;
	}
	
	@Override
	public void save(Plant plant) throws Exception {
		
		if( plant.getGenus()==null || plant.getGenus().isEmpty())
		{
			throw new Exception("Genus required");
		}
		plantDAO.save(plant);
	} 
	
	 
	
	public void save(Specimen specimen) throws Exception {
		specimenDAO.save(specimen);
	} 

	public IPlantDAO getPlantDAO() {
		return plantDAO;
	}

	public void setPlantDAO(IPlantDAO plantDAO) {
		this.plantDAO = plantDAO;
	}
	
	@Override
	public List<Plant> fetchPlants(Plant plant) {
		List<Plant> plants = plantDAO.fetchPlants(plant);
		return plants;
	}
	
	

	@Override
	public void loadSpecimens(Plant plant) {

 		List<Specimen> specimens = specimenDAO.fetchByPlantId(plant.getGuid());
		plant.setSpecimens(specimens); 
		
	}
	
	@Override
	public void savePhoto(Photo photo, InputStream inputStream) throws Exception {
		String ruta = "/Users/mserrano/git/JavaFullStackEjercicio/PlantPlaces/WebContent/resources/images";
		String rutaThumb = "/Users/mserrano/git/JavaFullStackEjercicio/PlantPlaces/WebContent/resources/thumbnails";
		 
		File directory = new File(ruta);
		String uniqueImageName = getUniqueImageName();
		File file = new File(directory, uniqueImageName);
		fileDAO.save(inputStream, file);
		 
		jmsBean.submit(file.toString()); 
 
		File thumbnailDirectory = new File(rutaThumb);
		File thumbnail = new File(thumbnailDirectory, uniqueImageName);
 
		Thumbnails.of(file).size(100, 100).toFile(thumbnail); 	
		photo.setUri(uniqueImageName);
		// eventually, save the photo to the database.
		photoDAO.save(photo); 
		 
	}

	private String getUniqueImageName() {
		// TODO Auto-generated method stub
		String imagePrefix = "plantPlaces";
		String imageSuffix = ".jpg";
		String middle ="";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		middle = sdf.format(new Date());
		
		return imagePrefix + middle + imageSuffix;
	}
	
	
	@Override
	public List<Photo> fetchPhotos(Specimen specimen) {
		return photoDAO.fetchPhotos(specimen);
	}

}

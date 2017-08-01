package com.plantplaces.ui;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.plantplaces.dto.Plant;
import com.plantplaces.service.IPlantService;

@WebService
public class PlantWSService implements IPlantWSService 
{
	 
	@Inject
	IPlantService plantService;
	
	public PlantWSService() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	public IPlantService getPlantService() {
		return plantService;
	}


	public void setPlantService(IPlantService plantService) {
		this.plantService = plantService;
	}


	@Override
	@WebMethod
	public String fetchPlants(String plantName)
	{
		String returnValue = "No hay plantas que hagan match";
        String filter = plantName; //req.getParameter("filter");	
		
		Plant searchPlant = new Plant();
		
		if ( filter != null && !filter.isEmpty())
		{
			searchPlant.setCommon(filter);
		} 
		
		//obteniendo la lista de plantas	
		List<Plant> plants = getPlantService().fetchPlants(searchPlant);
		
		//iterar sobre los resultados y elegir el ultimo que regresa.
		for (Plant plant : plants) {
			returnValue = plant.toString();
		}
		
		return returnValue;
	}
	
}

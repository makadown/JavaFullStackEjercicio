package com.plantplaces.service;

import java.util.*;

import com.plantplaces.dao.*;
import com.plantplaces.dto.*;

import javax.faces.bean.ManagedBean;
import javax.inject.*;

import org.springframework.context.annotation.Scope;

@Named
@ManagedBean
@Scope("session")
public class PlantService implements IPlantService {
	
	/* 
	 * Importante! 
	 * Sobre una clase: 
	 * 
	 * @Named 
		es para darle u nnombre a la clase inyectada, si no se le especifica, tomara el nombre en lowercase (ej, plantService)
	   @ManagedBean 
	    es para registrar automaticamente la clase en el runtime como una clase bean manejada. Al tener esta anotaci�n, la clase es
	    escaneada antes de hacer cualquier tipo de petici�n.
	   @Scope
	   	indica el ciclo de vida que tiene la instancia 
	   	
	   	Sobre un atributo:
	   	@Inject
	   	Identifica constructores inyectables, m�todos y campos. 
	   	Puede aplicarse tanto a miembros est�ticos como a instancias. 
	   	Un miembro inyectable puede tener cualquier modificador de acceso (privado, paquete-privado, protegido, p�blico).
	    Los constructores se inyectan primero, seguido por los campos, y luego los m�todos. 
	    Los campos y m�todos en las superclases se inyectan antes que los de las subclases. 
	    No se especifica el orden de inyecci�n entre campos y entre m�todos en la misma clase.
	 * */

	@Inject
	private IPlantDAO plantDAO;	
	private List<Plant> allPlants;
	
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
	
	public void save(Plant plant) throws Exception {
		
		if( plant.getGenus()==null || plant.getGenus().isEmpty())
		{
			throw new Exception("Genus required");
		}
		plantDAO.insert(plant);
	}

	public IPlantDAO getPlantDAO() {
		return plantDAO;
	}

	public void setPlantDAO(IPlantDAO plantDAO) {
		this.plantDAO = plantDAO;
	}

}

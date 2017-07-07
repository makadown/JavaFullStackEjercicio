package com.plantplaces.ui;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.plantplaces.dto.Plant;

@Named
@ManagedBean
@Scope("session")
public class SearchPlants 
{
    @Inject
	private Plant plant;
	
	public String execute() {
		//return  "search";
		if( plant!= null && plant.getName().equalsIgnoreCase("Redbud"))
		{
			return "search";
		}
		else
		{return  "noresults";}
		
	}

	public Plant getPlant() {
		return plant;
	}
	
	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	
	public List<Plant> completePlants(String query)
	{
		List<Plant> allPlants = new ArrayList<Plant>();
		
		//crear plantas y agregarlas a la coleccion.
		/*allPlants.add(new Plant("Eastern Redbud"));
		allPlants.add(new Plant("Paw Paw"));
		allPlants.add(new Plant("nasturtium"));*/
		allPlants.add(Plant.nuevaPlanta("Eastern Redbud"));
		allPlants.add(Plant.nuevaPlanta("Paw Paw"));
		allPlants.add(Plant.nuevaPlanta("nasturtium"));
		
		return allPlants;
	}
	
}

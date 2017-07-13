package com.plantplaces.ui;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;

import com.plantplaces.dto.Plant;
import com.plantplaces.service.IPlantService;

@Named
@ManagedBean
@Scope("session")
public class SearchPlants 
{
    @Inject
	private Plant plant;
	
    @Inject
    private IPlantService plantService;
    private List<Plant> plants;	
	private Plant selectedPlant;
	
	@Inject
	private SpecimenVO specimenVO;
    
	public String execute() {
		
        setPlants(plantService.fetchPlants(plant));
		
		if (plants.size() > 0){
			return "results";
		} else {
			return "noresults";
		}
	}

	public Plant getPlant() {
		return plant;
	}
	
	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	
	public List<Plant> completePlants(String query)
	{ 
		return plantService.filterPlants(query);
	}
	
	
	public List<Plant> getPlants() {
		return plants;
	}

	public void setPlants(List<Plant> plants) {
		this.plants = plants;
	}

	public Plant getSelectedPlant() {
		return selectedPlant;
	}

	public void setSelectedPlant(Plant selectedPlant) {
		this.selectedPlant = selectedPlant;
	}
	
	 
	  public void onRowSelect(SelectEvent event) {
         Plant plant  = ((Plant) event.getObject());
     
        // push the selected plant into SpecimenVO.
        specimenVO.setPlant(plant);
        
        try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("specimen.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        
    }
	 

	
}

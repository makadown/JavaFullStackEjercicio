package com.plantplaces.ui;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.plantplaces.dto.Plant;
import com.plantplaces.service.IPlantService;

@Named
@ManagedBean
@Scope("request")
public class AddPlant 
{	
	 @Inject
	private Plant plant;
		
	@Inject
	private IPlantService plantService;
	
	private String message = "foo";

	private FacesContext currentInstance;
	private FacesMessage fm ;
	
	public String execute()
	{
		String returnValue = "success";
		//get faces context
		currentInstance = FacesContext.getCurrentInstance();
		
		try {
			plantService.save(plant);
			fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved", "Plant saved!");
			//cual es el mensaje que queremos mostrar
			currentInstance.addMessage(null, fm);
			
		} catch (Exception e) {
			// Se recomienda tener estos try catch en el nivel más superior que se pueda
			returnValue = "fail";
			//e.printStackTrace();
			
			fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to save", "Plant could not be saved.");
			//cual es el mensaje que queremos mostrar
			currentInstance.addMessage(null, fm);
		}
		
		return returnValue;
		
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public IPlantService getPlantService() {
		return plantService;
	}

	public void setPlantService(IPlantService plantService) {
		this.plantService = plantService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

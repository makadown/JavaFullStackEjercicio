package com.plantplaces.dto;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@ManagedBean
@Scope("session")
public class Plant {
	String name ;
	
	/*
	 * Si dejo este constructor marca error porque sería un constructor más.
	 * TODO: ver como implementarlo correctamente para spring
	public Plant (String name)
	{
		this.name = name;
	}
	*/
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public static Plant nuevaPlanta(String Nombre)
	{
		Plant planta = new Plant();
		planta.setName(Nombre);
		return planta;
	}
	
	@Override   
	public String toString() { 
		return this.name;
	}
}

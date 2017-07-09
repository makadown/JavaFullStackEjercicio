package com.plantplaces.ui.test;

import org.junit.Test;

import com.plantplaces.dto.Plant;
import com.plantplaces.ui.SearchPlants;

import junit.framework.TestCase;

public class TestSearchPlants extends TestCase 
{
	private SearchPlants searchPlants ;
	private String execute;
	
	@Test
	public void testSearchPlantsExecute(){
		
		givenSearchPlantsCreatedWithRedBud();
		whenInvokeExecute();
		thenVerifyReturnSuccess();
		
	}
	
	@Test
	public void testSearchPlantsNoReBud(){
		
		givenSearchPlantsCreatedWithNoRedBud();
		whenInvokeExecute();
		thenVerifyReturnNoSuccess();
		
	}
	
	@Test
	public void testSearchPlantsNull()
	{
		givenSearchPlantsCreatedWithNullPlant();
		whenInvokeExecute();
		thenVerifyReturnNoSuccess();
	}

	private void givenSearchPlantsCreatedWithNullPlant() {
		searchPlants = new SearchPlants();
		
	}

	private void thenVerifyReturnSuccess() {
		 //assertEquals("success", execute);
		assertEquals("search", execute);
		
	}
	
	private void thenVerifyReturnNoSuccess() {
		 //assertEquals("success", execute);
		assertEquals("noresults", execute);
		
	}

	private void whenInvokeExecute() {
		execute = searchPlants.execute();
		
	}

	private void givenSearchPlantsCreatedWithRedBud() {
		searchPlants = new SearchPlants();
		Plant plant = new Plant();
		plant.setName("Redbud");
		searchPlants.setPlant(plant);
	}
	
	private void givenSearchPlantsCreatedWithNoRedBud() {
		searchPlants = new SearchPlants();
		Plant plant = new Plant();
		plant.setName("Paw Paw");
		searchPlants.setPlant(plant);
	}

}

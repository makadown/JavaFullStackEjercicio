package com.plantplaces.dao;

import java.util.List;

import org.junit.Test;

import com.plantplaces.dto.Plant;

import junit.framework.TestCase;

public class TestPlantHbmDAO extends TestCase 
{
	private IPlantDAO plantHbmDAO;
	private List<Plant> plants;
	
	@Test
	public void testFetchByCommonName()
	{
		givenPlantDAOInstatiated();
		whenCommonNameIsRedBud();
		thenVerifyResults();
	}

	private void thenVerifyResults() {
		assertTrue(plants.size()>0);
		
		for(Plant p : plants)
		{
			//assertEquals("RedBud", p.getCommon());
			assertTrue( p.getCommon().toUpperCase().equals("RedBud".toUpperCase()) );
		}
		
	}

	private void whenCommonNameIsRedBud() {
		
		Plant p = new Plant();
		p.setCommon("RedBud");
		plants =  plantHbmDAO.fetchPlants(p);
		
	}

	private void givenPlantDAOInstatiated() {
		plantHbmDAO = new PlantHbmDAO();
		
		
	}

}

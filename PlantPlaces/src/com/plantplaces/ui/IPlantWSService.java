package com.plantplaces.ui;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IPlantWSService {

	@WebMethod
	String fetchPlants(String plantName);

}
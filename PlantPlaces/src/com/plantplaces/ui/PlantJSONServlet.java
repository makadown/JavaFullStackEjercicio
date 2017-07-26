package com.plantplaces.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.plantplaces.dto.Plant;
import com.plantplaces.service.IPlantService;

public class PlantJSONServlet extends HttpServlet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	IPlantService plantService;
	
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		super.init();
		//esto configura la inyeccion de dependencia para plantService porque en Servlet el agua corre diferente
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Plant searchPlant = new Plant();
		//valor dummy para probar servlet
		searchPlant.setCommon("e");
		
		
		//obteniendo la lista de plantas	
		List<Plant> plants = plantService.fetchPlants(searchPlant);
		
		
		//empezar escribiendo el stream de datos
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		
		//iniciar el arbol de json
		JsonObjectBuilder rootBuilder = Json.createObjectBuilder();
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		
		for (Plant plant : plants) {
			//crear objecto JSON para cada planta. Las plantas tendrán los mismos nombres, pero diferente valor
			JsonObjectBuilder plantBuilder = Json.createObjectBuilder();
			JsonObject plantJson = plantBuilder.add("genus", plant.getGenus())
			.add("species", plant.getSpecies())
			.add("common", plant.getCommon())
			.add("cultivar", plant.getCultivar())
			.build();
			
			//agrega la planta al Array
			arrayBuilder.add(plantJson);
		}
		
		//agrega el arreglo de plantas a la raiz
		JsonObject root = rootBuilder.add("plants", arrayBuilder).build();
		
		//escribir el json al servlet 
		writer.print(root);
		
		writer.flush();
		writer.close();
	}

}

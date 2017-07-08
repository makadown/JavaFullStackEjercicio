package com.plantplaces.service;

import java.util.*;
import com.plantplaces.dto.*;

/**
 * Esta interfaz incluye todas las funciones relacionadas con la capa de negocio y 
 * entiades involucradas
 * 
 * @author Mario Serrano
 * 
 * */
public interface IPlantService {

	/**
	 * Regresa una colección de objetos Plant que contienen el texto de filtro
	 * @param filter Una subcadena que debe estar contenida en las plantas regresadas.
	 * @return Una colección de plantas que coinciden con el filtro.
	 */
	public List<Plant> filterPlants(String filter);
}

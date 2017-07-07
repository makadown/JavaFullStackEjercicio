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

	public List<Plant> filterPlants(String filter);
}

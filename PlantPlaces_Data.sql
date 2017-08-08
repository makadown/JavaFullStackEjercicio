USE `plantplaces` ;

Insert into `plantplaces`.`plant` (`genus`, `species`, `cultivar`, `common` ) 
values ( 'Asimina' , 'triloba', 'Potomac', 'Paw Paw' );


Insert into `plantplaces`.`plant` (`genus`, `species`, `cultivar`, `common` ) 
values ( 'Cercis' , 'canadensis', 'Eastern Redbud', 'Redbud' );

Insert into `plantplaces`.`plant` (`genus`, `species`, `cultivar`, `common` ) 
values ( 'Cercis 3' , 'canadensis 3', 'Eastern Redbud 3', 'Redbud 3' );

Insert into `plantplaces`.`plant` (`genus`, `species`, `cultivar`, `common` ) 
values ( 'Trapoleanum' , '', '', 'nasturtium' );


Insert into `plantplaces`.`plant` (`genus`, `species`, `cultivar`, `common` ) 
values ( 'Acer' , 'rubrum', 'Autumn Blaze', 'Autumn Blaze Maple' );

Insert into `plantplaces`.`plant` (`genus`, `species`, `cultivar`, `common` ) 
values ( 'Red Cercis' , 'canadensis', 'Northern Redbud', 'Autumn redbud' );

Insert into `plantplaces`.`plant` (`genus`, `species`, `cultivar`, `common` ) 
values ( 'Light Red Cercis' , 'canadensis 2', 'Southern Redbud', 'Winter redbud' );


Insert into `plantplaces`.`plant` (`genus`, `species`, `cultivar`, `common` ) 
values ( 'Red Cercis 2' , 'canadensis 2', 'Northern Redbud 2', 'Autumn redbud 2' );


Insert into `plantplaces`.`specimen` (`latitude`, `longitude`, `plant_id`, `planted_by`, `planted_date` , `description` ) 
values ( '44.44', '55.55', '6', 'Mario Serrano', 'Octubre 18', 'autoCreado por Script' );
Insert into `plantplaces`.`specimen` (`latitude`, `longitude`, `plant_id`, `planted_by`, `planted_date` , `description` ) 
values ( '32.66', '-116.50', '6', 'Mariana Moreno', 'Agosto 18', 'autoCreado por Script' );

Insert into `plantplaces`.`specimen` (`latitude`, `longitude`, `plant_id`, `planted_by`, `planted_date` , `description` ) 
values ( '11.11', '22.22', '1', 'Pepe Pecas', 'Nov 1', 'autoCreado por Script' );

Insert into `plantplaces`.`specimen` (`latitude`, `longitude`, `plant_id`, `planted_by`, `planted_date` , `description` ) 
values ( '32.65', '-115.46', '5', 'Claudia Estrada', 'Julio 13', 'autoCreado por Script' );

/*insert into contributor(first_name, last_name)  values ('mario', 'Serrano');*/
package com.ubagroup.portal.repositories;

import java.util.Collection;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ubagroup.portal.entities.Application;


@Repository
@Component
public interface ApplicationRepository extends CrudRepository<Application, Long>{

	void save(boolean statut);
	
	@Query(value = "SELECT DISTINCT * FROM Application a WHERE a.statut_actuel = 'online'", nativeQuery = true)
    Collection<Application> findAllApplicationOnline();
	
	
	  @Query(value ="SELECT COUNT(*) FROM Application a WHERE a.statut_actuel = 'online'",nativeQuery = true) 
	  long countOnline();
	  
	  @Query(value ="SELECT COUNT(*) FROM Application a",nativeQuery = true) 
	  long countTotal();   
	  
	  @Query(value ="SELECT COUNT(*) FROM Application a WHERE a.statut_actuel = 'offline'",nativeQuery = true) 
	  long countOffline(); 
	  
	  @Query(value ="SELECT COUNT(*) FROM Application a WHERE a.statut_actuel = 'null'",nativeQuery = true) 
	  long countOfflineCreate();

	
	  
	  



}
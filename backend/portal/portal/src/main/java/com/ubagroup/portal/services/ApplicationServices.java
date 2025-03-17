package com.ubagroup.portal.services;

import java.util.List;

import com.ubagroup.portal.entities.Application;

public interface ApplicationServices {
	
	List<Application> findAll();
	
	long countTotalApp();
	
	long countOnlineApp();
	
	long countOfflineApp();
	
	long countOfflineAppCreate();

}

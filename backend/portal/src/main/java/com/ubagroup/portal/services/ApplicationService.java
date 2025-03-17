package com.ubagroup.portal.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubagroup.portal.entities.Application;
import com.ubagroup.portal.repositories.ApplicationRepository;


@Service
public class ApplicationService {
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	public Application getApplicationById(Long id) {
		return applicationRepository.findById(id).orElse(null);
	}
	
	public long countTotalApp() {
		return  applicationRepository.countTotal();
	}
	
	
	public long countOnlineApp() { 
		return applicationRepository.countOnline(); 
	}
	  
	public long countOfflineApp() { 
		return applicationRepository.countOffline();
	}
	
	public long countOfflineAppCreate() { 
		return applicationRepository.countOfflineCreate();
	}
	
	
}

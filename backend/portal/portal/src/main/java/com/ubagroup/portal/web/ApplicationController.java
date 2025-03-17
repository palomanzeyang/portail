package com.ubagroup.portal.web;



import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.ubagroup.portal.entities.Application;
import com.ubagroup.portal.repositories.ApplicationRepository;
import com.ubagroup.portal.services.ApplicationService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Controller
@AllArgsConstructor
@Data
public class ApplicationController {
	
	@Autowired
	private ApplicationRepository applicationRepository;

	
	@Autowired
	private ApplicationService applicationService;
	
	 
	@GetMapping("/signup")  
	public String showSignUpForm(Application app) {
        return "addApp";
    }
	
	
	@PostMapping("/addapps")
	public String ajouterApplications(@Valid Application app, BindingResult result, Model model, @RequestParam MultipartFile file) {
		if(result.hasErrors()) {
			return"addApp";	
		}
		
		applicationRepository.save(app);
		
		String fileName ="IMG-PORTAL-"+app.getId()+".png";
		System.out.print("Le nom du fichier est :"+ fileName);
		
		moveFile(file, fileName);
		 
		return "redirect:/listApp";
	}
	
	@GetMapping("/listApp")
	public String showListApps(Model model) { 
		
	    model.addAttribute("app", applicationRepository.findAll()); 
	    return "listApp";
	}
	
	
	
	@GetMapping("/login")
	public String showConnexion(Model model) {
		
		return "login"; 
		
	}
	
	
	@GetMapping("/profileUser")
	public String showIn(Model model) {
		
		return "profileUser";
		
	}
	
	@GetMapping("/dashBoard")
	public String show(Model model) {
		
		long totalApplications = applicationService.countTotalApp();
		long onlineApplications = applicationService.countOnlineApp(); 
		long offlineApplications = applicationService.countOfflineApp();
		long offlineApplicationsCreate = applicationService.countOfflineAppCreate();
		
		
		model.addAttribute("totalApplications", totalApplications);
		model.addAttribute("onlineApplications", onlineApplications);
		model.addAttribute("offlineApplications", offlineApplications);
		model.addAttribute("offlineApplicationsCreate", offlineApplicationsCreate);
		
		return "dashBoard";
		
	}
	
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		Application app = applicationRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid apps Id:" +id));
		model.addAttribute("app",app);
		return"updateApp";
	}
	
	
	@PostMapping("/update/{id}")
	public String modifierApps(@PathVariable("id") Long id, @Valid Application app,
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			app.setId(id);
			return"updateApp";
			
		}
		
		applicationRepository.save(app);
		return "redirect:/listApp";
		
	}
	

	@GetMapping("/portalWeb")
	public String ListAppPortal(Model model) {  

		model.addAttribute("app",applicationRepository.findAllApplicationOnline());
	    return "portalWeb"; 
	}
	
	
	@GetMapping("/delete/{id}")
	public String supprimerApps(@PathVariable("id") Long id, Model model) {
		Application app = applicationRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid apps Id:" +id));
		applicationRepository.delete(app);
		return "redirect:/listApp";
		
	}
	
	@PostConstruct
	public void init() {
		try {
			Files.createDirectories(Paths.get("/uba_portal/pictures"));
		}catch (Exception e) {
			throw new RuntimeException("Impossible de créer le dossier!");
		}
	}
	
	public void moveFile(MultipartFile file, String fileName) {
		try {
			Path root = Paths.get("/uba_portal/pictures");
			if(!Files.exists(root)) {
				init();
			}
			Files.copy(file.getInputStream(), root.resolve(fileName));
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file.Error: " +e.getMessage());
		}
	}


	public ApplicationRepository getApplicationRepository() {
		return applicationRepository;
	}


	public void setApplicationRepository(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}


	public ApplicationService getApplicationService() {
		return applicationService;
	}


	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	

}

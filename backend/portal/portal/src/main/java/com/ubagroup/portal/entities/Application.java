package com.ubagroup.portal.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	private String url;
	
	private String statutActuel;
	
	private String icone;

	
	
	public Long getId() {
		
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatutActuel() {
		return statutActuel;
	}

	public void setStatutActuel(String statutActuel) {
		this.statutActuel = statutActuel;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}


}
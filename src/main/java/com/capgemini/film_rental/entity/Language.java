package com.capgemini.film_rental.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id",columnDefinition = "TINYINT UNSIGNED")
   // @JsonIgnore
    private int languageId;

    @Column(name = "name", nullable = false, length = 20,columnDefinition = "CHAR")
    private String name;

    @Column(name = "last_update", nullable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

    
    
    
    @OneToMany(mappedBy = "language")
    @JsonIgnore
    private List<Film> films=new ArrayList<>();

    @OneToMany(mappedBy = "originalLanguage")
    @JsonIgnore
    private List<Film> originalLanguageFilms=new ArrayList<>();
    
    
    

	public int getLanguageId() 
	{
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public List<Film> getOriginalLanguageFilms() {
		return originalLanguageFilms;
	}

	public void setOriginalLanguageFilms(List<Film> originalLanguageFilms) {
		this.originalLanguageFilms = originalLanguageFilms;
	}

	@Override
	public int hashCode() {
		return Objects.hash(films, languageId, lastUpdate, name, originalLanguageFilms);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		return Objects.equals(films, other.films) && Objects.equals(languageId, other.languageId)
				&& Objects.equals(lastUpdate, other.lastUpdate) && Objects.equals(name, other.name)
				&& Objects.equals(originalLanguageFilms, other.originalLanguageFilms);
	}

	@Override
	public String toString() {
		return "Language [languageId=" + languageId + ", name=" + name + ", lastUpdate=" + lastUpdate + ", films="
				+ films + ", originalLanguageFilms=" + originalLanguageFilms + "]";
	}

    // Getters, setters, equals, hashCode, toString
    
    
}

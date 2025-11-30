package com.capgemini.film_rental.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.capgemini.film_rental.entity.enums.Rating;
import com.capgemini.film_rental.entity.enums.RatingConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id",columnDefinition = "SMALLINT UNSIGNED")
    private int filmId;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_year",columnDefinition = "YEAR")
    private Integer releaseYear;

    
    //Language
   
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false,columnDefinition = "TINYINT UNSIGNED")
    private Language language;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "original_language_id",columnDefinition = "TINYINT UNSIGNED")
    private Language originalLanguage;

    
    
    //Inventory
    
    
    @Column(name = "rental_duration", nullable = false, columnDefinition = "TINYINT UNSIGNED DEFAULT 3")
    private int rentalDuration=3;

    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2, columnDefinition = "DECIMAL(4,2) DEFAULT 4.99")
    private BigDecimal rentalRate = BigDecimal.valueOf(4.99);

    @Column(name = "length",columnDefinition = "SMALLINT UNSIGNED",nullable = true)
    private Short length;

    @Column(name = "replacement_cost",precision = 5,scale = 2 ,nullable = false,columnDefinition = "DECIMAL(5,2) DEFAULT 19.99")
    private BigDecimal replacementCost=BigDecimal.valueOf(19.99);

    @Convert(converter = RatingConverter.class)
    @Column(name = "rating", nullable = true, columnDefinition = "ENUM('G', 'PG', 'PG-13', 'R', 'NC-17') DEFAULT 'G'")
    private Rating rating=Rating.G;

    @Column(name = "special_features", columnDefinition = "SET('Trailers','Commentaries','Deleted Scenes','Behind the Scenes')")
    private String specialFeatures;

    @Column(name = "last_update", nullable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable=false, updatable=false)
    private LocalDateTime lastUpdate;
    
    
    
    

    // Many-to-many relationship with Actor
    @ManyToMany
   
    @JoinTable(
        name = "film_actor", // Join table name
        joinColumns = @JoinColumn(name = "film_id",columnDefinition = "SMALLINT UNSIGNED"), // Foreign key in the join table referencing Film
        inverseJoinColumns = @JoinColumn(name = "actor_id",columnDefinition = "SMALLINT UNSIGNED") // Foreign key in the join table referencing Actor
    )
    @JsonIgnore
    private List<Actor> actors=new ArrayList<>();

    
    
    
    
    // Many-to-many relationship with Category
    @ManyToMany
    @JoinTable(
        name = "film_category", // Join table name
        joinColumns = @JoinColumn(name = "film_id",columnDefinition = "SMALLINT UNSIGNED"), // Foreign key in the join table referencing Film
        inverseJoinColumns = @JoinColumn(name = "category_id",columnDefinition = "TINYINT UNSIGNED") // Foreign key in the join table referencing Category
    )
    @JsonIgnore
    private List<Category> categories=new ArrayList<>();
    
    
//    @ManyToOne
//    @JoinColumn(name = "title", referencedColumnName = "title", insertable = false, updatable = false)
//    private FilmText filmText;

    // Getters and Setters
    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(Language originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Short getLength() {
        return length;
    }

    public void setLength(Short length) {
        this.length = length;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    

    

    

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(categories, other.categories)
				&& Objects.equals(description, other.description) && filmId == other.filmId
				&& Objects.equals(language, other.language) && Objects.equals(lastUpdate, other.lastUpdate)
				&& Objects.equals(length, other.length) && Objects.equals(originalLanguage, other.originalLanguage)
				&& rating == other.rating && Objects.equals(releaseYear, other.releaseYear)
				&& rentalDuration == other.rentalDuration && Objects.equals(rentalRate, other.rentalRate)
				&& Objects.equals(replacementCost, other.replacementCost)
				&& Objects.equals(specialFeatures, other.specialFeatures) && Objects.equals(title, other.title);
	}

    @Override
	public int hashCode() {
		return Objects.hash(actors, categories, description, filmId, language, lastUpdate, length, originalLanguage,
				rating, releaseYear, rentalDuration, rentalRate, replacementCost, specialFeatures, title);
	}

	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", description=" + description + ", releaseYear="
				+ releaseYear + ", language=" + language + ", originalLanguage=" + originalLanguage
				+ ", rentalDuration=" + rentalDuration + ", rentalRate=" + rentalRate + ", length=" + length
				+ ", replacementCost=" + replacementCost + ", rating=" + rating + ", specialFeatures=" + specialFeatures
				+ ", lastUpdate=" + lastUpdate + ", actors=" + actors + ", categories=" + categories + "]";
	}
    
    

    
}
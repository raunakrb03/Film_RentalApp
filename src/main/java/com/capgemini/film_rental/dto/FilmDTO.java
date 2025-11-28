
package com.capgemini.film_rental.dto;

import java.time.LocalDateTime;
import java.util.List;

public class FilmDTO {

    private int filmId;
    private String title;
    private String description;
    private Integer releaseYear;
    private String language;           // Instead of Language entity, use name or code
    private String originalLanguage;   // Same here
    private int rentalDuration;
    private double rentalRate;
    private int length;
    private double replacementCost;
    private String rating;
    private String specialFeatures;
    private LocalDateTime lastUpdate;
    private List<String> categories;   // Instead of Category entity, use category names

    // Constructors
    public FilmDTO() {}

    public FilmDTO(int filmId, String title, String description, Integer releaseYear,
                   String language, String originalLanguage, int rentalDuration,
                   double rentalRate, int length, double replacementCost, String rating,
                   String specialFeatures, LocalDateTime lastUpdate, List<String> categories) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.language = language;
        this.originalLanguage = originalLanguage;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
        this.lastUpdate = lastUpdate;
        this.categories = categories;
    }

    // Getters and Setters
    int getFilmId() { return filmId; }
    public void setFilmId(int filmId) { this.filmId = filmId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getOriginalLanguage() { return originalLanguage; }
    public void setOriginalLanguage(String originalLanguage) { this.originalLanguage = originalLanguage; }

    public int getRentalDuration() { return rentalDuration; }
    public void setRentalDuration(int rentalDuration) { this.rentalDuration = rentalDuration; }

    public double getRentalRate() { return rentalRate; }
    public void setRentalRate(double rentalRate) { this.rentalRate = rentalRate; }

    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }

    public double getReplacementCost() { return replacementCost; }
    public void setReplacementCost(double replacementCost) { this.replacementCost = replacementCost; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }

    public String getSpecialFeatures() { return specialFeatures; }
    public void setSpecialFeatures(String specialFeatures) { this.specialFeatures = specialFeatures; }

    public LocalDateTime getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }

    public List<String> getCategories() { return categories; }
    public void setCategories(List<String> categories) { this.categories = categories; }
}

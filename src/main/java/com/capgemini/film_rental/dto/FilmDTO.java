package com.capgemini.film_rental.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class FilmDTO {
    private int filmId;
    private String title;
    private String description;
    private Integer releaseYear;
    private Integer languageId;
    private Integer originalLanguageId;
    private int rentalDuration;
    private BigDecimal rentalRate;
    private Short length;
    private BigDecimal replacementCost;
    private String rating;
    private String specialFeatures;
    private LocalDateTime lastUpdate;
    private List<Integer> categoryIds;
    private List<Integer> actorIds;
    // New fields: provide friendly names to the frontend while keeping IDs for compatibility
    private List<String> categoryNames;
    private String languageName;
    private String originalLanguageName;

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int v) {
        this.filmId = v;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String v) {
        this.title = v;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String v) {
        this.description = v;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer v) {
        this.releaseYear = v;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer v) {
        this.languageId = v;
    }

    public Integer getOriginalLanguageId() {
        return originalLanguageId;
    }

    public void setOriginalLanguageId(Integer v) {
        this.originalLanguageId = v;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int v) {
        this.rentalDuration = v;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal v) {
        this.rentalRate = v;
    }

    public Short getLength() {
        return length;
    }

    public void setLength(Short v) {
        this.length = v;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal v) {
        this.replacementCost = v;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String v) {
        this.rating = v;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String v) {
        this.specialFeatures = v;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime v) {
        this.lastUpdate = v;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> v) {
        this.categoryIds = v;
    }

    public List<Integer> getActorIds() {
        return actorIds;
    }

    public void setActorIds(List<Integer> v) {
        this.actorIds = v;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getOriginalLanguageName() {
        return originalLanguageName;
    }

    public void setOriginalLanguageName(String originalLanguageName) {
        this.originalLanguageName = originalLanguageName;
    }

    public void setRentalCount(Long count) {
        // Placeholder for rental count logic
    }
}

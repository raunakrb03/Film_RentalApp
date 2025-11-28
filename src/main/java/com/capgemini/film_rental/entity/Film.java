package com.capgemini.film_rental.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int film_id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer release_year;

    @ManyToMany
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonIgnore
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "language_id")
    @JsonIgnore
    private Language language;

    @ManyToOne
    @JoinColumn(name = "original_language_id")
    @JsonIgnore
    private Language original_language;

    private int rental_duration;

    private double rental_rate;

    private int length;

    private double replacement_cost;

    private String rating; //enum

    private String special_features;

    private LocalDateTime last_update;

    // ✅ Added ManyToMany relationship with Category
    @ManyToMany
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )

    // Getters and setters
    public int getFilm_id() {
        return film_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getRelease_year() {
        return release_year;
    }

    public Language getLanguage() {
        return language;
    }

    public Language getOriginal_language() {
        return original_language;
    }

    public int getRental_duration() {
        return rental_duration;
    }

    public double getRental_rate() {
        return rental_rate;
    }

    public int getLength() {
        return length;
    }

    public double getReplacement_cost() {
        return replacement_cost;
    }

    public String getRating() {
        return rating;
    }

    public String getSpecial_features() {
        return special_features;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Integer getFilmId() {
        return getFilm_id();
    }
}
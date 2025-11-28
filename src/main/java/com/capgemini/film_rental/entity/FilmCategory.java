package com.capgemini.film_rental.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "film_category")
public class FilmCategory {

    @EmbeddedId
    private FilmCategoryKey id;

    @ManyToOne
    @JoinColumn(name = "film_id", insertable = false, updatable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @Column(name = "last_update")
    private LocalDateTime last_update;

    // Getters and setters
    public FilmCategoryKey getId() {
        return id;
    }

    public void setId(FilmCategoryKey id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }

    // Embedded key class
    @Embeddable
    public static class FilmCategoryKey {

        @Column(name = "film_id")
        private int film_id;

        @Column(name = "category_id")
        private int category_id;

        public FilmCategoryKey() {}

        public FilmCategoryKey(int film_id, int category_id) {
            this.film_id = film_id;
            this.category_id = category_id;
        }

        public int getFilm_id() {
            return film_id;
        }

        public void setFilm_id(int film_id) {
            this.film_id = film_id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FilmCategoryKey)) return false;
            FilmCategoryKey that = (FilmCategoryKey) o;
            return film_id == that.film_id && category_id == that.category_id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(film_id, category_id);
        }
    }
}
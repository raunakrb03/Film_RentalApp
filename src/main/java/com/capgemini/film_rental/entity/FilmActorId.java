package com.capgemini.film_rental.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FilmActorId implements Serializable {

    @Column(name = "actor_id", nullable = false)
    private Integer actorId;

    @Column(name = "film_id", nullable = false)
    private Integer filmId;

    public FilmActorId() {}

    public FilmActorId(Integer actorId, Integer filmId) {
        this.actorId = actorId;
        this.filmId = filmId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmActorId)) return false;
        FilmActorId that = (FilmActorId) o;
        return Objects.equals(actorId, that.actorId) &&
                Objects.equals(filmId, that.filmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, filmId);
    }
}

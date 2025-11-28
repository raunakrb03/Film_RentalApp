package com.capgemini.film_rental.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "film_actor"
)
public class FilmActor {

    @EmbeddedId
    private FilmActorId id;

    // Use @MapsId to tie the relations to the composite key fields
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("actorId")
    @JoinColumn(name = "actor_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_film_actor_actor"))
    private Actor actor;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filmId")
    @JoinColumn(name = "film_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_film_actor_film"))
    private Film film;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    public FilmActor() {}

    public FilmActor(Actor actor, Film film, LocalDateTime lastUpdate) {
        this.actor = actor;
        this.film = film;
        this.id = new FilmActorId(
                actor != null ? actor.getActorId() : null,
                film != null ? film.getFilmId() : null
        );
        this.lastUpdate = lastUpdate;
    }

    public FilmActorId getId() {
        return id;
    }

    public void setId(FilmActorId id) {
        this.id = id;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
        if (this.id == null) {
            this.id = new FilmActorId();
        }
        this.id.setActorId(actor != null ? actor.getActorId() : null);
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
        if (this.id == null) {
            this.id = new FilmActorId();
        }
        this.id.setFilmId(film != null ? film.getFilmId() : null);
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}

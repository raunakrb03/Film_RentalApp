
package com.capgemini.film_rental.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ActorDTO {

    private int actorId;
    private String firstName;
    private String lastName;
    private LocalDateTime lastUpdate;
    private List<String> films; // Instead of Film entity, use film titles or IDs

    // Constructors
    public ActorDTO() {}

    public ActorDTO(int actorId, String firstName, String lastName, LocalDateTime lastUpdate, List<String> films) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
        this.films = films;
    }

    // Getters and Setters
    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }
}

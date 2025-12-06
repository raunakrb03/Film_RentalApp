package com.capgemini.film_rental.dto;

public class ActorDTO {
    private int actorId;
    private String firstName;
    private String lastName;

    public ActorDTO() {}

    // Projection constructor used by JPA queries: new com.capgemini.film_rental.dto.ActorDTO(id, first, last)
    public ActorDTO(int actorId, String firstName, String lastName) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int v) {
        this.actorId = v;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String v) {
        this.firstName = v;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String v) {
        this.lastName = v;
    }
}


package com.capgemini.film_rental.dto.aggregates;

public class ActorFilmCountDTO {
    private int actorId;
    private String firstName;
    private String lastName;
    private long filmCount;

    public ActorFilmCountDTO(int id, String fn, String ln, long c) {
        this.actorId = id;
        this.firstName = fn;
        this.lastName = ln;
        this.filmCount = c;
    }

    public int getActorId() {
        return actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getFilmCount() {
        return filmCount;
    }
}

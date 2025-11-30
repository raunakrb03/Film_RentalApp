package com.capgemini.film_rental.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ActorCreateDTO {
    @NotBlank(message = "firstName must not be blank")
    @Size(max = 45, message = "firstName must be at most 45 characters")
    private String firstName;

    @NotBlank(message = "lastName must not be blank")
    @Size(max = 45, message = "lastName must be at most 45 characters")
    private String lastName;

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

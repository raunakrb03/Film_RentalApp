package com.capgemini.film_rental.dto.aggregates;

import jakarta.validation.constraints.NotBlank;

public class UpdateReturnDateDTO {
    @NotBlank(message = "Return date is required")
    private String returnDateIso;

    public String getReturnDateIso() {
        return returnDateIso;
    }

    public void setReturnDateIso(String returnDateIso) {
        this.returnDateIso = returnDateIso;
    }
}

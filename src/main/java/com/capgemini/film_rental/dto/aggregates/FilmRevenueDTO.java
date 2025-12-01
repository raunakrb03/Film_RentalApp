
package com.capgemini.film_rental.dto.aggregates;

import java.math.BigDecimal;

public class FilmRevenueDTO {
    private int filmId;
    private String filmTitle;
    private BigDecimal amount;

    // Constructor with film ID and amount (backward compatible)
    public FilmRevenueDTO(int id, BigDecimal amt) {
        this.filmId = id;
        this.amount = amt;
        this.filmTitle = null;
    }

    // Constructor with film ID, title, and amount
    public FilmRevenueDTO(int id, String title, BigDecimal amt) {
        this.filmId = id;
        this.filmTitle = title;
        this.amount = amt;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static class StoreAmount {
        public int storeId;
        public BigDecimal amount;

        public StoreAmount(int s, BigDecimal a) {
            this.storeId = s;
            this.amount = a;
        }
    }
}

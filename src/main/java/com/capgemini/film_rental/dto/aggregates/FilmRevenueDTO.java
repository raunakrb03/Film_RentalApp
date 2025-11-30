
package com.capgemini.film_rental.dto.aggregates;

import java.math.BigDecimal;

public class FilmRevenueDTO {
    private int filmId;
    private BigDecimal amount;

    public FilmRevenueDTO(int id, BigDecimal amt) {
        this.filmId = id;
        this.amount = amt;
    }

    public int getFilmId() {
        return filmId;
    }

    public BigDecimal getAmount() {
        return amount;
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

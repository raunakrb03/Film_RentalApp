package com.capgemini.film_rental.dto;

public class InventoryAddDTO {
    private int filmId;
    private int storeId;

    public InventoryAddDTO() {
    }

    public InventoryAddDTO(int filmId, int storeId) {
        this.filmId = filmId;
        this.storeId = storeId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}


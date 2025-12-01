package com.capgemini.film_rental.dto;

public class FilmInventoryCountDTO {
    private int filmId;
    private String filmTitle;
    private int storeId;
    private long inventoryCount;

    public FilmInventoryCountDTO() {
    }

    public FilmInventoryCountDTO(int filmId, String filmTitle, int storeId, long inventoryCount) {
        this.filmId = filmId;
        this.filmTitle = filmTitle;
        this.storeId = storeId;
        this.inventoryCount = inventoryCount;
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

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public long getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(long inventoryCount) {
        this.inventoryCount = inventoryCount;
    }
}



package com.capgemini.film_rental.dto.aggregates; public class TopFilmDTO {
    private int filmId;
    private long rentals;
    public TopFilmDTO(int id,long r){
        this.filmId=id;
        this.rentals=r;
    }
    public int getFilmId(){return filmId;}
    public long getRentals(){return rentals;}
}

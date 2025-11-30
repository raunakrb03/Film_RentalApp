
package com.capgemini.film_rental.dto.aggregates;

public class StoreInventoryDTO {
    private String title;
    private long copies;

    public StoreInventoryDTO(String t, long c) {
        this.title = t;
        this.copies = c;
    }

    public String getTitle() {
        return title;
    }

    public long getCopies() {
        return copies;
    }
}

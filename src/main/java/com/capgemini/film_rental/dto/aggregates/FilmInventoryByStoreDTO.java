
package com.capgemini.film_rental.dto.aggregates; public class FilmInventoryByStoreDTO { private int storeAddressId; private long copies; public FilmInventoryByStoreDTO(int s,long c){this.storeAddressId=s; this.copies=c;} public int getStoreAddressId(){return storeAddressId;} public long getCopies(){return copies;} }

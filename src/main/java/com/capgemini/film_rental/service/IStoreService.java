package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.StoreDTO;

import java.util.List;

public interface IStoreService {
    StoreDTO.ManagerDetails managerDetails(int storeId);

    List<Integer> customerIds(int storeId);

    List<StoreDTO> findByCity(String city);

    StoreDTO assignAddress(int storeId, int addressId);

    StoreDTO updatePhone(int storeId, String phone);

    List<StoreDTO> findByCountry(String country);

    List<StoreDTO.ManagerAndStoreView> managersOverview();
    List<Integer> staffIds(int storeId);
    StoreDTO createStore(StoreDTO dto);
}
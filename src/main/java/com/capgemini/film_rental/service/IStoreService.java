package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.StoreDTO;

import java.util.List;

public interface IStoreService {
    StoreDTO.ManagerDetails managerDetails(int storeId);

    List<Integer> customerIds(int storeId);

    List<StoreDTO> findByCity(String city);

    StoreDTO assignAddress(int storeId, int addressId);

    List<StoreDTO.ManagerAndStoreView> managersOverview();

    StoreDTO assignManager(int storeId, int managerStaffId);

    StoreDTO findByPhone(String phone);
}
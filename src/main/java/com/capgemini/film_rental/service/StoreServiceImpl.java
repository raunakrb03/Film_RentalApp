package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.StoreDTO;
import com.capgemini.film_rental.entity.Address;
import com.capgemini.film_rental.entity.Customer;
import com.capgemini.film_rental.entity.Staff;
import com.capgemini.film_rental.entity.Store;
import com.capgemini.film_rental.exception.NotFoundException;
import com.capgemini.film_rental.repository.IAddressRepository;
import com.capgemini.film_rental.repository.IStoreRepository;
import com.capgemini.film_rental.repository.IStaffRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StoreServiceImpl implements IStoreService {

    private final IStoreRepository repo;
    private final IStaffRepository staffRepo;
    private final IAddressRepository addressRepo;

    public StoreServiceImpl(IStoreRepository repo, IStaffRepository staffRepo, IAddressRepository addressRepo) {
        this.repo = repo;
        this.staffRepo = staffRepo;
        this.addressRepo = addressRepo;
    }

    private Store get(int id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Store not found: " + id));
    }

    @Override
    public StoreDTO.ManagerDetails managerDetails(int storeId) {
        Store s = get(storeId);
        Staff m = s.getManagerStaff();
        StoreDTO.ManagerDetails d = new StoreDTO.ManagerDetails();
        if (m != null) {
            d.firstName = m.getFirstName();
            d.lastName = m.getLastName();
            d.email = m.getEmail();
            d.phone = m.getAddress() != null ? m.getAddress().getPhone() : null;
        }
        return d;
    }

    @Override
    public List<Integer> customerIds(int storeId) {
        return repo.findById(storeId).map(Store::getCustomers).orElseGet(java.util.ArrayList::new).stream()
                .map(Customer::getCustomerId).collect(Collectors.toList());
    }

    @Override
    public List<StoreDTO> findByCity(String city) {
        return repo.findByAddress_City_CityIgnoreCase(city).stream().map(s -> {
            StoreDTO d = new StoreDTO();
            d.setStoreId(s.getStoreId());
            d.setManagerStaffId(s.getManagerStaff() != null ? s.getManagerStaff().getStaffId() : null);
            d.setAddressId(s.getAddress() != null ? s.getAddress().getAddressId() : null);
            return d;
        }).collect(Collectors.toList());
    }

    @Override
    public StoreDTO assignAddress(int storeId, int addressId) {
        Store s = get(storeId);
        Address a = addressRepo.findById(addressId).orElseThrow(() -> new NotFoundException("Address not found"));
        s.setAddress(a);
        s = repo.save(s);
        StoreDTO d = new StoreDTO();
        d.setStoreId(s.getStoreId());
        d.setManagerStaffId(s.getManagerStaff() != null ? s.getManagerStaff().getStaffId() : null);
        d.setAddressId(s.getAddress() != null ? s.getAddress().getAddressId() : null);
        return d;
    }

    @Override
    public StoreDTO updatePhone(int storeId, String phone) {
        Store s = get(storeId);
        Address a = s.getAddress();
        if (a == null) throw new NotFoundException("Store has no address");
        a.setPhone(phone);
        addressRepo.save(a);
        StoreDTO d = new StoreDTO();
        d.setStoreId(s.getStoreId());
        d.setManagerStaffId(s.getManagerStaff() != null ? s.getManagerStaff().getStaffId() : null);
        d.setAddressId(s.getAddress() != null ? s.getAddress().getAddressId() : null);
        return d;
    }

    @Override
    public List<StoreDTO.ManagerAndStoreView> managersOverview() {
        return repo.findAll().stream().map(s -> {
            StoreDTO.ManagerAndStoreView v = new StoreDTO.ManagerAndStoreView();
            Staff m = s.getManagerStaff();
            if (m != null) {
                v.managerFirstName = m.getFirstName();
                v.managerLastName = m.getLastName();
                v.managerEmail = m.getEmail();
                v.managerPhone = m.getAddress() != null ? m.getAddress().getPhone() : null;
            }
            v.storeAddress = s.getAddress() != null ? s.getAddress().getAddress() : null;
            v.storeCity = s.getAddress() != null && s.getAddress().getCity() != null ? s.getAddress().getCity().getCity() : null;
            v.storePhone = s.getAddress() != null ? s.getAddress().getPhone() : null;
            return v;
        }).collect(Collectors.toList());
    }

    @Override
    public List<StoreDTO> findByCountry(String country) {
        return repo.findByCountry(country).stream().map(s -> {
            StoreDTO d = new StoreDTO();
            d.setStoreId(s.getStoreId());
            d.setManagerStaffId(s.getManagerStaff() != null ? s.getManagerStaff().getStaffId() : null);
            d.setAddressId(s.getAddress() != null ? s.getAddress().getAddressId() : null);
            return d;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Integer> staffIds(int storeId) {
        Store store = get(storeId);
        return store.getStaff().stream()
                .map(Staff::getStaffId)
                .collect(Collectors.toList());
    }


    @Override
    public StoreDTO createStore(StoreDTO dto) {
        Store store = new Store();
        if (dto.getAddressId() != null) {
            Address address = addressRepo.findById(dto.getAddressId())
                    .orElseThrow(() -> new NotFoundException("Address not found"));
            store.setAddress(address);
        }
        if (dto.getManagerStaffId() != null) {
            Staff manager = staffRepo.findById(dto.getManagerStaffId())
                    .orElseThrow(() -> new NotFoundException("Manager not found"));
            store.setManagerStaff(manager);
        }

        // ✅ Set lastUpdate before saving
        store.setLastUpdate(java.time.LocalDateTime.now());

        repo.save(store);

        StoreDTO response = new StoreDTO();
        response.setStoreId(store.getStoreId());
        response.setManagerStaffId(store.getManagerStaff() != null ? store.getManagerStaff().getStaffId() : null);
        response.setAddressId(store.getAddress() != null ? store.getAddress().getAddressId() : null);
        return response;

    }

    @Override
    public StoreDTO findByPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
        List<Store> stores = repo.findByAddress_Phone(phone);
        if (stores == null || stores.isEmpty()) {
            throw new NotFoundException("Store not found with phone: " + phone);
        }
        Store store = stores.get(0);
        StoreDTO d = new StoreDTO();
        d.setStoreId(store.getStoreId());
        d.setManagerStaffId(store.getManagerStaff() != null ? store.getManagerStaff().getStaffId() : null);
        d.setAddressId(store.getAddress() != null ? store.getAddress().getAddressId() : null);
        return d;
    }
}
package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.StaffCreateDTO;
import com.capgemini.film_rental.dto.StaffDTO;
import com.capgemini.film_rental.entity.Address;
import com.capgemini.film_rental.entity.Staff;
import com.capgemini.film_rental.exception.NotFoundException;
import com.capgemini.film_rental.repository.IAddressRepository;
import com.capgemini.film_rental.repository.IStaffRepository;
import com.capgemini.film_rental.repository.IStoreRepository;
import com.capgemini.film_rental.service.IStaffService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

    private final IStaffRepository staffRepo;
    private final IAddressRepository addressRepo;
    private final IStoreRepository storeRepo;

    public StaffServiceImpl(IStaffRepository staffRepo, IAddressRepository addressRepo, IStoreRepository storeRepo) {
        this.staffRepo = staffRepo;
        this.addressRepo = addressRepo;
        this.storeRepo = storeRepo;
    }

    private Staff get(int id) {
        return staffRepo.findById(id).orElseThrow(() -> new NotFoundException("Staff not found: " + id));
    }

    private StaffDTO toDTO(Staff s) {
        StaffDTO d = new StaffDTO();
        d.setStaffId(s.getStaffId());
        d.setFirstName(s.getFirstName());
        d.setLastName(s.getLastName());
        d.setEmail(s.getEmail());
        d.setAddressId(s.getAddress() != null ? s.getAddress().getAddressId() : null);
        d.setStoreId(s.getStore() != null ? s.getStore().getStoreId() : null);
        d.setUsername(s.getUsername());
        return d;
    }

    @Override
    public String create(StaffCreateDTO dto) {
        Staff s = new Staff();
        s.setFirstName(dto.getFirstName());
        s.setLastName(dto.getLastName());
        if (dto.getAddressId() != null) s.setAddress(addressRepo.findById(dto.getAddressId()).orElseThrow(() -> new NotFoundException("Address not found")));
        if (dto.getStoreId() != null) s.setStore(storeRepo.findById(dto.getStoreId()).orElseThrow(() -> new NotFoundException("Store not found")));
        s.setEmail(dto.getEmail());
        s.setUsername(dto.getUsername());
        // password handled elsewhere (security). For now store raw if present (not recommended).
        s.setPassword(dto.getPassword());
        staffRepo.save(s);
        return "Record Created Successfully";
    }

    @Override
    public StaffDTO updateLastName(int id, String ln) {
        Staff s = get(id);
        s.setLastName(ln);
        staffRepo.save(s);
        return toDTO(s);
    }

    @Override
    public StaffDTO updateFirstName(int id, String fn) {
        Staff s = get(id);
        s.setFirstName(fn);
        staffRepo.save(s);
        return toDTO(s);
    }

    @Override
    public StaffDTO updateEmail(int id, String email) {
        Staff s = get(id);
        s.setEmail(email);
        staffRepo.save(s);
        return toDTO(s);
    }

    @Override
    public java.util.List<StaffDTO> findByPhone(String phone) {
        return staffRepo.findByPhone(phone).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public java.util.List<StaffDTO> findByCountry(String country) {
        return staffRepo.findByCountry(country).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public java.util.List<StaffDTO> findByFirstName(String fn) {
        return staffRepo.findByFirstNameContainingIgnoreCase(fn).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public java.util.List<StaffDTO> findByLastName(String ln) {
        return staffRepo.findByLastNameContainingIgnoreCase(ln).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public java.util.List<StaffDTO> findByEmail(String email) {
        return staffRepo.findByEmail(email).stream().map(this::toDTO).collect(Collectors.toList());
    }


    @Override
    public StaffDTO updatePhone(int id, String phone) {
        Staff s = get(id);
        if (s.getAddress() == null) {
            throw new NotFoundException("Address not found for staff: " + id);
        }
        s.getAddress().setPhone(phone);
        staffRepo.save(s);
        return toDTO(s);
    }

    @Override
    public List<StaffDTO> findByCity(String city) {
        return staffRepo.findByCity(city)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StaffDTO assignAddress(int staffId, int addressId) {
        Staff s = get(staffId);
        Address a = addressRepo.findById(addressId).orElseThrow(() -> new NotFoundException("Address not found"));
        s.setAddress(a);
        Staff saved = staffRepo.save(s);
        return toDTO(saved);
    }

    @Override
    public StaffDTO assignStore(int staffId, int storeId) {
        Staff s = get(staffId);
        var store = storeRepo.findById(storeId).orElseThrow(() -> new NotFoundException("Store not found"));
        s.setStore(store);
        Staff saved = staffRepo.save(s);
        return toDTO(saved);
    }

}
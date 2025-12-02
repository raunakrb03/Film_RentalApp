package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.CustomerCreateDTO;
import com.capgemini.film_rental.dto.CustomerDTO;
import com.capgemini.film_rental.entity.Address;
import com.capgemini.film_rental.entity.Customer;
import com.capgemini.film_rental.entity.Store;
import com.capgemini.film_rental.exception.NotFoundException;
import com.capgemini.film_rental.repository.IAddressRepository;
import com.capgemini.film_rental.repository.ICustomerRepository;
import com.capgemini.film_rental.repository.IStoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository repo;
    private final IStoreRepository storeRepo;
    private final IAddressRepository addressRepo;

    public CustomerServiceImpl(ICustomerRepository repo, IStoreRepository storeRepo, IAddressRepository addressRepo) {
        this.repo = repo;
        this.storeRepo = storeRepo;
        this.addressRepo = addressRepo;
    }

    private Customer get(int id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Customer not found: " + id));
    }

    private Address getAddr(int id) {
        return addressRepo.findById(id).orElseThrow(() -> new NotFoundException("Address not found: " + id));
    }

    private Store getStore(int id) {
        return storeRepo.findById(id).orElseThrow(() -> new NotFoundException("Store not found: " + id));
    }

    private CustomerDTO toDTO(Customer c) {
        CustomerDTO d = new CustomerDTO();
        d.setCustomerId(c.getCustomerId());
        d.setStoreId(c.getStore() != null ? c.getStore().getStoreId() : null);
        d.setFirstName(c.getFirstName());
        d.setLastName(c.getLastName());
        d.setEmail(c.getEmail());
        d.setAddressId(c.getAddress() != null ? c.getAddress().getAddressId() : null);
        d.setActive(c.getActive());
        return d;
    }

    @Override
    public CustomerDTO createCustomer(CustomerCreateDTO dto) {
        Customer c = new Customer();
        c.setFirstName(dto.getFirstName());
        c.setLastName(dto.getLastName());
        c.setEmail(dto.getEmail());
        c.setActive(dto.getActive() != null ? dto.getActive() : true);
        c.setCreateDate(java.time.LocalDateTime.now());

        if (dto.getStoreId() != null) {
            c.setStore(getStore(dto.getStoreId()));
        }

        if (dto.getAddressId() != null) {
            c.setAddress(getAddr(dto.getAddressId()));
        }

        Customer saved = repo.save(c);
        return toDTO(saved);
    }

    @Override
    public CustomerDTO assignStore(int id, int storeId) {
        Customer c = get(id);
        c.setStore(getStore(storeId));
        return toDTO(repo.save(c));
    }

    @Override
    public java.util.List<CustomerDTO> findByPhone(String phone) {
        return repo.findByPhone(phone).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public java.util.List<CustomerDTO> findByCity(String city) {
        return repo.findByCity(city).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public java.util.List<CustomerDTO> findByCountry(String country) {
        return repo.findByCountry(country).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO assignAddress(int id, int addressId) {
        Customer c = get(id);
        c.setAddress(getAddr(addressId));
        return toDTO(repo.save(c));
    }

    @Override
    public java.util.List<CustomerDTO> findByEmail(String email) {
        return repo.findByEmailIgnoreCase(email).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updatePhone(int id, String phone) {
        Customer c = get(id);
        Address a = c.getAddress();
        if (a == null) throw new NotFoundException("Customer has no address");
        a.setPhone(phone);
        addressRepo.save(a);
        return toDTO(c);
    }

    @Override
    public CustomerDTO updateFirstName(int id, String firstName) {
        Customer c = get(id);
        c.setFirstName(firstName);
        return toDTO(repo.save(c));
    }

    @Override
    public CustomerDTO updateLastName(int id, String lastName) {
        Customer c = get(id);
        c.setLastName(lastName);
        return toDTO(repo.save(c));
    }

    @Override
    public CustomerDTO getCustomerByNumber(String phone) {
        return repo.findByPhone(phone).stream().findFirst().map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Customer not found for phone: " + phone));
    }

    @Override
    public CustomerDTO updateEmail(int id, String email) {
        Customer c = get(id);
        c.setEmail(email);
        return toDTO(repo.save(c));
    }

    @Override
    public java.util.List<CustomerDTO> findInactiveCustomers() {
        return repo.findByActiveFalse()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public java.util.List<CustomerDTO> findActiveCustomers() {
        return repo.findByActiveTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findByFirstName(String firstName) {
        return repo.findByFirstNameIgnoreCase(firstName)
                .stream()
                .map(this::toDTO)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Customer not found: " + firstName));
    }

    @Override
    public java.util.List<CustomerDTO> findByLastName(String lastName) {
        return repo.findByLastNameIgnoreCase(lastName)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }





}
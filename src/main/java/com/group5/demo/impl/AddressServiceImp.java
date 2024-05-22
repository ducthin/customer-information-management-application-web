package com.group5.demo.impl;

import com.group5.demo.dao.AddressRepository;
import com.group5.demo.model.Address;
import com.group5.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImp implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address saveAddress(Address requestAddress) {

        return addressRepository.save(requestAddress);
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address findAddress(String Street) {
        return addressRepository.findByStreet(Street);
    }

    @Override
    public void delete(int id) {
        addressRepository.deleteById(id);
    }

    @Override
    public boolean isAddress(String street) {
        Address address = findAddress(street);
        return address != null;
    }
}

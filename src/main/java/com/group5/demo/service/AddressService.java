package com.group5.demo.service;

import com.group5.demo.model.Address;

import java.util.List;

public interface AddressService {

    Address saveAddress(Address requestAddress);

    List<Address> getAllAddress();

    Address findAddress(String Street);

    void delete(int address);

    boolean isAddress(String street);
}

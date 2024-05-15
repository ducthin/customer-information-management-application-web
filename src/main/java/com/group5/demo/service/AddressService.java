package com.group5.demo.service;

import com.group5.demo.model.Address;
import com.group5.demo.request.RequestAddress;

import java.util.List;

public interface AddressService {

    public Address saveAddress(Address requestAddress);

    public List<Address> getAllAddress();

    public Address findAddress(String Street);

    public void delete(int address);

    public boolean isAddress(String street);
}

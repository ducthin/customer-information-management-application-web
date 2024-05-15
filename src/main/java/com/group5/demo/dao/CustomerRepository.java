package com.group5.demo.dao;

import com.group5.demo.model.Customers;
import com.group5.demo.role.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customers, Integer> {
    Optional<Customers> findByEmail(String email);

    Customers findByRole(Role role);
    Customers findByOtp(int otp);

//    List<Customers> findByRole(Role role);
    List<Customers> findByPhoneContaining(String phone);
    @Transactional
    @Modifying
    @Query("update Customers c set c.enable = true where c.email=?1")
    void enableCustomer(String email);
}

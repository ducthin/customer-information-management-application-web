package com.group5.demo;

import com.group5.demo.dao.AddressRepository;
import com.group5.demo.dao.CustomerRepository;
import com.group5.demo.dao.ProductRepository;
import com.group5.demo.impl.UserServiceImp;
import com.group5.demo.model.Address;
import com.group5.demo.model.Customers;
import com.group5.demo.model.Product;
import com.group5.demo.role.Role;
import com.group5.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserServiceImp userServiceImp;
	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("halhalsdasdsads");
		Customers customer = customerRepository.findByRole(Role.ADMIN);
		Customers customerUer = customerRepository.findByRole(Role.USER);
		if(customer == null)
		{
			Customers customer1 = new Customers();
			customer1.setRole(Role.ADMIN);
			customer1.setPhone("12345");
			customer1.setFirstName("Le ");
			customer1.setEmail("leto2004@gmail.com");
			customer1.setLastName("To");
			customer1.setPassword(passwordEncoder.encode("12345"));
			customer1.setEnable(true);

			customerService.inMemoryUserDetailsManager(customer1);
			customerRepository.save(customer1);
		}
		if(customerUer == null)
		{
			Customers customer1 = new Customers();
			customer1.setRole(Role.USER);
			customer1.setPhone("12345");
			customer1.setFirstName("Le ");
			customer1.setEmail("trung@gmail.com");
			customer1.setLastName("adas");
			customer1.setPassword(passwordEncoder.encode("12345"));
			customer1.setEnable(true);

			customerService.inMemoryUserDetailsManager(customer1);
			customerRepository.save(customer1);
		}
	}
}

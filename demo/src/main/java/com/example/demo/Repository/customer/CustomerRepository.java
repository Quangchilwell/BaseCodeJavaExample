package com.example.demo.Repository.customer;

import com.example.demo.Entity.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerInfo, Long>, CustomerRepositoryCustom {

}

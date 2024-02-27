package com.example.demo.Service.customer.impl;

import com.example.demo.Model.CustomerDTO;
import com.example.demo.Repository.customer.CustomerRepository;
import com.example.demo.Service.customer.CustomerService;
import com.example.demo.Utils.SqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private SqlUtils sqlUtils;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> getInfo() {
        HashMap<String, Object> params = new HashMap<>();
        String sql0 = "SELECT * FROM CUSTOMER_INFO WHERE CARD_ID = :cardId ";
        params.put("cardId", "001201013266");

//        String sql = "SELECT * FROM CUSTOMER_INFO WHERE CARD_ID = '001201013266'";
        CustomerDTO customerDTOS = sqlUtils.sqlQueryModel().queryForObject(sql0, params, CustomerDTO.class);

        return null;
    }
}

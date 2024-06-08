package com.CrudOpration.MyCrud.service;

import com.CrudOpration.MyCrud.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    String saveCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    List<CustomerDTO> getAllCustomersById(int customerID);


    String DeleteCustomersById(int customerID);

    String UpdateCustomersById(CustomerDTO customerDTO);
}

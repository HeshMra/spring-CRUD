package com.CrudOpration.MyCrud.service.IMPL;

import com.CrudOpration.MyCrud.dto.CustomerDTO;
import com.CrudOpration.MyCrud.entity.Customer;
import com.CrudOpration.MyCrud.repo.CustomerRepo;
import com.CrudOpration.MyCrud.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class); //CUSTOMER DTO ,entity CLASS EKAKATA CONVERT krnawa
        if (!customerRepo.existsById(customer.getCustomerId())) {
            customerRepo.save(customer);
            return customer.getCustomerId() + " saved successfully";
        } else {
            throw new DuplicateKeyException("Already Added");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        if (!customers.isEmpty()) {
            List<CustomerDTO> customerDTO = customers.stream()
                    .map(customer -> modelMapper.map(customer, CustomerDTO.class)) //Customer enity , DTO walata convert krnawa gaddi
                    .collect(Collectors.toList());
            return customerDTO;
        } else {
            throw new RuntimeException("No Customers Found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomersById(int customerID) {
        Customer customer = customerRepo.findById(customerID)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerID));

        return Collections.singletonList(modelMapper.map(customer, CustomerDTO.class)); //Customer enity , DTO walata convert krnawa gaddi
    }

    @Override
    public String DeleteCustomersById(int customerID) {
        if(customerRepo.existsById(customerID)){
            customerRepo.deleteById(customerID);
            return "deleted successfully" + customerID;
        }else{
            throw new RuntimeException("No Customer Found In That Id");
        }
    }

    @Override
    public String UpdateCustomersById(CustomerDTO customerDTO) {
        int customerId = customerDTO.getCustomerId();

        if (customerRepo.existsById(customerId)) {
            Customer customerToUpdate = customerRepo.findById(customerId).orElse(null);

            if (customerToUpdate != null) {

                // Map fields from customerDTO to customerToUpdate
                modelMapper.map(customerDTO, customerToUpdate); //convert dto to entity

                // Save the updated customer to the repository
                customerRepo.save(customerToUpdate);

                return customerDTO.getCustomerName() + " Updated Successfully";
            } else {
                throw new RuntimeException("Failed to find Customer with ID: " + customerId);
            }
        } else {
            throw new RuntimeException("No data found for that ID: " + customerId);
        }
    }


}

package com.CrudOpration.MyCrud.controller;

import com.CrudOpration.MyCrud.dto.CustomerDTO;
import com.CrudOpration.MyCrud.service.CustomerService;
import com.CrudOpration.MyCrud.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //Save CUSTOMER
    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveCustomer(@RequestBody CustomerDTO customerDTO){
        String message = customerService.saveCustomer(customerDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message),
                HttpStatus.CREATED
        );
    }

    //GET-ALL-CUSTOMERS
    @GetMapping("/get-all")
    public ResponseEntity<StandardResponse> getAllCustomers(){
        List<CustomerDTO> allCustomers=customerService.getAllCustomers();

        return new ResponseEntity<StandardResponse> (
                new StandardResponse(200,"Success",allCustomers),
                HttpStatus.OK
        );
    }

    //GET-CUSTOMER-BY-ID
    @GetMapping(path="/get-by-id", params = "id")

    public ResponseEntity<StandardResponse> getAllCustomersById(@RequestParam(value = "id")int CustomerID){
        List<CustomerDTO> allCustomersid=customerService.getAllCustomersById(CustomerID);

        return new ResponseEntity<StandardResponse> (
                new StandardResponse(200,"Success",allCustomersid),
                HttpStatus.OK
        );
    }

    //DELETE-CUSTOMER-BY-ID
    @DeleteMapping(path="/delete-customer", params = "id")
    public ResponseEntity<StandardResponse> DeleteCustomersById(@RequestParam(value = "id")int CustomerID){
       String deleted=customerService.DeleteCustomersById(CustomerID);

        return new ResponseEntity<StandardResponse> (
                new StandardResponse(200,"Success",deleted),
                HttpStatus.OK
        );
    }

    //UPDATE-CUSTOMER-BY-ID
    @PutMapping(path="/update-customer", params = "id")
    public ResponseEntity<StandardResponse> UpdateCustomersById(@RequestBody CustomerDTO customerDTO , @RequestParam(value = "id")int CustomerID){
        String message=customerService.UpdateCustomersById(customerDTO);

        return new ResponseEntity<StandardResponse> (
                new StandardResponse(201,"Success",message),
                HttpStatus.CREATED
        );
    }


}

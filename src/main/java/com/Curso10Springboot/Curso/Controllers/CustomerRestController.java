package com.Curso10Springboot.Curso.Controllers;

import com.Curso10Springboot.domain.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@RestController
@RequestMapping("/clientes")
public class CustomerRestController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
        new Customer(1, "John Doe", "johndoe", "password123"),
        new Customer(2, "Jane Smith", "janesmith", "password456"),
        new Customer(3, "Alice Johnson", "alicej", "password789"),
        new Customer(4, "Bob Brown",  "bobb", "password101112"  ),
        new Customer(5, "Jonathan Uribe", "juribe", "password131415"  )
    ));

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Customer> getAllCustomers() {

        return customers;
    }




    @GetMapping("/id/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        return customers.stream()
                .filter(customer -> customer.getID() == id)
                .findFirst()
                .orElse(null);
    }


    // @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    @GetMapping("/username/{username}")
    public Customer getCustomerByUsername(@PathVariable String username) {
        return customers.stream()
                .filter(customer -> customer.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }


    // Endpoint to add a new customer method
     @PostMapping
    //@RequestMapping(method = RequestMethod.POST)
    public Customer postCliente( @RequestBody  Customer customer) {
        customers.add(customer);
        return customer;

    }



    @PutMapping
    public Customer putCliente( @RequestBody Customer customer ) {
      for (Customer c : customers) {
            if (c.getID() == customer.getID()) {
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());
                return c;
            }
        }
        return null; // If customer not found
    }



    @DeleteMapping("/{id}")
    public String deleteCliente(@PathVariable int id) {
        boolean removed = customers.removeIf(customer -> customer.getID() == id);

        if (removed) {
            return "Customer with ID " + id + " deleted successfully.";
        } else {
            return "Customer with ID " + id + " not found.";
        }
    }

    @PatchMapping
    public Customer patchCliente(@RequestBody Customer customer) {
        for (Customer c : customers) {
            if (c.getID() == customer.getID()) {
                if (customer.getName() != null) {
                    c.setName(customer.getName());
                }
                if (customer.getUsername() != null) {
                    c.setUsername(customer.getUsername());
                }
                if (customer.getPassword() != null) {
                    c.setPassword(customer.getPassword());
                }
                return c;
            }
        }
        return null; // If customer not found
    }








}

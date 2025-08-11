package com.Curso10Springboot.Curso.Controllers;

import com.Curso10Springboot.Curso.domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity <List<Customer>> getAllCustomers() {

        return ResponseEntity.ok(customers);
    }




    @GetMapping("/id/{id}")
    public ResponseEntity <Customer> getCustomerById(@PathVariable int id) {
        return ResponseEntity.ok(
                customers.stream()
                        .filter(customer -> customer.getID() == id)
                        .findFirst()
                        .orElse(null)
        );
    }


    // @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    @GetMapping("/username/{username}")
    public ResponseEntity<?>  getCustomerByUsername(@PathVariable String username) {
     for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return ResponseEntity.ok(customer);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( "Clente not found with username: " + username);
    }


    // Endpoint to add a new customer method
     @PostMapping
    //@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> postCliente( @RequestBody  Customer customer) {
        customers.add(customer);
        //apliquemos buenas practicas
       // return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully: " + customer.getName());

         URI location = ServletUriComponentsBuilder
                 .fromCurrentRequest()
                 .path("/{username}")
                 .buildAndExpand(customer.getUsername())
                 .toUri();

         //return ResponseEntity.created(location).build();
         return ResponseEntity.created(location).body(customer);
    }



    @PutMapping
    public ResponseEntity<?> putCliente( @RequestBody Customer customer ) {
      for (Customer c : customers) {
            if (c.getID() == customer.getID()) {
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());
               //return ResponseEntity.ok( "Customer updated successfully: " + customer.getID());
                //simplificada
                return ResponseEntity.noContent().build();
            }
        }
        //return null; // If customer not found
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with ID: " + customer.getID());
        return ResponseEntity.noContent().build();


    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteCliente(@PathVariable int id) {
       for (Customer c : customers) {
            if (c.getID() == id) {
                customers.remove(c);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body( "Customer deleted successfully with ID: " + id);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with ID: " + id);
    }

    @PatchMapping
    public ResponseEntity<?> patchCliente(@RequestBody Customer customer) {
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
                return ResponseEntity.ok("Customer updated successfully: " + customer.getID());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with ID: " + customer.getUsername());
    }








}

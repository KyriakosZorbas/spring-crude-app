/**
 * Created by Kyriakos Zorbas on 04/07/21.
 */

package com.kyriakos.controller;

import com.kyriakos.model.Customer;
import com.kyriakos.model.CustomerOrder;
import com.kyriakos.model.Product;
import com.kyriakos.pojo.order.OrderRequest;
import com.kyriakos.repository.CustomerRepository;
import com.kyriakos.repository.OrderRepository;
import com.kyriakos.repository.ProductRepository;
import com.kyriakos.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;


@RestController
@RequestMapping("/api")
public class OrdersController {

    public static final Logger logger = LoggerFactory.getLogger(OrdersController.class);
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;


    // -------------------Retrieve All Orders--------------------------------------------

    @RequestMapping(value = "/order/", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerOrder>> listAllOrders() {
        List<CustomerOrder> orders = (List<CustomerOrder>) orderRepository.findAll();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    // -------------------Create an Order-------------------------------------------

    @RequestMapping(value = "/order/", method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        logger.info("Creating Order : {}", orderRequest);

        try {
            CustomerOrder customerOrder = new CustomerOrder();
            Customer customer = new Customer();

            customer.setEmail(orderRequest.getCustomer().getEmail());
            customerRepository.save(customer);
            customerOrder.setCustomer(customerRepository.findOne(customer.getCustomerId()));

            Set<Product> productSet = new HashSet<Product>();

            Iterator it = orderRequest.getProducts().listIterator();
            Double total = 0.0;

            while (it.hasNext()) {
                com.kyriakos.pojo.order.Product product = (com.kyriakos.pojo.order.Product) it.next();
                total = total + (productRepository.findOne(product.getProductId()).getProductPrice());
                productSet.add(productRepository.findOne(product.getProductId()));

            }

            if (total > 0) {
                total = Math.round(total * 100.0) / 100.0;
            }

            customerOrder.setProducts(productSet);
            customerOrder.setTotal(total);

            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            customerOrder.setTimestamp(timestamp.getTime());


            orderRepository.save(customerOrder);

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception ex) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create the product "), HttpStatus.BAD_REQUEST);
        }


    }


    // -------------------Retrieve Order Based on Date--------------------------------------------

    @RequestMapping(value = "/order/search/", method = RequestMethod.POST)
    public ResponseEntity<?> searchOrdersByDate(@RequestBody com.kyriakos.pojo.order.Date date) {
        logger.info("Searching Orders in this time period : {}", date);

        try {

            List<CustomerOrder> ordersFiltered = new ArrayList<>();

            List<CustomerOrder> orders = (List<CustomerOrder>) orderRepository.findAll();
            if (orders.isEmpty()) {
                return new ResponseEntity<>(orders, HttpStatus.NOT_FOUND);
            }

            Iterator it = orders.listIterator();
            while (it.hasNext()) {
                CustomerOrder customerOrder = (CustomerOrder) it.next();
                // If the timestamp in the order is in the given time period we add it to results
                if (customerOrder.getTimestamp() >= date.getFrom() && customerOrder.getTimestamp() <= date.getTo()) {
                    ordersFiltered.add(customerOrder);
                }
            }

            if (ordersFiltered.isEmpty()) {
                return new ResponseEntity<>(new CustomErrorType("There aren't orders within the given time period."), HttpStatus.NO_CONTENT);

            } else {
                return new ResponseEntity<>(ordersFiltered, HttpStatus.OK);
            }

        } catch (Exception ex) {
            return new ResponseEntity<>(new CustomErrorType("An error has occurred , please try again later"), HttpStatus.BAD_REQUEST);
        }


    }


}
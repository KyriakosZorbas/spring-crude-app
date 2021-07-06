package com.kyriakos.endpoints.unit.controller;


import com.kyriakos.model.Customer;
import com.kyriakos.model.CustomerOrder;
import com.kyriakos.model.Product;
import com.kyriakos.repository.CustomerRepository;
import com.kyriakos.repository.OrderRepository;
import com.kyriakos.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)


@SpringBootTest
public class CreateOrderTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Test
    public void saveTest() {

        Product product1 = new Product();
        product1.setProductName("Mojito");
        product1.setProductPrice(10.0);

        Product product2 = new Product();
        product2.setProductName("Margarita");
        product2.setProductPrice(25.0);

        productRepository.save(product1);
        productRepository.save(product2);

        Set<Product> productSet = new HashSet<Product>();


        CustomerOrder customerOrder = new CustomerOrder();
        Customer customer = new Customer();
        customer.setEmail("test@test.com");
        customerRepository.save(customer);
        customerOrder.setCustomer(customerRepository.findOne(customer.getCustomerId()));


        Double total = product1.getProductPrice() + product2.getProductPrice();
        total = Math.round(total * 100.0) / 100.0;


        customerOrder.setProducts(productSet);
        customerOrder.setTotal(total);


        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        customerOrder.setTimestamp(timestamp.getTime());

        orderRepository.save(customerOrder);


        long savedOrderId = customerOrder.getOrderId();

        System.out.println("savedOrderId: "+savedOrderId);



        /* Check if customer is created */
        Assert.assertNotNull(customerRepository.findOne(customer.getCustomerId()));
        /* Customer's email inside customer repository should be : test@test.com*/
        Assert.assertEquals("test@test.com", customerRepository.findOne(customer.getCustomerId()).getEmail());


        /* Customer's email inside order repository should be : test@test.com*/
        Assert.assertEquals("test@test.com", orderRepository.findOne(savedOrderId).getCustomer().getEmail());

        /* Check if order is created */
        Assert.assertNotNull(orderRepository.findOne(savedOrderId));

        /* Total price of the order should be : 35.0*/
        Assert.assertEquals(35.0, orderRepository.findOne(savedOrderId).getTotal(), 1);



    }
}

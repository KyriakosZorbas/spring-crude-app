/**
 * Created by Kyriakos Zorbas on 04/07/21.
 */
package com.kyriakos;

import com.kyriakos.model.Product;
import com.kyriakos.repository.CustomerRepository;
import com.kyriakos.repository.OrderRepository;
import com.kyriakos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCrudApplication implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;


    public static void main(String[] args) {
        SpringApplication.run(SpringCrudApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {

        //Initialize database with some products

        Product mojito = new Product();
        mojito.setProductName("Mojito");
        mojito.setProductPrice(10.95);

        Product margarita = new Product();
        margarita.setProductName("Margarita");
        margarita.setProductPrice(4.95);

        productRepository.save(mojito);
        productRepository.save(margarita);



    }


}

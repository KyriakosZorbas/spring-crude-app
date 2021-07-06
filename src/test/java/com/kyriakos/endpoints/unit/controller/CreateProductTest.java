package com.kyriakos.endpoints.unit.controller;


import com.kyriakos.model.Product;
import com.kyriakos.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)


@SpringBootTest
public class CreateProductTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void saveTest() {


        Product product = new Product();
        product.setProductPrice(10.0);
        product.setProductName("Test Name");

        productRepository.save(product);

        long savedProductId = product.getProductId();

        /* Check if product is created */
        Assert.assertNotNull(productRepository.findOne(savedProductId));

        /* Name of the product should be : 35.0 */
        Assert.assertEquals("Test Name", productRepository.findOne(savedProductId).getProductName());

        /* Price of the product should be : 10.0 */
        Assert.assertEquals(10.0, productRepository.findOne(savedProductId).getProductPrice(), 1);

    }
}

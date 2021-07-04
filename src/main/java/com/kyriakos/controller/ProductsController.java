/**
 * Created by Kyriakos Zorbas on 04/07/21.
 */

package com.kyriakos.controller;

import com.kyriakos.model.Product;
import com.kyriakos.repository.ProductRepository;
import com.kyriakos.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ProductsController {

    public static final Logger logger = LoggerFactory.getLogger(ProductsController.class);
    @Autowired
    ProductRepository productRepository;


    // -------------------Retrieve All Products--------------------------------------------

    @RequestMapping(value = "/product/", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    // -------------------Retrieve Single Product------------------------------------------

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable("id") long id) {
        logger.info("Fetching Product with id {}", id);
        Product product = productRepository.findOne(id);
        if (product == null) {
            logger.error("Product with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Product with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    // -------------------Create a Product-------------------------------------------

    @RequestMapping(value = "/product/", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        logger.info("Creating Product : {}", product);

        try {
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create the product "), HttpStatus.BAD_REQUEST);
        }

    }


    // ------------------- Update a Product ------------------------------------------------

    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        logger.info("Updating Product with id {}", id);

        Product currentProduct = productRepository.findOne(id);

        if (currentProduct == null) {
            logger.error("Unable to update. Product with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to upate. Product with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentProduct.setProductName(product.getProductName());
        currentProduct.setProductPrice(product.getProductPrice());

        productRepository.save(currentProduct);
        return new ResponseEntity<>(currentProduct, HttpStatus.OK);
    }


    // ------------------- Delete a Product-----------------------------------------

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Product with id {}", id);

        Product product = productRepository.findOne(id);
        if (product == null) {
            logger.error("Unable to delete. Product with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Product with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        productRepository.delete(id);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }


}

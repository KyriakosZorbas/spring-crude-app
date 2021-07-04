/**
 * Created by Kyriakos Zorbas on 04/07/21.
 */
package com.kyriakos.repository;

import com.kyriakos.model.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Long> {

}





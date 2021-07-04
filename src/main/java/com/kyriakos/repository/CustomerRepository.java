/**
 * Created by Kyriakos Zorbas on 04/07/21.
 */
package com.kyriakos.repository;

import com.kyriakos.model.Customer;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer, Long> {

}

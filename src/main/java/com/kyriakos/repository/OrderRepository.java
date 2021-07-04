/**
 * Created by Kyriakos Zorbas on 04/07/21.
 */
package com.kyriakos.repository;

import com.kyriakos.model.CustomerOrder;
import org.springframework.data.repository.CrudRepository;


public interface OrderRepository extends CrudRepository<CustomerOrder, Long> {

}

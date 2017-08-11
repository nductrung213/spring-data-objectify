package test.simple;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import vn.khtt.data.objectify.repository.ObjectifyRepository;

public interface CustomerRepository extends ObjectifyRepository<Customer, Long> {
    List<Customer> findByAgeGreaterThan(int age);
    List<Customer> findByNameAndAge(String name, int age);
}

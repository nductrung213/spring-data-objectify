package test.simple;

import com.googlecode.objectify.ObjectifyService;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import test.BaseTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PagingAndSortingTests extends BaseTest {
    @Autowired
    private CustomerRepository customerRepository;

    public PagingAndSortingTests() {
        ObjectifyService.register(Customer.class);
    }

    @Test
    public void testSorting() throws Exception {
        customerRepository.save(Customer.alice);
        customerRepository.save(Customer.bob);
        customerRepository.save(Customer.tom);
        
        Sort sort = new Sort("age");
        List<Customer> customers = new ArrayList<Customer>();
        for (Customer customer : customerRepository.findAll(sort)){
            customers.add(customer);
        }
        Assert.assertEquals(customers.get(0), Customer.tom);
        Assert.assertEquals(customers.get(1), Customer.alice);
        Assert.assertEquals(customers.get(2), Customer.bob);
    }
    
    @Test
    public void testPaging() throws Exception {
        customerRepository.save(Customer.alice);
        customerRepository.save(Customer.bob);
        customerRepository.save(Customer.tom);

        PageRequest pageRequest = new PageRequest(0, 2);
        Page<Customer> page = customerRepository.findAll(pageRequest);
        System.out.println(page);
    }
}

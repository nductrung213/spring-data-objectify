package test.simple;

import com.googlecode.objectify.ObjectifyService;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import test.BaseTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudTests extends BaseTest {
    @Autowired
    private CustomerRepository personRepository;
    
    private Customer tom = new Customer("Tom", 25);
    private Customer bob = new Customer("Bob", 31);
    private Customer alice = new Customer("Alice", 29);

    public CrudTests() {
        ObjectifyService.register(Customer.class);
    }
    
    
    @Test
    public void testCrud() throws Exception {
        // Simple an entity
        personRepository.save(tom);
        Assert.assertTrue(personRepository.exists(tom.getId()));
        Customer p = personRepository.findOne(tom.getId());
        Assert.assertEquals(tom, p);
        
        // Save entities
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(bob);
        customers.add(alice);
        int count = 0;
        for (Customer c : personRepository.save(customers)){
            count++;
        }
        Assert.assertEquals(count, customers.size());
        
        // findAll
        count = 0;
        for (Customer c : personRepository.findAll()){
            count++;
        }
        Assert.assertEquals(count, 3);
        
        // findAll(Iterable<ID> ids)
        List<Long> ids = new ArrayList<Long>();
        ids.add(bob.getId());
        ids.add(alice.getId());
        count = 0;
        for (Customer c : personRepository.findAll(ids)){
            count++;
        }
        Assert.assertEquals(count, ids.size());
        
        // count()
        Assert.assertEquals(personRepository.count(), 3);
        
        // delete(ID id)
        personRepository.delete(tom.getId());
        Assert.assertEquals(personRepository.count(), 2);
        
        // delete(T entity)
        personRepository.delete(bob);
        Assert.assertEquals(personRepository.count(), 1);
        
        // delete(Iterable<? extends T> entities)
        personRepository.save(tom);
        personRepository.save(bob);
        personRepository.delete(customers);
        Assert.assertEquals(personRepository.count(), 1);

        personRepository.save(tom);
        personRepository.save(bob);
        personRepository.deleteAll();
        Assert.assertEquals(personRepository.count(), 0);
    }
}

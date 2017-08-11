package test.simple;

import com.googlecode.objectify.ObjectifyService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import test.BaseTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomQueryTests extends BaseTest {
    @Autowired
    private CustomerRepository personRepository;

    private Customer tom = new Customer("Tom", 25);
    private Customer bob = new Customer("Bob", 31);
    private Customer alice = new Customer("Alice", 29);

    public CustomQueryTests() {
        ObjectifyService.register(Customer.class);
    }

    @Test
    public void testFindByAgeGreaterThan() throws Exception {
        personRepository.save(tom);
        personRepository.save(bob);
        personRepository.save(alice);
        
        Assert.assertEquals(personRepository.findByAgeGreaterThan(30).size(), 1);
        Assert.assertEquals(personRepository.findByAgeGreaterThan(25).size(), 2);
    }

    @Test
    public void testFindByNameAndAge() throws Exception {
        personRepository.save(tom);
        personRepository.save(bob);
        personRepository.save(alice);

        Assert.assertEquals(personRepository.findByNameAndAge("Bob", 31).size(), 1);
        Assert.assertEquals(personRepository.findByNameAndAge("Alice", 29).size(), 1);
        Assert.assertEquals(personRepository.findByNameAndAge("Alice", 31).size(), 0);
    }
}

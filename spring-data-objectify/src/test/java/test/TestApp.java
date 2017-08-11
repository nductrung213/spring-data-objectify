package test;

import com.googlecode.objectify.ObjectifyService;

import com.googlecode.objectify.util.Closeable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import test.simple.Customer;
import test.simple.CustomerRepository;

import vn.khtt.data.objectify.config.EnableObjectifyRepositories;

@SpringBootApplication
@EnableObjectifyRepositories
public class TestApp {
    @Autowired
    private CustomerRepository repository;

    public TestApp() {
    }

    public static void main(String[] args) {
        ObjectifyService.register(Customer.class);
        Closeable closeable = ObjectifyService.begin();

        TestApp app = SpringApplication.run(TestApp.class, args).getBean(TestApp.class);

        app.repository.save(new Customer("Tom", 31));
        app.repository.save(new Customer("Chris", 33));
        app.repository.save(new Customer("Dave", 47));

        System.out.println(app.repository.findAll());
        
        closeable.close();
    }
}

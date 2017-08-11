package example;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SetupCustomerExampleData implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;
    
    public SetupCustomerExampleData() {
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectifyService.run(new VoidWork(){
            @Override
            public void vrun() {
                for (int i=1; i<5; i++){
                    customerRepository.save(new Customer("Customer " + i, 25));
                }
            }
        });
    }
}

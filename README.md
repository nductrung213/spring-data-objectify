[![Build Status](https://travis-ci.org/nhuttrung/spring-data-objectify.svg?branch=master)](https://travis-ci.org/nhuttrung/spring-data-objectify)

# spring-data-objectify
Spring Data module for AppEngine using Objectify

## Showcase
1. Run the example.
We provide a Spring Data Rest using Objectify repository
````
git clone https://github.com/nhuttrung/spring-data-objectify.git
cd spring-data-objectify
cd spring-data-objectify-example
gradle appengineRun
````

2. See the result
````
curl http://localhost:8080
````

Check the result look like below:
````
{
  "_links" : {
    "employees" : {
      "href" : "http://localhost:8080/employees"
    },
    "customers" : {
      "href" : "http://localhost:8080/customers{?page,size,sort}",
      "templated" : true
    },
    "profile" : {
      "href" : "http://localhost:8080/profile"
    }
  }
}
````

3. Install to local Maven
````
cd ..
cd spring-data-objectify
gradle install
````

## Usage
1. Create Entity class
````
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Customer {
    @Id
    private Long id;
  
    private String name;
  
    // Geters and Setters ...
}
````

2. Create Repository interface
````
import vn.khtt.data.objectify.repository.ObjectifyRepository;

public interface CustomerRepository extends ObjectifyRepository<Customer, Long> {}
````

3. Spring Boot app
Annotate with @EnableObjectifyRepositories

````
import vn.khtt.data.objectify.config.EnableObjectifyRepositories;

@SpringBootApplication
@EnableObjectifyRepositories
public class ExampleApp extends SpringBootServletInitializer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ExampleApp.class, args);
    }
    
    public ExampleApp() {
        ObjectifyService.register(Customer.class);
    }

    @Bean
    public FilterRegistrationBean objectifyFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ObjectifyFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
    
        return registration;
    }
}
````


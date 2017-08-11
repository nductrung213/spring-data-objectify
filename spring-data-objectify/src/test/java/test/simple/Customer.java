package test.simple;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Customer {
    public static final Customer tom = new Customer("Tom", 25);
    public static final Customer bob = new Customer("Bob", 31);
    public static final Customer alice = new Customer("Alice", 29);

    @Id
    private Long id;

    @Index
    private String name;

    @Index
    private int age;

    public Customer() {
    }
    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
    
    public String toString(){
        return name + " " + age;
    }
}

package io.github.navpil.jpatest;

import io.github.navpil.jpatest.jpa.Product;
import io.github.navpil.jpatest.repositories.ProductRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


        //http://cleancodejava.com/simple-spring-data-jpa-example/

        //Create Spring application context
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring.xml");
        ((org.springframework.jdbc.datasource.DriverManagerDataSource)ctx.getBean("dataSource")).setUrl("jdbc:hsqldb:mem://productDb2");
        ctx.refresh();

        System.out.println(((DriverManagerDataSource)ctx.getBean("dataSource")).getUrl());

        ProductRepository bean = ctx.getBean(ProductRepository.class);

        bean.save(new Product(1, "Television"));
        //Get service from context.
        ProductService productService = ctx.getBean(ProductService.class);

        //Add some items
//        productService.add(new Product(1, "Television"));
        productService.add(new Product(2, "Phone"));
        productService.addAll(Arrays.asList(
                new Product(3, "Peach"),
                new Product(4, "Strawberry"),
                new Product(5, "Melone"),
                new Product(6, "Onion")
        ));

        //Test entity listing
        System.out.println("findAll=" + productService.findAll());

        //Test specified find methods
        System.out.println("findByName is 'Peach': " + productService.findByNameIs("Peach"));
        System.out.println("findByNameContainingIgnoreCase 'on': " + productService.findByNameContainingIgnoreCase("on"));

        ctx.close();
    }
}

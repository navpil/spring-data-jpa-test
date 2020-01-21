package io.github.navpil.jpatest;

import io.github.navpil.jpatest.jpa.Product;
import io.github.navpil.jpatest.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void add(Product product) {
        productRepository.save(product);
    }

    @Transactional(readOnly=true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public void addAll(Collection<Product> products) {
        for (Product product : products) {
            productRepository.save(product);
        }
    }

    @Transactional(readOnly=true)
    public List<Product> findByNameIs(String name) {
        return productRepository.findByNameIs(name);
    }

    @Transactional(readOnly=true)
    public List<Product> findByNameContainingIgnoreCase(String searchString) {
        return productRepository.findByNameContainingIgnoreCase(searchString);
    }
}
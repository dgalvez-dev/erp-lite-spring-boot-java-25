package com.dgalvez.erp_lite;

import com.dgalvez.erp_lite.persistence.jpa.entities.ProductEntity;
import com.dgalvez.erp_lite.persistence.jpa.repositories.ProductRepository;
import com.dgalvez.erp_lite.persistence.mongo.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ErpLiteApplication implements CommandLineRunner {

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(ErpLiteApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        this.catalogRepository.findAll().forEach(System.out::println);
        this.productRepository.findAll().stream()
                .map(ProductEntity::getName)
                .forEach(System.out::println);
    }
}

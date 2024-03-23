package com.intraConnect.intraConnect.repositoy;

import com.intraConnect.intraConnect.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface ProductoRepository extends JpaRepository<Product, UUID> {
}

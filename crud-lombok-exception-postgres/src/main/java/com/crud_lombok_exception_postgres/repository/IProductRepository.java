package com.crud_lombok_exception_postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud_lombok_exception_postgres.entity.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{

}

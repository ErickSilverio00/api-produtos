package com.example.produto.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.produto.demo.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
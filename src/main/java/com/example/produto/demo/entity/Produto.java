package com.example.produto.demo.entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    @NotEmpty(message = "A descrição deve ser informada!")
    @Length(min = 5, max = 200, message = "A descrição deverá ter de 5 a 200 caracteres")
    private String descricao;
    @NotEmpty(message = "A unidade deve ser informada!")
    private String unidade;
    @NotEmpty(message = "O fornecedor deve ser informado!")
    @Length(min = 5, max = 200, message = "O fornecedor deverá ter de 5 a 200 caracteres")
    private String fornecedor;
    @NotEmpty(message = "O preço deve ser informado!")
    private Float preco;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

}

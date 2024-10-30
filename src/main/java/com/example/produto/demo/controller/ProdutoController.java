package com.example.produto.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.produto.demo.entity.Produto;
import com.example.produto.demo.repository.ProdutoRepository;
import com.example.produto.demo.responses.Response;

import jakarta.validation.Valid;

@RestController
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/produtos")
	public List<Produto> Get() {
		return produtoRepository.findAll();
	}

	@GetMapping("/produto/{id}")
	public ResponseEntity<Produto> GetById(@PathVariable long id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isPresent()) {
			return new ResponseEntity<>(produto.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/produto")
	public ResponseEntity<Response<Produto>> Post(@Valid @RequestBody Produto produto, BindingResult result) {
		Response<Produto> response = new Response<Produto>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		produtoRepository.save(produto);
		response.setData(produto);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/produto/{id}")
	public ResponseEntity<Response<Produto>> Update(@PathVariable long id, @Valid @RequestBody Produto produtoNovo,
			BindingResult result) {
		Optional<Produto> produtoAntigo = produtoRepository.findById(id);
		Response<Produto> response = new Response<Produto>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		if (produtoAntigo.isPresent()) {
			Produto produto = produtoAntigo.get();
			produto.setDescricao(produtoNovo.getDescricao());
			produto.setUnidade(produtoNovo.getUnidade());
			produto.setFornecedor(produtoNovo.getFornecedor());
			produto.setPreco(produtoNovo.getPreco());
			response.setData(produto);
			produtoRepository.save(produto);
			return ResponseEntity.ok(response);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/produto/{id}")
	public ResponseEntity<Void> Delete(@PathVariable long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			produtoRepository.delete(produto.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

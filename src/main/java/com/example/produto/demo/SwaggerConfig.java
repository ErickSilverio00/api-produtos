package com.example.produto.demo;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API de Produtos", version = "v1", description = "Documentação da API de Produtos"))
public class SwaggerConfig {

}

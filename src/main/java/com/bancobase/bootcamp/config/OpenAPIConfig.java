package com.bancobase.bootcamp.config;

import com.bancobase.bootcamp.services.CurrencyService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
public class OpenAPIConfig {
    private final CurrencyService currencyService;

    @Autowired
    public OpenAPIConfig(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    private void initializeCurrencies() {
        //  To initialize the currencies table with the API's data
        currencyService.setCurrencies();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        initializeCurrencies(); // Llamada al método para inicializar las monedas
        return new OpenAPI().info(new Info()
                .title("Bootcamp UADY-BASE")
                .version("v0.0.1")
                .description("Proyecto demo para el bootcamp UADY-BASE.")
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.bancobase.com/")
                )
        );
    }
}

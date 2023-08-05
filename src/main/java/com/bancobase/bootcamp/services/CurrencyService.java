package com.bancobase.bootcamp.services;

import com.bancobase.bootcamp.dto.CurrencyDTO;
import com.bancobase.bootcamp.dto.response.ExchangeRateResponse;
import com.bancobase.bootcamp.dto.response.Symbol;
import com.bancobase.bootcamp.dto.response.SymbolsNameResponse;
import com.bancobase.bootcamp.http.APIExchangeRateClient;
import com.bancobase.bootcamp.repositories.CurrencyRepository;
import com.bancobase.bootcamp.schemas.CurrencySchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    @GetMapping("/currency")
    public List<CurrencyDTO> getCurrencies() {
        return  currencyRepository.findAll().
                stream().map(CurrencyDTO::getFromSchema)
                .collect(Collectors.toList());
    }

    public void setCurrencies() {
        APIExchangeRateClient apiExchangeRateClient = new APIExchangeRateClient();
        ExchangeRateResponse exchangeRateResponse = apiExchangeRateClient.getExchangeRate();
        SymbolsNameResponse symbolsNameResponse = apiExchangeRateClient.getSymbolsName();
        if (currencyRepository.count() > 0) {
            currencyRepository.deleteAll();
        }

        Map<String, Double> exchangeRates = exchangeRateResponse.getRates();
        symbolsNameResponse.getSymbols().forEach((code, symbol) -> {
            String name = symbol.getDescription();
            Double value = exchangeRates.get(code);

            if (name != null && value != null) {
                CurrencySchema currencySchema = new CurrencySchema();
                currencySchema.setName(name);
                currencySchema.setSymbol(code);
                currencySchema.setValue(value);
                currencyRepository.save(currencySchema);
            }
        });

    }
}

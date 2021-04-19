package com.stock.market.StockMarketTool.controllers;

import com.stock.market.StockMarketTool.dtos.StocksIdxDTO;
import com.stock.market.StockMarketTool.exceptions.StockIdxControllerBadRequestException;
import com.stock.market.StockMarketTool.services.StocksIdxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class StockIdxController {

    private StocksIdxService StocksIdxService;


    @Autowired
    void dowJonesDataUploadService(StocksIdxService service) {
        this.StocksIdxService = service;
    }


    @PostMapping("/stocksIdxs/{stockSymbol}")
    public List<StocksIdxDTO> uploadCSVFile(@PathVariable String stocksSymbol) {
        if (stocksSymbol == null || stocksSymbol.isEmpty()) {
            String message = "Missing path param stocks symbol. Specify the value.";
            throw new StockIdxControllerBadRequestException(message);
        }
        return this.StocksIdxService.getStocksIdxBySymbol(stocksSymbol);
    }
}
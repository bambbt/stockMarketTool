package com.stock.market.StockMarketTool.controllers;

import com.stock.market.StockMarketTool.commands.SaveStockIdxCommand;
import com.stock.market.StockMarketTool.dtos.StocksIdxDTO;
import com.stock.market.StockMarketTool.exceptions.StockIdxControllerBadRequestException;
import com.stock.market.StockMarketTool.services.StocksIdxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class StockIdxController {

    private StocksIdxService stocksIdxService;


    @Autowired
    void dowJonesDataUploadService(StocksIdxService service) {
        this.stocksIdxService = service;
    }


    @GetMapping("/stocksIdxs/{stocksSymbol}")
    public List<StocksIdxDTO> fetchStocksBySymbol(@PathVariable String stocksSymbol) {
        if (stocksSymbol == null || stocksSymbol.isEmpty()) {
            String message = "Missing path param stocks symbol. Specify the value.";
            throw new StockIdxControllerBadRequestException(message);
        }
        return this.stocksIdxService.getStocksIdxBySymbol(stocksSymbol);
    }

    @PostMapping("/stocksIdxs/stockIdx")
    public StocksIdxDTO add(@RequestBody SaveStockIdxCommand command) {
        if (command.validate()) {
            return this.stocksIdxService.save(command);
        } else {
            String message = "The stock you are trying to save is invalid.";
            throw new StockIdxControllerBadRequestException(message);
        }
    }
}

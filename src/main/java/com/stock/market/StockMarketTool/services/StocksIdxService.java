package com.stock.market.StockMarketTool.services;

import com.stock.market.StockMarketTool.dtos.StocksIdxDTO;
import com.stock.market.StockMarketTool.model.StocksIdxItem;
import com.stock.market.StockMarketTool.repository.StocksIdxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StocksIdxService {

    private StocksIdxRepository stocksIdxRepository;

    @Autowired
    StocksIdxService(StocksIdxRepository repository) {
        this.stocksIdxRepository = repository;
    }


    public List<StocksIdxDTO> getStocksIdxBySymbol(String symbol) {
        List<StocksIdxItem> stocksIdxItems = this.stocksIdxRepository.findByStockSymbol(symbol);
        List<StocksIdxDTO> stocksIdxItemsDtos = new ArrayList<>();
        for (StocksIdxItem it : stocksIdxItems) {
            StocksIdxDTO stocksIdxDTO = StocksIdxDTO.builder()
                    .close(it.getClose())
                    .date(it.getDate())
                    .daysToNextDividend(it.getDaysToNextDividend())
                    .high(it.getHigh())
                    .low(it.getLow())
                    .open(it.getOpen())
                    .nextWeeksOpen(it.getNextWeeksOpen())
                    .nextWeeksClose(it.getNextWeeksClose())
                    .percentChangePrice(it.getPercentChangePrice())
                    .percentChangeVolumeOverLastWk(it.getPercentChangeVolumeOverLastWk())
                    .previousWeeksVolume(it.getPreviousWeeksVolume())
                    .percentReturnNextDividend(it.getPercentReturnNextDividend())
                    .quarter(it.getQuarter())
                    .volume(it.getVolume())
                    .percentChangeNextWeeksPrice(it.getPercentChangeNextWeeksPrice())
                    .stock(it.getStock())
                    .build();
            stocksIdxItemsDtos.add(stocksIdxDTO);
        }
        return stocksIdxItemsDtos;
    }
}

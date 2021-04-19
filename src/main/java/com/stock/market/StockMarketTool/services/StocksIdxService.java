package com.stock.market.StockMarketTool.services;

import com.stock.market.StockMarketTool.commands.SaveStockIdxCommand;
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

    public StocksIdxDTO save(SaveStockIdxCommand it) {
        StocksIdxItem stocksIdxItem = StocksIdxItem.builder()
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
        StocksIdxItem stocksIdxItem1 = this.stocksIdxRepository.save(stocksIdxItem);

        return StocksIdxDTO.builder().close(stocksIdxItem1.getClose())
                .date(stocksIdxItem1.getDate())
                .daysToNextDividend(stocksIdxItem1.getDaysToNextDividend())
                .high(stocksIdxItem1.getHigh())
                .low(stocksIdxItem1.getLow())
                .open(stocksIdxItem1.getOpen())
                .nextWeeksOpen(stocksIdxItem1.getNextWeeksOpen())
                .nextWeeksClose(stocksIdxItem1.getNextWeeksClose())
                .percentChangePrice(stocksIdxItem1.getPercentChangePrice())
                .percentChangeVolumeOverLastWk(stocksIdxItem1.getPercentChangeVolumeOverLastWk())
                .previousWeeksVolume(stocksIdxItem1.getPreviousWeeksVolume())
                .percentReturnNextDividend(stocksIdxItem1.getPercentReturnNextDividend())
                .quarter(stocksIdxItem1.getQuarter())
                .volume(stocksIdxItem1.getVolume())
                .percentChangeNextWeeksPrice(stocksIdxItem1.getPercentChangeNextWeeksPrice())
                .stock(stocksIdxItem1.getStock())
                .build();
    }
}

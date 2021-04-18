package com.stock.market.StockMarketTool.exceptions;

import com.stock.market.StockMarketTool.model.StocksIdxItem;
import com.stock.market.StockMarketTool.readers.CSVHelper;
import com.stock.market.StockMarketTool.repository.StocksIdxItemRepository;
import com.stock.market.StockMarketTool.services.CSVUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StocksIdxUploadService {


    private StocksIdxItemRepository stocksIdxItemRepository;

    @Autowired
    StocksIdxUploadService(StocksIdxItemRepository repository) {
        this.stocksIdxItemRepository = repository;
    }

    public void save(MultipartFile file) throws CSVUploadException {
        try {
            List<StocksIdxItem> dowJonesIndex = CSVHelper.csvToDowJonesIndex(file.getInputStream());
            stocksIdxItemRepository.saveAll(dowJonesIndex);
        } catch (IOException e) {
            throw new CSVUploadException("Failed to upload file. Check file type and content.");
        }
    }
}

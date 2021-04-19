package com.stock.market.StockMarketTool;

import com.stock.market.StockMarketTool.dtos.StocksIdxDTO;
import com.stock.market.StockMarketTool.services.StocksIdxService;
import com.stock.market.StockMarketTool.services.StocksIdxUploadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
@Transactional
public class StocksIdxServiceTest {


    @Autowired
    private StocksIdxUploadService stocksIdxUploadService;
    @Autowired
    private StocksIdxService stocksIdxService;

    @BeforeEach
    void uploadStockIdx() throws IOException {
        File sample = new File("src/test/resources/dow_jones_index.data");
        InputStream is = new FileInputStream(sample);
        MultipartFile file = new MockMultipartFile("dow_jones_index.data", is);
        stocksIdxUploadService.save(file);
    }


    @Test
    void successfullyQueryDataByStockSymbols() {
        //when
        List<StocksIdxDTO> stocksIdxDTOList = stocksIdxService.getStocksIdxBySymbol("AXP");
        Assert.state(stocksIdxDTOList.size() == 25, "Sucessfully fetched data!");
    }


}

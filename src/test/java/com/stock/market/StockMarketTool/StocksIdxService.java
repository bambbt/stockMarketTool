package com.stock.market.StockMarketTool;

import com.stock.market.StockMarketTool.exceptions.StocksIdxUploadService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@SpringBootTest
@Transactional
public class StocksIdxService {


    @Autowired
    private StocksIdxUploadService stocksIdxUploadService;
    private StocksIdxService stocksIdxService;

    @BeforeEach
    void uploadStockIdx() throws IOException {
        File sample = new File("src/test/resources/dow_jones_index.data");
        InputStream is = new FileInputStream(sample);
        MultipartFile file = new MockMultipartFile("dow_jones_index.data", is);
        stocksIdxUploadService.save(file);
    }


    void successfullyQueryDataByStockSymbols(){

        //when

    }


}

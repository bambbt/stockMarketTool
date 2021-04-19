package com.stock.market.StockMarketTool;

import com.stock.market.StockMarketTool.repository.StocksIdxRepository;
import com.stock.market.StockMarketTool.services.StocksIdxUploadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@SpringBootTest
@Transactional
public class DowJonesDataUploadServiceTest {

    @Autowired
    private StocksIdxUploadService service;
    @Autowired
    private StocksIdxRepository repository;

    @Test
    void successfullyStoresDowJonesData() throws IOException {
        File sample = new File("src/test/resources/dow_jones_index.data");
        InputStream is = new FileInputStream(sample);
        MultipartFile file = new MockMultipartFile("dow_jones_index.data", is);
        //when
        service.save(file);

        Assert.state(repository.findAll().size() > 0, "Data has been saved");
    }
}

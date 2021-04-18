package com.stock.market.StockMarketTool;


import com.stock.market.StockMarketTool.dtos.DowJonesIndexDTO;
import com.stock.market.StockMarketTool.readers.CSVHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SpringBootTest
public class DowJonesDataReaderTest {


    @Test
    void successfullyConvertCSVToDomainObject() throws IOException {
        File sample = new File("src/test/resources/dow_jones_index.data");


        List<DowJonesIndexDTO> records = CSVHelper.csvToDowJonesIndex(sample);
        try {
            Path path = Paths.get(sample.getAbsolutePath());
            Assert.state(Files.lines(path).count() - 1 == records.size(), "All lines have been converted!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

package com.stock.market.StockMarketTool.readers;

import com.stock.market.StockMarketTool.dtos.DowJonesIndexDTO;
import jdk.vm.ci.meta.Local;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {


    private static String ignoreCurrency(String value, int currencyEndPosition) {
        return value.substring(currencyEndPosition + 1, value.length() - 1);
    }

    private static Double doubleFrom(String value) {
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Integer integerFrom(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Long longFrom(String value) {
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static List<DowJonesIndexDTO> csvToDowJonesIndex(File sample) throws IOException {
        InputStream is = new FileInputStream(sample);
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withDelimiter(',').withHeader());

        Iterable<CSVRecord> csvRecords = csvParser.getRecords();
        List<DowJonesIndexDTO> readRecords = new ArrayList<>();
        for (CSVRecord csvRecord : csvRecords) {

            Double high = doubleFrom(ignoreCurrency(csvRecord.get("high"), 0));
            Double low = doubleFrom(ignoreCurrency(csvRecord.get("low"), 0));
            Double close = doubleFrom(ignoreCurrency(csvRecord.get("close"), 0));
            Double open = doubleFrom(ignoreCurrency(csvRecord.get("open"), 0));
            Double nextWeekClose = doubleFrom(ignoreCurrency(csvRecord.get("next_weeks_close"), 0));
            Double nextWeekOpen = doubleFrom(ignoreCurrency(csvRecord.get("next_weeks_open"), 0));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/uuuu");
            LocalDate date = LocalDate.parse(csvRecord.get("date"), formatter);
            DowJonesIndexDTO dowJonesIndexDTO = DowJonesIndexDTO.builder()
                    .close(close)
                    .date(date)
                    .days_to_next_dividend(integerFrom(csvRecord.get("days_to_next_dividend")))
                    .high(high)
                    .low(low)
                    .open(open)
                    .next_weeks_close(nextWeekClose)
                    .next_weeks_open(nextWeekOpen)
                    .percent_change_next_weeks_price(doubleFrom(csvRecord.get("percent_change_next_weeks_price")))
                    .percent_change_volume_over_last_wk(doubleFrom(csvRecord.get("percent_change_volume_over_last_wk")))
                    .previous_weeks_volume(longFrom(csvRecord.get("previous_weeks_volume")))
                    .percent_change_price(Double.valueOf(csvRecord.get("percent_change_price")))
                    .quarter(integerFrom(csvRecord.get("quarter")))
                    .volume(longFrom(csvRecord.get("volume")))
                    .percent_return_next_dividend(doubleFrom(csvRecord.get("percent_return_next_dividend")))
                    .stock(csvRecord.get("stock"))
                    .build();
            readRecords.add(dowJonesIndexDTO);
        }
        return readRecords;
    }


}



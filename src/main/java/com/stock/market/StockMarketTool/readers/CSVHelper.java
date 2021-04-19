package com.stock.market.StockMarketTool.readers;

import com.stock.market.StockMarketTool.model.StocksIdxItem;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public static List<StocksIdxItem> csvToDowJonesIndex(InputStream is) throws IOException {
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withDelimiter(',').withHeader());

        Iterable<CSVRecord> csvRecords = csvParser.getRecords();
        List<StocksIdxItem> readRecords = new ArrayList<>();
        for (CSVRecord csvRecord : csvRecords) {

            Double high = doubleFrom(ignoreCurrency(csvRecord.get("high"), 0));
            Double low = doubleFrom(ignoreCurrency(csvRecord.get("low"), 0));
            Double close = doubleFrom(ignoreCurrency(csvRecord.get("close"), 0));
            Double open = doubleFrom(ignoreCurrency(csvRecord.get("open"), 0));
            Double nextWeekClose = doubleFrom(ignoreCurrency(csvRecord.get("next_weeks_close"), 0));
            Double nextWeekOpen = doubleFrom(ignoreCurrency(csvRecord.get("next_weeks_open"), 0));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/uuuu");
            LocalDate date = LocalDate.parse(csvRecord.get("date"), formatter);
            StocksIdxItem dowJonesIndex = StocksIdxItem.builder()
                    .close(close)
                    .date(date)
                    .daysToNextDividend(integerFrom(csvRecord.get("days_to_next_dividend")))
                    .high(high)
                    .low(low)
                    .open(open)
                    .nextWeeksOpen(nextWeekClose)
                    .nextWeeksClose(nextWeekOpen)
                    .percentChangePrice(doubleFrom(csvRecord.get("percent_change_next_weeks_price")))
                    .percentChangeVolumeOverLastWk(doubleFrom(csvRecord.get("percent_change_volume_over_last_wk")))
                    .previousWeeksVolume(longFrom(csvRecord.get("previous_weeks_volume")))
                    .percentReturnNextDividend(Double.valueOf(csvRecord.get("percent_change_price")))
                    .quarter(integerFrom(csvRecord.get("quarter")))
                    .volume(longFrom(csvRecord.get("volume")))
                    .percentChangeNextWeeksPrice(doubleFrom(csvRecord.get("percent_return_next_dividend")))
                    .stock(csvRecord.get("stock"))
                    .build();
            readRecords.add(dowJonesIndex);
        }
        return readRecords;
    }
}




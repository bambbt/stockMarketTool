package com.stock.market.StockMarketTool.dtos;

import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DowJonesIndexDTO {

    private Integer quarter;
    private String stock;
    private LocalDate date;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Long volume;
    private Double percent_change_price;
    private Double percent_change_volume_over_last_wk;
    private Long previous_weeks_volume;
    private Double next_weeks_open;
    private Double next_weeks_close;
    private Double percent_change_next_weeks_price;
    private Integer days_to_next_dividend;
    private Double percent_return_next_dividend;

}

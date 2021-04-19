package com.stock.market.StockMarketTool.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveStockIdxCommand {


    private Integer quarter;
    private String stock;
    private LocalDate date;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Long volume;
    private Double percentChangePrice;
    private Double percentChangeVolumeOverLastWk;
    private Long previousWeeksVolume;
    private Double nextWeeksOpen;
    private Double nextWeeksClose;
    private Double percentChangeNextWeeksPrice;
    private Integer daysToNextDividend;
    private Double percentReturnNextDividend;


    public boolean validate() {
        // add validity constraints
        return true;
    }
}

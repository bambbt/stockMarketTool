package com.stock.market.StockMarketTool.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stocksidxitem")
public class StocksIdxItem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column
    private Integer quarter;
    @Column
    private String stock;
    @Column
    private LocalDate date;
    @Column
    private Double open;
    @Column
    private Double high;
    @Column
    private Double low;
    @Column
    private Double close;
    @Column
    private Long volume;
    @Column
    private Double percentChangePrice;
    @Column
    private Double percentChangeVolumeOverLastWk;
    @Column
    private Long previousWeeksVolume;
    @Column
    private Double nextWeeksOpen;
    @Column
    private Double nextWeeksClose;
    @Column
    private Double percentChangeNextWeeksPrice;
    @Column
    private Integer daysToNextDividend;
    @Column
    private Double percentReturnNextDividend;


}

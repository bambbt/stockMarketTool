package com.stock.market.StockMarketTool.repository;

import com.stock.market.StockMarketTool.model.StocksIdxItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StocksIdxRepository extends JpaRepository<StocksIdxItem, Long> {
    @Query("from StocksIdxItem s where s.stock = :symbol ")
    List<StocksIdxItem> findByStockSymbol(@Param("symbol") String symbol);
}

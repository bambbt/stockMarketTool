package com.stock.market.StockMarketTool.repository;

import com.stock.market.StockMarketTool.model.StocksIdxItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocksIdxItemRepository extends JpaRepository<StocksIdxItem, Long> {

}

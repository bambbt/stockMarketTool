package com.stock.market.StockMarketTool.controllers;


import com.stock.market.StockMarketTool.services.StocksIdxUploadService;
import com.stock.market.StockMarketTool.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1")
public class StockIdxUploadController {

    private StocksIdxUploadService stocksIdxUploadService;


    @Autowired
    void dowJonesDataUploadService(StocksIdxUploadService service) {
        this.stocksIdxUploadService = service;
    }


    @PostMapping("/upload/csvFile")
    public ResponseEntity<APIResponse> uploadCSVFile(@RequestParam MultipartFile file) {
        this.stocksIdxUploadService.save(file);
        String message = "File \"" + file.getOriginalFilename() + "\" uploaded succesfully!";
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse(message));
    }
}

package com.shop.inditex.web.controller;

import com.shop.inditex.domain.dto.PriceDto;
import com.shop.inditex.domain.service.PriceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController()
@RequestMapping("/api")
public class PriceController {

    private static Logger LOGGER = LogManager.getLogger(PriceController.class);

    @Autowired
    private PriceService priceService;


    @GetMapping("/get-prices")
    public ResponseEntity<PriceDto> getPrices(@RequestParam(value = "applicationDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                                              @RequestParam(value = "productId", required = true) Integer productId,
                                              @RequestParam(value = "brandId", required = true) Integer brandId) {
        LOGGER.info("PriceController::getPrices {} ", date, productId, brandId);
        return new ResponseEntity<>(priceService.getCorrectPrice(date, productId, brandId), HttpStatus.OK);
    }

}

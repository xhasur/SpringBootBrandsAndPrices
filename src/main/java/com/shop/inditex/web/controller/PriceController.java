package com.shop.inditex.web.controller;

import com.shop.inditex.domain.dto.PriceDto;
import com.shop.inditex.domain.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController()
@RequestMapping("/api")
public class PriceController {

    private static Logger LOGGER = LogManager.getLogger(PriceController.class);

    @Autowired
    private PriceService priceService;


    @GetMapping("/get-prices")
    @Operation(summary = "Get the best price among the dates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the best price", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Price not found", content = @Content)})
    public ResponseEntity<PriceDto> getPrices(@Parameter(description = "Date", example = "2020-10-12T07:30:10Z")
                                              @RequestParam(value = "applicationDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                                              @Parameter(description = "productId", example = "35455")
                                              @RequestParam(value = "productId", required = true) Integer productId,
                                              @Parameter(description = "brandId", example = "1 (ZARA)")
                                              @RequestParam(value = "brandId", required = true) Integer brandId) {
        LOGGER.info("PriceController::getPrices {} ", date, productId, brandId);
        return new ResponseEntity<>(priceService.getCorrectPrice(date, productId, brandId), HttpStatus.OK);
    }

}

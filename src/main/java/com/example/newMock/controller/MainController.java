package com.example.newMock.controller;

import com.example.newMock.model.RequestDTO;
import com.example.newMock.model.ResponceDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Request;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@RestController
public class MainController {
    private Logger log = LoggerFactory.getLogger(MainController.class);

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(
            value = "/info/PostBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public  Object postBalances(@RequestBody RequestDTO requestDTO)
    {
        try{
            String clientID = requestDTO.getClientID();
            char firstDigit = clientID.charAt(0);
            BigDecimal maxLimit;
            String RqUID = requestDTO.getRqUID();
            String account = requestDTO.getAccount();
            String currency= "";

            if(firstDigit == '8'){
                maxLimit = new BigDecimal(2000.00);
                currency = "US";
            }
            else if(firstDigit == '9'){
                maxLimit = new BigDecimal(1000.00);
                currency = "EU";
            } else {
                maxLimit = new BigDecimal(10000.00);
                currency = "RU";
            }

            BigDecimal balance = getRandomBigDecimal(maxLimit);

            ResponceDTO responseDTO = new ResponceDTO();
            responseDTO.setRqUID(RqUID);
            responseDTO.setClientID(clientID);
            responseDTO.setAccount(account);
            responseDTO.setCurrency(currency);
            responseDTO.setBalance(balance);
            responseDTO.setMaxLimit(maxLimit);

            log.error("*********** Request DTO ***********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("*********** Responce DTO ***********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));

            return responseDTO;
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }


    private BigDecimal getRandomBigDecimal(BigDecimal maxLimit){
        Random random = new Random();
        double randomValue = random.nextDouble();
        BigDecimal randomBigDecimal = BigDecimal.valueOf(randomValue).multiply(maxLimit);
        return randomBigDecimal.setScale(2, RoundingMode.HALF_UP);
    }
}

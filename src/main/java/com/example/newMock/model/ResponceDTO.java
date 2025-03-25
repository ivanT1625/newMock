package com.example.newMock.model;

import lombok.*;

import java.math.BigDecimal;

@Data
public class ResponceDTO {
    private String rqUID;
    private String clientID;
    private String account;
    private String currency;
    private BigDecimal balance;
    private BigDecimal maxLimit;
}

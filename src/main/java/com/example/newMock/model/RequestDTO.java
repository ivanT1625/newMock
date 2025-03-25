package com.example.newMock.model;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestDTO {
    private String rqUID;
    private String clientID;
    private String account;
    private String openDate;
    private String closeDate;
}

package ru.hotkto.jewelryworkshop.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientOrderSearchDTO {
    private LocalDate orderDateFrom;
    private LocalDate orderDateTo;
    private String clientsName;
    private Boolean isDeadlineExpired;
    private Boolean isLoose;
}

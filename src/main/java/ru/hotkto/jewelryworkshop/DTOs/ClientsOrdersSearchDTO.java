package ru.hotkto.jewelryworkshop.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientsOrdersSearchDTO {
    private String clientName;
    private String employeeName;
    private LocalDate date;
    private Boolean isCompleted;
}

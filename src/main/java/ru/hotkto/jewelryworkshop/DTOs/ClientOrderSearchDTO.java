package ru.hotkto.jewelryworkshop.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientOrderSearchDTO {
    private LocalDate orderCreationDate;
}

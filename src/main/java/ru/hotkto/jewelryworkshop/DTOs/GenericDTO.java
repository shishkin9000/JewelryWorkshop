package ru.hotkto.jewelryworkshop.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class GenericDTO {
    private Long id;
    private String createdBy;
    private LocalDateTime createdWhen;
    private boolean isDeleted;
    private String deletedBy;
    private LocalDateTime deletedWhen;
}

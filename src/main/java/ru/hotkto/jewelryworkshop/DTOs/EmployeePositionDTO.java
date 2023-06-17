package ru.hotkto.jewelryworkshop.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePositionDTO extends GenericDTO{
    private String title;
    private String description;
    private String role;


}

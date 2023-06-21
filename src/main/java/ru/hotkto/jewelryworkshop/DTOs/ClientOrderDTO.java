package ru.hotkto.jewelryworkshop.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.hotkto.jewelryworkshop.models.Client;
import ru.hotkto.jewelryworkshop.models.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientOrderDTO extends GenericDTO{
    private String description;
    private LocalDate deadline;
    private boolean isCompleted;
    private LocalDateTime completedWhen;
    private Integer price;
    private String status;
    private ClientDTO clientDTO;
    private EmployeeDTO employeeDTO;
}

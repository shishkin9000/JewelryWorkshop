package ru.hotkto.jewelryworkshop.DTOs;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.hotkto.jewelryworkshop.models.ClientOrder;
import ru.hotkto.jewelryworkshop.models.Employee;
import ru.hotkto.jewelryworkshop.models.EmployeePosition;
import ru.hotkto.jewelryworkshop.models.FinishedItem;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO extends GenericDTO {
    private String login;
    private String password;
    private String fullName;
    private String phone;
    private String address;
    private LocalDate birthDate;
    private EmployeePosition employeePosition;
    private List<ClientOrder> clientOrders;
    private List<FinishedItem> finishedItems;
}

package ru.hotkto.jewelryworkshop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "empoyees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "employees_sequence", allocationSize = 1)
public class Employee extends GenericModel{
    private String firstName;
    private String middleName;
    private String lastName;
    private Position position;
    private LocalDate birthDate;
    private List<WorkOrder> workOrders;
}

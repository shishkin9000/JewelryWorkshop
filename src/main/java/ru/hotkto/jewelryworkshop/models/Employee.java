package ru.hotkto.jewelryworkshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "employees_id_seq", allocationSize = 1)
public class Employee extends GenericModel {

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false, foreignKey = @ForeignKey(name = "FK_EMPLOYEE_POSITION_ID"))
    private EmployeePosition employeePosition;

    @OneToMany(mappedBy = "employee")
    private List<ClientOrder> clientOrders;

    @OneToMany(mappedBy = "employee")
    private List<FinishedItem> finishedItems;


}

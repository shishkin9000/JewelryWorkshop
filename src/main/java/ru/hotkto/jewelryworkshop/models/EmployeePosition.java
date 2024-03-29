package ru.hotkto.jewelryworkshop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees_positions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "employees_positions_id_seq", allocationSize = 1)
public class EmployeePosition extends GenericModel {

    @Column(name = "title")
    public String title;

    @Column(name = "description")
    public String description;

    @Column(name = "role")
    public String role;
}

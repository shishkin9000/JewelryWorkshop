package ru.hotkto.jewelryworkshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "worker_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "worker_orders_sequence", allocationSize = 1)
public class WorkerOrder extends GenericModel{
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}

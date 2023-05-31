package ru.hotkto.jewelryworkshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "worker_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "worker_orders_sequence", allocationSize = 1)
public class WorkerOrder extends GenericModel{
    @Column(name = "order_date_time")
    private LocalDateTime orderDateTime;

    @ManyToOne
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "FK_WORKER_ORDER_EMPLOYEE"))
    private Employee employee;

    @OneToMany(mappedBy = "workerOrder")
    private List<Metal> metals;

    @OneToMany(mappedBy = "workerOrder")
    private List<Gem> gems;

    @Column(name = "deadline")
    private LocalDate deadline;
}

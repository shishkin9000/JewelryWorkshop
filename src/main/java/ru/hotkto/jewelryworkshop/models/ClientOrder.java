package ru.hotkto.jewelryworkshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "clients_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "clients_orders_sequence", allocationSize = 1)
public class ClientOrder extends GenericModel{
    @Column(name = "order_date_time")
    private LocalDateTime orderDateTime;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "client_id",
            nullable = false,
            foreignKey =@ForeignKey(name = "FK_ORDER_CLIENT"))
    private Client client;

    @Column(name = "deadline")
    private LocalDate deadline;

}

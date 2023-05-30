package ru.hotkto.jewelryworkshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "clients_orders_sequence", allocationSize = 1)
public class ClientOrder extends GenericModel{
    //продумать попадают ли металлы и камни на склад
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}

package ru.hotkto.jewelryworkshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gems")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "gems_sequence", allocationSize = 1)
public class Gem extends GenericModel{

    @OneToOne
    @JoinColumn(name = "gem_type_id")
    private GemType gemType;

    @Column(name = "color")
    private String color;

    @Column(name = "carat_weight")
    private int caratWeight;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "warehouse_finished_product_id",
            foreignKey = @ForeignKey(name = "FK_GEM_WAREHOUSE_FINISHED_ITEM"))
    private WarehouseFinishedItem warehouseFinishedItem;

    @ManyToOne
    @JoinColumn(name = "worker_order_id",
            foreignKey = @ForeignKey(name = "FK_ORDER_CLIENT"))
    private WorkerOrder workerOrder;


}

package ru.hotkto.jewelryworkshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "metals")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "metals_sequence", allocationSize = 1)
public class Metal extends GenericModel{

    @OneToOne
    @JoinColumn(name = "metal_type_id")
    private MetalType metalType;

    @Column(name = "weight")
    private int weight;

    @ManyToOne
    @JoinColumn(name = "warehouse_finished_product_id",
            foreignKey = @ForeignKey(name = "FK_METAL_WAREHOUSE_FINISHED_ITEM"))
    private WarehouseFinishedItem warehouseFinishedItem;

    @ManyToOne
    @JoinColumn(name = "worker_order_id",
            foreignKey = @ForeignKey(name = "FK_WORKER_ORDER"))
    private WorkerOrder workerOrder;

    @ManyToOne
    @JoinColumn(name = "client_order_id",
            foreignKey = @ForeignKey(name = "FK_CLIENT_ORDER"))
    private ClientOrder clientOrder;
}

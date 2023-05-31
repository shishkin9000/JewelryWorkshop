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
    @JoinColumn(name = "warehouse_finished_product_id")
    private WarehouseFinishedProduct warehouseFinishedProduct;

    @ManyToOne
    @JoinColumn(name = "worker_order_id")
    private WorkerOrder workerOrder;

}

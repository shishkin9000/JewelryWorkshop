package ru.hotkto.jewelryworkshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "warehouse_finished_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "warehouse_finished_products_sequence", allocationSize = 1)
public class WarehouseFinishedProduct extends GenericModel {

    @Column(name = "item_code")
    private Long itemCode;

    @Column(name = "net_cost")
    private int net_cost;

    @OneToMany(mappedBy = "warehouseFinishedProduct")
    private List<Metal> metal;

    @OneToMany(mappedBy = "warehouseFinishedProduct")
    private List<Gem> gems;

    @Column(name = "description")
    private String description;

    @Column(name = "photo_path")
    private String photo_path;
}

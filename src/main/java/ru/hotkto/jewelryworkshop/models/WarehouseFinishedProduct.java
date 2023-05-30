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
@Table(name = "warehouse_finished_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "warehouse_finished_products_sequence", allocationSize = 1)
public class WarehouseFinishedProduct extends GenericModel {

    @Column(name = "item_code")
    private Long itemCode;


}

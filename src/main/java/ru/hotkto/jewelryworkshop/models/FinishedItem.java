package ru.hotkto.jewelryworkshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "finished_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "finished_items_id_seq", allocationSize = 1)
public class FinishedItem extends GenericModel {

    @Column(name = "item_code")
    private int itemCode;

    @Column(name = "net_cost")
    private double net_cost;

    @Column(name = "description")
    private String description;

    @Column(name = "photo_path")
    private String photo_path;

    @Column(name = "metals_info")
    private String metalsInfo;

    @Column(name = "gems_info")
    private String gemsInfo;

    @ManyToOne
    @JoinColumn(name = "employee_id",
            foreignKey = @ForeignKey(name = "FK_FINISHED_ITEM_EMPLOYEE"))
    private Employee employee;

    @ManyToMany
    @JoinTable(
            name = "finished_items_metals_types",
            joinColumns = @JoinColumn(name = "finished_item_id"),
            inverseJoinColumns = @JoinColumn(name = "metal_type_id")
    )
    private List<MetalType> metalTypes;

    @ManyToMany
    @JoinTable(
            name = "finished_items_gems_types",
            joinColumns = @JoinColumn(name = "finished_item_id"),
            inverseJoinColumns = @JoinColumn(name = "gem_type_id")
    )
    private List<GemType> gemTypes;


}

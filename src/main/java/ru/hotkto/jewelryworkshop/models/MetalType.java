package ru.hotkto.jewelryworkshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "metals_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "metal_types_id_seq", allocationSize = 1)
public class MetalType extends GenericModel{

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(
            name = "finished_items_metals_types",
            joinColumns = @JoinColumn(name = "metal_type_id"),
            inverseJoinColumns = @JoinColumn(name = "finished_item_id")
    )
    private List<FinishedItem> finishedItems;
}

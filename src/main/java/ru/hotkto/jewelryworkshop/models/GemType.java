package ru.hotkto.jewelryworkshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "gems_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "gems_types_id_seq", allocationSize = 1)
public class GemType extends GenericModel{

    @Column(name = "title")
    private String title;

    @Column(name = "carat_weight")
    private Double caratWeight;

    @ManyToMany
    @JoinTable(
            name = "finished_items_gems_types",
            joinColumns = @JoinColumn(name = "gem_type_id"),
            inverseJoinColumns = @JoinColumn(name = "finished_item_id")
    )
    private List<FinishedItem> finishedItems;
}

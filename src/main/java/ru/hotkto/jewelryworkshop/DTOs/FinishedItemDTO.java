package ru.hotkto.jewelryworkshop.DTOs;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.hotkto.jewelryworkshop.models.Employee;
import ru.hotkto.jewelryworkshop.models.GemType;
import ru.hotkto.jewelryworkshop.models.MetalType;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinishedItemDTO extends GenericDTO{
    private int itemCode;
    private double netCost;
    private String description;
    private String photoPath;
    private String metalsInfo;
    private String gemsInfo;
    private Employee employee;
    private List<MetalType> metalTypes;
    private List<GemType> gemTypes;
}

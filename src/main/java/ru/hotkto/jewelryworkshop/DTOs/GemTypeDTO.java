package ru.hotkto.jewelryworkshop.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.hotkto.jewelryworkshop.models.FinishedItem;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GemTypeDTO extends GenericDTO{

    private String title;
    private Double caratWeight;
}

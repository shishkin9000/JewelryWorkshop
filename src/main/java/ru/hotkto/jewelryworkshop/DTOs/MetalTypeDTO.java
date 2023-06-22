package ru.hotkto.jewelryworkshop.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetalTypeDTO extends GenericDTO{
    private String title;
}

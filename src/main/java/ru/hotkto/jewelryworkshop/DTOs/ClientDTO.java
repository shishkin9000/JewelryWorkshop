package ru.hotkto.jewelryworkshop.DTOs;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.hotkto.jewelryworkshop.models.ClientOrder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO extends GenericDTO {

    private String fullName;
    private String phone;
    private List<ClientOrder> clientOrders;

}

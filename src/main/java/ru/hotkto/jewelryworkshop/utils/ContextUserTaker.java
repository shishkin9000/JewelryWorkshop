package ru.hotkto.jewelryworkshop.utils;

import javassist.NotFoundException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.hotkto.jewelryworkshop.DTOs.EmployeeDTO;
import ru.hotkto.jewelryworkshop.services.EmployeeService;
import ru.hotkto.jewelryworkshop.services.customUserDetails.CustomUserDetails;

import java.util.Objects;

public class ContextUserTaker {
    private ContextUserTaker() {}

    public static EmployeeDTO getContextUser(EmployeeService service, SecurityContext context) throws NotFoundException {
            CustomUserDetails customUserDetails = (CustomUserDetails) context.getAuthentication().getPrincipal();
            Long employeeId = Long.valueOf(customUserDetails.getUserId());
            return service.getOne(employeeId);
    }
}

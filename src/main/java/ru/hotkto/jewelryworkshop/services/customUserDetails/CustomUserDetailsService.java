package ru.hotkto.jewelryworkshop.services.customUserDetails;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.hotkto.jewelryworkshop.models.Employee;
import ru.hotkto.jewelryworkshop.repositories.EmployeesRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.hotkto.jewelryworkshop.UserRolesConstants.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeesRepository employeesRepository;

    @Value("${spring.security.admin_name}")
    private String adminName;
    @Value("${spring.security.admin_password}")
    private String adminPassword;

    public CustomUserDetailsService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(adminName)) {
            return new CustomUserDetails(
                    null,
                    username,
                    adminPassword,
                    List.of(new SimpleGrantedAuthority("ROLE_" + ADMIN)));
        } else {
            Employee employee = employeesRepository.findByLogin(username);
            List<GrantedAuthority> authorities = new ArrayList<>();

            switch (employee.getEmployeePosition().getRole()) {
                case "USER" -> authorities.add(new SimpleGrantedAuthority("ROLE_" + USER));
                case "MANAGER" -> authorities.add(new SimpleGrantedAuthority("ROLE_" + MANAGER));
                case "DIRECTOR" -> authorities.add(new SimpleGrantedAuthority("ROLE_" + DIRECTOR));
            }

            return new CustomUserDetails(employee.getId().intValue(),
                    username,
                    employee.getPassword(),
                    authorities);
        }

    }


}

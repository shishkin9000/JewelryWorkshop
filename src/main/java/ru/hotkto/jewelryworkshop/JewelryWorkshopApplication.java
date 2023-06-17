package ru.hotkto.jewelryworkshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.hotkto.jewelryworkshop.mappers.EmployeesPositionsMapper;
import ru.hotkto.jewelryworkshop.models.Client;
import ru.hotkto.jewelryworkshop.models.Employee;
import ru.hotkto.jewelryworkshop.models.EmployeePosition;
import ru.hotkto.jewelryworkshop.repositories.ClientsRepository;
import ru.hotkto.jewelryworkshop.repositories.EmployeesPositionsRepository;
import ru.hotkto.jewelryworkshop.repositories.EmployeesRepository;
import ru.hotkto.jewelryworkshop.services.EmployeesPositionsService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class JewelryWorkshopApplication implements CommandLineRunner {

    @Autowired
    EmployeesRepository employeesRepository;
    @Autowired
    EmployeesPositionsService positionsService;
    @Autowired
    EmployeesPositionsMapper employeesPositionsMapper;

    public static void main(String[] args) {
        SpringApplication.run(JewelryWorkshopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Start test");
//        EmployeePosition employeePosition = employeesPositionsMapper.toEntity(positionsService.getOne(1L));
//        Employee employee = new Employee(
//                "loh",
//                "loh",
//                "loh",
//                "loh",
//                "loh",
//                LocalDate.of(1900,1,1),
//                employeePosition,
//                null,
//                null
//        );
//        employeesRepository.save(employee);
//        System.out.println("success");
    }
}

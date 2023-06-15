package ru.hotkto.jewelryworkshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.hotkto.jewelryworkshop.models.Client;
import ru.hotkto.jewelryworkshop.repositories.ClientsRepository;

import java.util.List;

@SpringBootApplication
public class JewelryWorkshopApplication implements CommandLineRunner {

    @Autowired
    ClientsRepository clientsRepository;

    public static void main(String[] args) {
        SpringApplication.run(JewelryWorkshopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        List<Client> clientList = clientsRepository.findAll();
//        clientList.forEach(System.out::println);
    }
}

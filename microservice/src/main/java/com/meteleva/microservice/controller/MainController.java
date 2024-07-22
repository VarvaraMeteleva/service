package com.meteleva.microservice.controller;

import com.meteleva.microservice.entit.Customer;
import com.meteleva.microservice.repository.CustomerRep;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "main_metods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {
    @Autowired
    private final CustomerRep customerRep;

    @Operation(
            summary = "добавление новой записи в БД",
            description = "Получает DTO объекта и билдером собирает и сохраняет сущность в бзау"
    )

    @PostMapping("/api/add")
    public void addCustomer(@RequestBody Customer customerDTO) {
        log.info(
                "New row: " + customerRep.save(
                        Customer.builder()
                                .name(customerDTO.getName())
                                .surname(customerDTO.getSurname())
                                .gender(customerDTO.getGender())
                                .age(customerDTO.getAge())
                                .phone_number(customerDTO.getPhone_number())
                                .build())
        );
    }

    @SneakyThrows
    @GetMapping("/api/all")
    public List<Customer> getAll() {
        return customerRep.findAll();
    }

    @GetMapping("/api")
    public Customer getCustomer(@RequestParam int id) {
        return customerRep.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deletCustomer(@RequestParam int id) {
        customerRep.deleteById(id);
    }

    @PutMapping("/api/change")
    public String changeCustomer(@RequestBody Customer cat) {
        if (!customerRep.existsById(cat.getId())) {
            return "NO such row!";
        } else {
            return customerRep.save(cat).toString();
        }
    }


}

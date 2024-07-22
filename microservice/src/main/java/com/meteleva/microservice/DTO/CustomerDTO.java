package com.meteleva.microservice.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDTO {
    String name;
    String surname;
    String gender;
    int age;
    String phone_number;
}

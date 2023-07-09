package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private long id;

    private String firstName;

    private String email;

    private String lastName;
}

package com.ethan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Integer id;
    private String departName;
    private List<Employee> employees;

    public Department(Integer id){
        this.id = id;
    }
}

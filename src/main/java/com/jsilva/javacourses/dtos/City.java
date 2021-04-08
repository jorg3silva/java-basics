package com.jsilva.javacourses.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class City {
    private String name;
    private List<User> users = new ArrayList<>();

    public City(String name, User... u) {
        this.name = name;
        users.addAll(Arrays.asList(u));
    }
}

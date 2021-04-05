package com.jsilva.javacourses.controlllers;

import com.jsilva.javacourses.dtos.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RestController
@RequestMapping("/streams")
public class JavaStreamsMemory {

    @GetMapping("/ex1")
    public ResponseEntity<String> ex1() {

        List<Users> usersList =  new ArrayList<>();
        Users u1 = Users.builder().name("John Silva").age(29).build();
        Users u2 = Users.builder().name("Barh Herrera").age(27).build();
        Users u3 = Users.builder().name("Anna Perez").age(7).build();
        Users u4 = Users.builder().name("Eilin Perez").age(7).build();
        usersList.add(u1);
        usersList.add(u2);
        usersList.add(u3);
        usersList.add(u4);

        double result = usersList.stream().map(Users::getAge)
                .filter(age -> age >= 20).count();

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}

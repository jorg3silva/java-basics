package com.jsilva.javacourses.controlllers;

import com.jsilva.javacourses.dtos.City;
import com.jsilva.javacourses.dtos.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Slf4j
@Component
@RestController
@RequestMapping("/streams")
public class JavaStreamsMemory {

    @GetMapping("/ex1")
    public ResponseEntity<String> ex1() {

        List<User> usersList =  new ArrayList<>();
        User u1 = User.builder().name("John Silva").age(29).build();
        User u2 = User.builder().name("Barh Herrera").age(27).build();
        User u3 = User.builder().name("Anna Perez").age(7).build();
        User u4 = User.builder().name("Eilin Perez").age(7).build();
        usersList.add(u1);
        usersList.add(u2);
        usersList.add(u3);
        usersList.add(u4);

        Long result = usersList.stream().map(User::getAge)
                .filter(age -> age >= 20).count();

        log.info("stream, map, reduce ex: 1 >>> {}", result.toString());

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/ex2")
    public ResponseEntity<String> ex2() {

        List<User> usersList =  new ArrayList<>();
        User u1 = User.builder().name("John Silva").age(29).build();
        User u2 = User.builder().name("Barh Herrera").age(27).build();
        User u22 = User.builder().name("Bruth Herrera").age(27).build();
        User u3 = User.builder().name("Anna Perez").age(7).build();
        User u4 = User.builder().name("Eilin Perez").age(7).build();

        City newYork = new City("New York", u1);
        City paris = new City("Paris", u2, u22, u3);
        City santiago = new City("Paris", u4);

        List<City> cities = new ArrayList<>();
        cities.add(newYork);
        cities.add(paris);
        cities.add(santiago);

        Long count = cities.stream().flatMap( c -> c.getUsers().stream()).count();
        log.info("Count: {}", count.toString() );

        cities.stream()
                .flatMap( c -> c.getUsers().stream())
                .filter(p -> p.getName().toLowerCase(Locale.ROOT).startsWith("b"))
                .forEach(p -> log.info(p.getName()));

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/ex3")
    public ResponseEntity<String> ex3() {

        // PATRONES
        List<User> usersList =  new ArrayList<>();
        User u1 = User.builder().name("John Silva").age(29).build();
        User u2 = User.builder().name("Barh Herrera").age(27).build();
        User u22 = User.builder().name("Bruth Herrera").age(27).build();
        User u3 = User.builder().name("Anna Perez").age(7).build();
        User u4 = User.builder().name("Eilin Perez").age(7).build();

        // STREAM DE UNB ARRAY
        User[] users = {u1,u2, u22,u3,u4};
        log.info("{}", Stream.of(users).count());
        Arrays.stream(users).forEach(a -> log.info(a.getName()));

        log.info("--------====================\n\n");

        // STREAM DE UN ARCHIVOS
        Path path = Paths.get("./src/main/resources/users-file.txt");
        try(Stream<String> lines = Files.lines(path)) {
            lines.filter(p -> p.toLowerCase(Locale.ROOT).startsWith("a")).forEach(log::info);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("--------====================\n\n");
        // STREAM DE UN regex
        String str = "Esta es una oración para el stream";
        Pattern p = Pattern.compile(" ");
        log.info("Contador de palabras stream: {}", p.splitAsStream(str).count());

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}

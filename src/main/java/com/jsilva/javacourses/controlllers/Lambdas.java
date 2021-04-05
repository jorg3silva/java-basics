package com.jsilva.javacourses.controlllers;
import com.jsilva.javacourses.lambdas.Consumer;
import com.jsilva.javacourses.lambdas.Function;
import com.jsilva.javacourses.lambdas.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


@Slf4j
@Component
@RestController
@RequestMapping("/lambdas")
public class Lambdas {

    /**
     *
     *
     *      Java functional interfaces toolbox:
     *
     *      Supplier<T> T get()
     *      Consumer<T> void accept(T t)
     *      Predicate<T> boolean test(T t)
     *      Function<T,R> R apply(T t)
     *
     * @return ResponseEntity
     */
    @GetMapping("/ex1")
    public ResponseEntity<String> ex1() {
        // example 1
        log.info("-----=============== Init ex1 ....");
        Supplier<String> supplier = () -> "Hello World!";
        log.info(supplier.get());



        // example 2
        log.info("-----=============== Init ex2 ....");
        Consumer<String> consumer = (String a) -> {
            log.info(a+"-Concat");
        };
        consumer.consume("PrefijoEjemplo");



        // example 3
        log.info("-----=============== Init ex3 ....");
        Function<List<String>, String> function = (List<String> a) -> {
            return a != null && !a.isEmpty() ? a.get(0) : null;
        };
        log.info(function.apply(Arrays.asList("foo", "bar")));
        log.info(function.apply(null));


        // example 4
        log.info("-----=============== Init ex4 ....");
        Runnable runnable = () -> log.info("Runnable ...");
        runnable.run();

        // example 5
        log.info("-----=============== Init ex5 ....");
        java.util.function.Consumer<String> c1 = s -> log.info("consumer 1 {}", s);
        java.util.function.Consumer<String> c2 = s -> log.info("consumer 2 {}", s);

        java.util.function.Consumer<String> c3 = c1.andThen(c2);
        c3.accept("consumerStr");

        // example 6
        log.info("-----=============== Init ex6 ....");
        Predicate<String> p = String::isEmpty;
        Predicate<String> p2 = Objects::isNull;
        Predicate<String> p3 = p.negate().and(p2);
        log.info(p3.test("")+"");


        // example 7
        log.info("-----=============== Init ex7 comparing....");
        List<String> strList = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                "ten");
        java.util.function.ToIntFunction<String> f = String::length;
        Comparator<String> comp = Comparator.comparingInt(f);


        java.util.function.Consumer<List<?>> printerList = e -> {
            e.forEach(iter->log.info(iter.toString()));
        };
        strList.sort(comp);
        printerList.accept(strList);


        // example 8
        log.info("-----=============== Init ex8 comparing....");
        List<String> strList2 = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                "ten");
        Comparator<String> compAbece = (o1, o2) -> o1.compareTo(o2);
        strList2.sort(compAbece);
        printerList.accept(strList2);

        // example 8
        log.info("-----=============== Init ex9 comparing....");
        List<Integer> strList3 = Arrays.asList(1,1000,222,33,689,1239,1239,90);
        Comparator<Integer> compInt = (o1, o2) -> o1 > o2 ? -1 : (o1 == o2 ? 0 : 1);
        strList3.sort(compInt);
        printerList.accept(strList3);


        return new ResponseEntity<>("OK",HttpStatus.OK);
    }
}

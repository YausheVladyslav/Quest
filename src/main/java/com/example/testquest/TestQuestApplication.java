package com.example.testquest;

import com.example.testquest.service.EquationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@RequiredArgsConstructor
@SpringBootApplication
public class TestQuestApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(TestQuestApplication.class, args);
    }

    final EquationsService equationsService;

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        Scanner scanner = new Scanner(System.in);
        equationsService.calculate(scanner.nextLine());
    }
}

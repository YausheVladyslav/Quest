package com.example.testquest.service;

import com.example.testquest.entity.EquationsEntity;
import com.example.testquest.repository.EquationsRepository;
import lombok.RequiredArgsConstructor;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class EquationsService {

    private final EquationsRepository equationsRepository;

    public void calculate(String equation) {
        Expression expression = new ExpressionBuilder(equation).build();
        EquationsEntity equationsEntity = new EquationsEntity();
        checkOperations(equation);
        double result = expression.evaluate();
        equationsEntity.setEquation(equation);
        equationsEntity.setResult(result);
        equationsRepository.save(equationsEntity);
    }


    public void allEquations() {
        List<EquationsEntity> listEntity = equationsRepository.findAll();
        for (EquationsEntity values : listEntity) {
            System.out.println(values.getId() + " "
                    + values.getEquation() + " "
                    + values.getResult());
        }
    }

    public void findByResultsAndMore(long result) {
        List<EquationsEntity> listEntity = equationsRepository.findAll();
        for (EquationsEntity values : listEntity) {
            if (values.getResult() >= result) {
                System.out.println(values.getEquation());
            }
        }
    }

    public void findByResultsAndLess(long result) {
        List<EquationsEntity> listEntity = equationsRepository.findAll();
        for (EquationsEntity values : listEntity) {
            if (values.getResult() <= result) {
                System.out.println(values.getEquation());
            }
        }
    }

    public void findByResults(long result) {
        List<EquationsEntity> listEntity = equationsRepository.findAll();
        for (EquationsEntity values : listEntity) {
            if (values.getResult() == result) {
                System.out.println(values.getEquation());
            }
        }
    }

    public void editEquation(long id, String request) {
        Optional<EquationsEntity> equationsEntity = equationsRepository.findById(id);
        if (equationsEntity.isPresent()) {
            checkOperations(request);
            Expression expression = new ExpressionBuilder(request).build();
            double result = expression.evaluate();
            equationsEntity.get().setEquation(request);
            equationsEntity.get().setResult(result);
            equationsRepository.save(equationsEntity.get());
        }
    }

    public void findNumbers(String textWithNumbers) {
        Pattern regex = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = regex.matcher(textWithNumbers);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        System.out.println(count);
    }

    private void checkOperations(String request) {
        for (int i = 0; i < request.length(); i++) {
            if (request.charAt(i) == '-' && request.charAt(i + 1) == '+' ||
                    request.charAt(i) == '+' && request.charAt(i + 1) == '+' ||
                    request.charAt(i) == '-' && request.charAt(i + 1) == '-' && request.charAt(i + 2) == '-') {
                throw new IllegalArgumentException();
            }
        }
    }
}

package com.example.expense_tracker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.expense_tracker.model.Expense_Model;
import com.example.expense_tracker.repository.ExpenseRepository;

@RequestMapping("/api")
@RestController
public class ExpenseController {

    @Autowired
    ExpenseRepository expenseRepository;

    @PostMapping("/expenses")
    public ResponseEntity<Expense_Model> createExpense(@RequestBody Expense_Model expenseModel){
        try {
            @SuppressWarnings("unused")
            Expense_Model exp=expenseRepository.save(new Expense_Model(expenseModel.getAmount(),expenseModel.getType(),null, expenseModel.getDescription()));
            return new ResponseEntity<>(HttpStatus.CREATED);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/expenses/{id}")
    public ResponseEntity<Expense_Model> updateExpenses(@PathVariable("id") long id, @RequestBody Expense_Model expenseModel) {
    Optional<Expense_Model> expData = expenseRepository.findById(id);

    if (expData.isPresent()) {
      Expense_Model exp = expData.get();
      exp.setAmount(expenseModel.getAmount());
      exp.setDescription(expenseModel.getDescription());
      exp.setType(expenseModel.getType());
      return new ResponseEntity<>(expenseRepository.save(exp), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/expenses/{id}")
    public ResponseEntity<HttpStatus> deleteExpenses(@PathVariable("id") long id) {
      try {
        expenseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @GetMapping("/expenses")
    public ResponseEntity<List<Expense_Model>> getAllExpenses( ) {
    try {
      List<Expense_Model> expenses = expenseRepository.findAll();
      if (expenses.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(expenses, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

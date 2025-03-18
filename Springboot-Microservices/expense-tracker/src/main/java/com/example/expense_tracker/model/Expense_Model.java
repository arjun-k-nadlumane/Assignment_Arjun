package com.example.expense_tracker.model;

import java.util.Date;

import jakarta.persistence.*;

@Table(name="My_Expenses")
@Entity
public class Expense_Model {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @Column(name="amount")
    private int amount;

    @Column(name="type")
    private String type;

    // @Column(name="exp_date")
    // private Date exp_date;

    @Column(name="description")
    private String description;

    public Expense_Model(){

    }

    public Expense_Model(int amount,String type,Date exp_date,String description){
        this.amount=amount;
        this.type=type;
        // this.exp_date=exp_date;
        this.description=description;
    }

    public long getId(){
        return id;
    }

    public String getType(){
        return type;
    }
    public int getAmount(){
        return amount;
    }
    // public Date getDate(){
    //     return exp_date;
    // }
    public String getDescription(){
        return description;
    }
    public void setType(String type){
        this.type=type;
    }
    public void setAmount(int amount){
        this.amount=amount;
    }
    public void setDescription(String description){
        this.description=description;
    }
}

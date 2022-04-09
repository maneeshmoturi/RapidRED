package com.example.budgetapp.models;

public class Budget {

    private String Id, Title, Amount, Date;

    public Budget() {}

    public Budget(String id, String title, String amount, String date) {
        this.Id = id;
        this.Title = title;
        this.Amount = amount;
        this.Date = date;
    }


    public String getId() { return Id; }

    public void setId(String id) { Id = id; }

    public String getTitle() { return Title; }

    public void setTitle(String title) { Title = title; }

    public String getAmount() { return Amount; }

    public void setAmount(String amount) { Amount = amount; }

    public String getDate() { return Date; }

    public void setDate(String date) { Date = date; }

}

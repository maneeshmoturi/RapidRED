package com.example.budgetapp.models;

public class Budget {
    private String Date, Description, Price;
    private Boolean isShowMenu;

    public Budget() { }

    public Budget(String Date, String Description, String Price) {
        this.Date = Date;
        this.Description = Description;
        this.Price = Price;
        this.isShowMenu = Boolean.TRUE;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public Boolean isShowMenu() {
        return isShowMenu;
    }
}

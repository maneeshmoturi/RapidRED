package com.example.budgetapp.models;

public class Budget {

    private String Id, Date, Title, Amount;
    private boolean showMenu = false;

    public Budget() {}

    public Budget(String date, String title, String amount) {
        this.Date = date;
        this.Title = title;
        this.Amount = amount;
    }

    public Budget(String id, String date, String title, String amount) {
        this.Id = id;
        this.Date = date;
        this.Title = title;
        this.Amount = amount;
    }


    public String getId() { return Id; }

    public void setId(String id) { Id = id; }

    public String getTitle() { return Title; }

    public void setTitle(String title) { Title = title; }

    public String getAmount() { return Amount; }

    public void setAmount(String amount) { Amount = amount; }

    public String getDate() { return Date; }

    public void setDate(String date) { Date = date; }

    public boolean isShowMenu() {
        return showMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }
}

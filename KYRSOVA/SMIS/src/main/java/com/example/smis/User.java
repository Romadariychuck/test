package com.example.smis;

public class User {
    //Оголошення днних для подальших маніпуляцій записами з БД
    private Integer ID;
    private String Name;
    private String Last_Name;
    private String Telephone;
    private String Register_Number;
    private String Auto;
    private Integer Price;
    private String Status;
    private String Login;
    private String Password;

    public User() {

    }
    //Присвоєння значень для користувача
    public User(String user_name, String user_last_name, String user_auto, String user_telephone, Integer user_price, String user_status) {
        Name = user_name;
    Last_Name = user_last_name;
    Auto = user_auto;
    Telephone = user_telephone;
    Price = user_price;
    Status = user_status;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    //Присвоєння значень для Витягування інформації користувачів у панель Адміністратора
    public User(Integer id, String name, String last_name, String auto, String telephone, String register_Number, Integer price, String status) {
        ID = id;
        Name = name;
        Last_Name = last_name;
        Telephone = telephone;
        Register_Number = register_Number;
        Auto = auto;
        Price = price;
        Status = status;
    }


    public Integer getID()      {
        return ID;
    }
    public Integer setID()      {
        return ID;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }
    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getRegister_Number()
    {
        return Register_Number;
    }

    public void setRegister_Number(String register_Number) {
        Register_Number = register_Number;
    }

    public String getAuto() {
        return Auto;
    }

    public void setAuto(String auto) {
        Auto = auto;
    }
}

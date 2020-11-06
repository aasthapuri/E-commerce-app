package com.example.pikapainting.Model;

public class Users {
    private  String name,phone,password;
    public Users(){

    }

    public Users(String password, String phone,String name) {
        this.password = password;
        this.name=name;
        this.phone=phone;
    }

    public String getphone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }
    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }
    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }
}

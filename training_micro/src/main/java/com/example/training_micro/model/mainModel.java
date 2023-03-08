package com.example.training_micro.model;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class mainModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private int age;

    public mainModel(){

    }

    public mainModel(String name, String address, int age){
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "mainModel [id=" + id + ", name=" + name + ", address=" + address + ", age=" + age + "]";
    }
}

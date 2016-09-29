package com.rtm.finder.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "secondName")
    private String secondName;

    @ManyToOne
    private City city;

    @OneToMany
    private Set<Car> cars;

    public User(){}

    public User(String firstName, String secondName, City city, Set<Car> cars) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.city = city;
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Car> getCar() {
        return cars;
    }

    public void setCar(Set<Car> cars) {
        this.cars = cars;
    }
}

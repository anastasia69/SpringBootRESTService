package web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import web.enums.City;
import web.enums.Position;
import web.enums.Sex;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEES")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @JsonIgnore
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "SEX")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "CITY")
    @Enumerated(EnumType.STRING)
    private City city;

    @Column(name = "SALARY")
    private Integer salary;

    @Column(name = "POSITION")
    @Enumerated(EnumType.STRING)
    private Position position;

    public Employee() {}

    public Employee(String name, String surname, Sex sex,
                    City city, Integer salary, Position position) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.city = city;
        this.salary = salary;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}

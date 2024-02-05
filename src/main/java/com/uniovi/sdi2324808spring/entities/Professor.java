package com.uniovi.sdi2324808spring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Professor {
    @Id
    @GeneratedValue
    private Long id;

    private String DNI;
    private String name;
    private String surname;
    private String category;

    public Professor(Long id, String DNI, String name, String surname, String category) {
        this.id = id;
        this.DNI = DNI;
        this.name = name;
        this.surname = surname;
        this.category = category;
    }

    public Professor() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", DNI='" + DNI + '\'' +
                ", nombre='" + name + '\'' +
                ", apellidos='" + surname + '\'' +
                ", categoria='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equals(id, professor.id) && Objects.equals(DNI, professor.DNI) && Objects.equals(name, professor.name) && Objects.equals(surname, professor.surname) && Objects.equals(category, professor.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, DNI, name, surname, category);
    }
}

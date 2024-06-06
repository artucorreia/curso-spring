package com.example.firstproject.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Table
@Entity(name = "occupation")
public class Occupation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position", nullable = false, length = 100, unique = true)
    private String position;

    @Column(name = "workload", nullable = false, length = 4)
    private String workload;

    @OneToMany(mappedBy = "occupation")
    private List<Person> persons;

    public Occupation() {
    }

    public Occupation(Long id, String position, String workload) {
        this.id = id;
        this.position = position;
        this.workload = workload;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Occupation that = (Occupation) o;
        return Objects.equals(id, that.id)
                && Objects.equals(position, that.position)
                && Objects.equals(workload, that.workload)
                && Objects.equals(persons, that.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position, workload, persons);
    }

    @Override
    public String toString() {
        return "Occupation{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", workload='" + workload + '\'' +
                ", persons=" + persons +
                '}';
    }
}

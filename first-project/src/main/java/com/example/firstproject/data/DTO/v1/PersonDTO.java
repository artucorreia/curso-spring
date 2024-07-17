package com.example.firstproject.data.DTO.v1;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

public class PersonDTO extends RepresentationModel<PersonDTO> {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String address;
    private String gender;
    private Boolean enabled;

    public PersonDTO() {}

    public PersonDTO(
            Long id,
            String firstName,
            String lastName,
            LocalDate birthdate,
            String address,
            String gender,
            Boolean enabled
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.address = address;
        this.gender = gender;
        this.enabled = enabled;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
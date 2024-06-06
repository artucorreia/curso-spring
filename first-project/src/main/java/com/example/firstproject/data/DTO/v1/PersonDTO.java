package com.example.firstproject.data.DTO.v1;

import java.util.Objects;

public class PersonDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private OccupationDTO occupation;

    public PersonDTO() {}

    public PersonDTO(
            Long id,
            String firstName,
            String lastName,
            String address,
            String gender,
            OccupationDTO occupation
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.occupation = occupation;
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

    public OccupationDTO getOccupation() {
        return occupation;
    }

    public void setOccupation(OccupationDTO occupation) {
        this.occupation = occupation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(id, personDTO.id)
                && Objects.equals(firstName, personDTO.firstName)
                && Objects.equals(lastName, personDTO.lastName)
                && Objects.equals(address, personDTO.address)
                && Objects.equals(gender, personDTO.gender)
                && Objects.equals(occupation, personDTO.occupation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, gender, occupation);
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", occupation=" + occupation +
                '}';
    }
}

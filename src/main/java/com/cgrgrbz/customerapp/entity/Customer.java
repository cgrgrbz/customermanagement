package com.cgrgrbz.customerapp.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "customer")
@Schema(name = "Customer", description = "Customer entity")
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Unique id field of Customer object")
    private int id;
    @Column(name = "first_name")
    @Schema(description = "FirstName field of Customer object")
    @NotBlank(message = "this field must be filled")
    private String firstName;
    @Column(name = "last_name")
    @Schema(description = "LastName field of Customer object")
    @NotBlank(message = "this field must be filled")
    private String lastName;
    @Column(name = "age")
    @Schema(description = "Age field of Customer object")
    @Min(value = 0, message = "must be greater than or equal to 0")
    @Max(value = 100, message = "must be less than or equal to 100")
    private int age;
    @Column(name = "gender")
    @Schema(description = "Gender field of Customer object")
    @Pattern(regexp = "(?:male|Male|MALE|female|Female|FEMALE)")
    private String gender;
    @Column(name = "email")
    @Schema(description = "Email field of Customer object")
    //@Pattern(regexp = "^[a-zA-Z0-9._-]+\\@[a-zA-Z0-9_-]+\\.[A-Za-z]+$")
    @Email(message = "this field must be filled with proper email, e.g: abc@example.com")
    private String email;
    @Column(name = "phone_number")
    @Schema(description = "PhoneNumber field of Customer object")
    @Pattern(regexp = "^[5]\\d{2}[- ]?\\d{3}[- ]?\\d{4}$", message = "must be start with 5 and requires 10 digits")
    private String phoneNumber;

    public Customer() {
    }

    public Customer(String firstName, String lastName, int age, String gender, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

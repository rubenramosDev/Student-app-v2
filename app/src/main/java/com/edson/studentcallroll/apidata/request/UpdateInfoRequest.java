package com.edson.studentcallroll.apidata.request;

public class UpdateInfoRequest {

    private String email;
    private String identifierNumber;
    private String lastName;
    private String name;
//    private String password;

    public UpdateInfoRequest() {
    }

    public UpdateInfoRequest(String email, String identifierNumber, String lastName, String name) {
        this.email = email;
        this.identifierNumber = identifierNumber;
        this.lastName = lastName;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentifierNumber() {
        return identifierNumber;
    }

    public void setIdentifierNumber(String identifierNumber) {
        this.identifierNumber = identifierNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

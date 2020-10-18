package com.example.demo.to;

public class EditorialTO {
    private Integer id;

    private String name;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public EditorialTO() {
    }

    public EditorialTO(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}

package com.example.demo.to;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorTO {
    private Integer id;

    private String name;
    private String lastName;
    private String familyName;
    private Date dob;    
    private String picture;
}

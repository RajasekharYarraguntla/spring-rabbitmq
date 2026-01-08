package com.raja.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserUpdated {

    private Long id;
    private String name;
    private String email;
}

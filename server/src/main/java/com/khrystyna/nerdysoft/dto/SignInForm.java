package com.khrystyna.nerdysoft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInForm {
    @NotNull
    private String email;

    @NotNull
    private String password;
}

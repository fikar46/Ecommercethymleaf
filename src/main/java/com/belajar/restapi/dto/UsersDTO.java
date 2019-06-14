package com.belajar.restapi.dto;


import com.belajar.restapi.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.Cookie;
import javax.validation.constraints.NotNull;

/**
 * The type Account detail dto.
 *
 * @author mzulfikarmaylendra  on 10/08/18 11.31
 */
@Setter /*Untuk Generate Setter*/
@Getter /*Untuk Generate Getter*/
@NoArgsConstructor
public class UsersDTO {
    @NotNull(message = "id user tidak boleh null")
    private Integer id;
    @NotNull
    private String username;
    @NotNull
    private Long wallet;
    @NotNull
    private String password;
    /**
     * Instantiates a new Account detail dto.
     *
     * @param users             the hardware
     */
    public UsersDTO(Users users) {
        this.id = users.getId();
        this.username = users.getUsername();
        this.wallet = users.getWallet();
        this.password = users.getPassword();

    }
}

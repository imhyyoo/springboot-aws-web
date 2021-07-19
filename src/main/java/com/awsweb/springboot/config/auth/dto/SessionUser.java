package com.awsweb.springboot.config.auth.dto;

import com.awsweb.springboot.domain.user.User;
import lombok.Getter;

import javax.jws.soap.SOAPBinding;
import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}

package com.hqyj.module.user.entity;

import com.hqyj.module.role.entity.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@Table(name = "user")
public class User implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long userId;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String salt;
    private String url;
    private Integer status;

    private List<Role> roles;

}

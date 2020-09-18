package com.hqyj.module.role.entity;


import com.hqyj.module.perm.entity.Perm;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@Table(name = "Role")
public class Role implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer roleId;
    private String roleName;
    private String description;

    private List<Perm> perms;
}

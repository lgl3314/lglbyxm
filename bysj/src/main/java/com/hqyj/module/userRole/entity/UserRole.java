package com.hqyj.module.userRole.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author lykgogo
 * @Date 2020/9/10 14:40
 */

@Data
@Table(name = "user_role")
public class UserRole implements Serializable {

    @Id
    private Long userId;
    @Id
    private Integer roleId;
}

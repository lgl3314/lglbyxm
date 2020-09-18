package com.hqyj.module.perm.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@Table(name = "Perm")
public class Perm implements Serializable {


    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer permId;
    private String permName;
    private String permCode;
    private String url;

}

package com.byxm.springbootByxm.modules.account.mapper;

import com.byxm.springbootByxm.modules.account.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PaymentMapper {

    @Select("SELECT * FROM payment WHERE s_id=#{sId}")
    List<Payment> getPaymentBySId(int sId);

}

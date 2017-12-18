package com.achain.blockchain.game.domain.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yujianjian
 * @since 2017-12-12 下午3:36
 */
@EqualsAndHashCode(callSuper = true)
@TableName("blockchain_dog_order")
@Data
public class BlockchainDogOrder extends OrderBase implements Serializable{

    private static final long serialVersionUID = -5658403517282904661L;


    private Integer dogId;
    private String orderId;
    private String seller;
    private String buyer;
    private Long startingPrice;
    private Long endingPrice;
    private Long transPrice;
    private Date beginTime;
    private Date endTime;




}

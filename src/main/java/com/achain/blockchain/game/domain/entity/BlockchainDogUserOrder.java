package com.achain.blockchain.game.domain.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yujianjian
 * @since 2017-12-12 下午7:40
 */
@EqualsAndHashCode(callSuper = true)
@TableName("blockchain_dog_user_order")
@Data
public class BlockchainDogUserOrder extends OrderBase implements Serializable {

    private static final long serialVersionUID = -9047287224950493382L;


    private String rechargeTrxId;
    private Integer rechargeStatus;
    private String message;
    private String method;



}

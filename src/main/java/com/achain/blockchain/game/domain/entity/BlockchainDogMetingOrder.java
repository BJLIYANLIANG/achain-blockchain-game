package com.achain.blockchain.game.domain.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yujianjian
 * @since 2017-12-12 下午3:36
 */
@EqualsAndHashCode(callSuper = true)
@TableName("blockchain_dog_meting_order")
@Data
public class BlockchainDogMetingOrder extends BlockchainDogOrder implements Serializable{

    private static final long serialVersionUID = 6755003004551812379L;


    private Integer sellerDogId;
    private Integer buyerDogId;







}

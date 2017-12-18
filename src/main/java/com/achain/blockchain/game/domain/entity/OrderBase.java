package com.achain.blockchain.game.domain.entity;

import java.util.Date;

import lombok.Data;

/**
 * @author yujianjian
 * @since 2017-12-18 下午2:11
 */
@Data
class OrderBase {

    private Integer id;
    /**
     * 交易id
     */
    private String trxId;
    /**
     * 订单状态,0-进行中,1-交易成功,2-交易取消,3-交易失效
     */
    private Integer status;
    private Date createTime;
    private Date updateTime;
}

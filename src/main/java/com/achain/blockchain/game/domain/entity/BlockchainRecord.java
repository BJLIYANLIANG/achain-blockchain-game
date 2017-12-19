package com.achain.blockchain.game.domain.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yujianjian
 * @since 2017-12-11 下午1:56
 */
@EqualsAndHashCode(callSuper = true)
@TableName("blockchain_record")
@Data
public class BlockchainRecord extends OrderBase implements Serializable {

    private static final long serialVersionUID = 1432748946229084964L;


    /**
     * 交易所在块号
     */
    private Long blockNum;


    private String contractId;

    /**
     * 交易时间
     */
    private Date trxTime;

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

package com.achain.blockchain.game.domain.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author yujianjian  2017-12-29 11:39
 */
@Data
public class Wallet2DTO implements Serializable{

    private static final long serialVersionUID = -8050539202820124272L;

    private String trxId;

    private String contract_Id;

    private String event_params;

    private String event_type;

    private Long block_num;

    private String trx_time;
    /**
     * 调用方法名
     */
    private String called_abi;

    private String from_addr;

    private String amount;
    /**
     * 调用参数
     */
    private String abi_params;

    private String coin_type;
}

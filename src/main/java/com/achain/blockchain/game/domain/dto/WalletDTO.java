package com.achain.blockchain.game.domain.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author yujianjian  2017-12-29 11:37
 */
@Data
public class WalletDTO implements Serializable{

    private static final long serialVersionUID = -8050539202820124272L;
    String msg;
    Integer code;
    List<Wallet2DTO> result;

}

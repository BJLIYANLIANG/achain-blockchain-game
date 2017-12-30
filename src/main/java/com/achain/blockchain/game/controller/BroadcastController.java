package com.achain.blockchain.game.controller;

import com.achain.blockchain.game.conf.Config;
import com.achain.blockchain.game.domain.dto.OfflineSignDTO;
import com.achain.blockchain.game.domain.dto.TransactionDTO;
import com.achain.blockchain.game.domain.dto.Wallet2DTO;
import com.achain.blockchain.game.domain.dto.WalletDTO;
import com.achain.blockchain.game.domain.enums.ContractGameMethod;
import com.achain.blockchain.game.job.TransactionJob;
import com.achain.blockchain.game.service.IBlockchainService;
import com.achain.blockchain.game.utils.HttpUtils;
import com.achain.blockchain.game.utils.SDKHttpClient;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SimpleTimeZone;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.spring.web.json.Json;

/**
 * @author yujianjian
 * @since 2017-12-11 上午11:08
 */
@RestController
@RequestMapping("/api/act")
@Slf4j
public class BroadcastController {


    @Autowired
    private IBlockchainService blockchainService;
    @Autowired
    private TransactionJob transactionJob;
    @Autowired
    private SDKHttpClient httpClient;
    @Autowired
    private Config config;


    /**
     * 交易广播接口
     */
    @RequestMapping(value = "/network_broadcast_transaction", method = RequestMethod.POST)
    public String networkBroadcastTransaction(String message) {
        log.info("ActRPCTransactionController|network_broadcast_transaction|收到消息|[message={}]", message);
        String result = null;
        try {
            result = blockchainService.networkBroadcast(message);
        } catch (Exception e) {
            log.info("ActRPCTransactionController|network_broadcast_transaction|执行异常", e);
        }
        log.info("ActRPCTransactionController|network_broadcast_transaction|返回结果|[result={}]", result);
        return result;
    }

    /**
     * 离线签名接口
     * @param offlineSignDTO 签名数据
     * @return 签名后的data
     */
    @PostMapping("offline/sign")
    public Map<String,String> offLineSign(@RequestBody OfflineSignDTO offlineSignDTO){
        log.info("offLineSign|offlineSignDTO={}",offlineSignDTO);
        return blockchainService.offLineSign(offlineSignDTO);
    }

    /**
     * 合约充值离线签名接口
     * @param offlineSignDTO 签名数据
     * @return 签名后的data
     */
    @PostMapping("offLineRechargeSign")
    public Map<String,String> offLineRechargeSign(@RequestBody OfflineSignDTO offlineSignDTO){
        log.info("offLineRechargeSign|offlineSignDTO={}",offlineSignDTO);
        return blockchainService.offLineRechargeSign(offlineSignDTO);
    }


    /**
     * 查询账户act余额,获得的余额需要除以10的五次方
     * @param actAddress act地址
     * @return 余额
     */
    @GetMapping("balance")
    public Long getBalance(String actAddress){
        log.info("getBalance|actAddress={}",actAddress);
        if(StringUtils.isEmpty(actAddress)){
            return 0L;
        }
        return blockchainService.getBalance(actAddress);
    }

    /**
     * 通用查询接口
     * @param list 查询参数,无参数传空列表
     * @return 查询结果
     */
    @PostMapping("query")
    public String query(@RequestBody List<String> list){
        log.info("query|list={}",list);
        return blockchainService.commonQuery(list);
    }

    /**
     * 更新没有改变状态的trx
     */
    @GetMapping("update/transaction")
    public void updateTransaction(String trxId){
        log.info("updateTransaction|orgTrxId={}",trxId);
        String url = config.broadcastUrl + "?trx_id=" + trxId + "&page=1&per_page=10";
        try {
            String result = HttpUtils.get(url);
            log.info("updateTransaction|result={}",result);
            WalletDTO walletDTO = JSONObject.parseObject(result, WalletDTO.class);
            log.info("updateTransaction|walletDTO={}",walletDTO);
            if(Objects.isNull(walletDTO) || walletDTO.getCode() != 200){
                return;
            }
            Wallet2DTO dto = walletDTO.getResult().get(0);
            if(Objects.isNull(dto)){
                return;
            }
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setApiParams(dto.getAbi_params());
            try{
                transactionDTO.setAmount((long)(new Double(dto.getAmount()) * 10000));
            }catch (Exception e){

            }
            transactionDTO.setBlockNum(dto.getBlock_num());
            transactionDTO.setCallAbi(dto.getCalled_abi());
            transactionDTO.setContractId(dto.getContract_Id());
            transactionDTO.setEventParam(dto.getEvent_params());
            transactionDTO.setEventType(dto.getEvent_type());
            transactionDTO.setTrxId(trxId);
            SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date trxTime = smf.parse(dto.getTrx_time());
            transactionDTO.setTrxTime(trxTime);
            transactionDTO.setFromAddr(dto.getFrom_addr());

            if(StringUtils.isEmpty(transactionDTO.getEventType())){
                transactionDTO.setCallAbi(ContractGameMethod.RECHARGE.getValue());
            }
            transactionJob.update(transactionDTO);
            log.info("updateTransaction|success|transactionDTO={}",transactionDTO);
        }catch (Exception e){
            log.error("updateTransaction|orgTrxId={}",trxId,e);
        }

    }

}

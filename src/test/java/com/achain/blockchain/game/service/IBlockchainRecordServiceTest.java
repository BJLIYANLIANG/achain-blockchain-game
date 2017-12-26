package com.achain.blockchain.game.service;

import com.achain.blockchain.game.conf.Config;
import com.achain.blockchain.game.domain.entity.BlockchainRecord;
import com.achain.blockchain.game.utils.SDKHttpClient;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IBlockchainRecordServiceTest {

    @Autowired
    private IBlockchainRecordService blockchainService;
    @Autowired
    private Config config;
    @Autowired
    private SDKHttpClient httpClient;

    @Test
    public void insert() throws Exception {
        BlockchainRecord blockchainRecord = new BlockchainRecord();
        blockchainRecord.setBlockNum(1L);
        blockchainRecord.setContractId("12321");
        blockchainRecord.setTrxId("fdafds");
        blockchainRecord.setTrxTime(new Date());
        boolean insert = blockchainService.insert(blockchainRecord);
        Assert.assertTrue(insert);
    }

    @Test
    public void listAll() throws Exception {
        EntityWrapper<BlockchainRecord> wrapper = new EntityWrapper<>();
        List<BlockchainRecord> list = blockchainService.selectList(wrapper);
        long blockCount = config.headerBlockCount;
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void selectMaxBlockNum() throws Exception {
        EntityWrapper<BlockchainRecord> wrapper = new EntityWrapper<>();
        wrapper.orderBy("block_num", false);
        BlockchainRecord blockchainRecord = blockchainService.selectOne(wrapper);
        String walletUrl = config.walletUrl;
        System.out.println(23123);
    }


    @Test
    public void testRpc() throws Exception{
        String url = "http://172.16.33.16:18888/rpc";
        String user = "admin:123456";
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("CONBGzSXPfG3ddgzvv3vsf5jqTWqhgRTRrxW");
        jsonArray.add("wallet01");
        jsonArray.add("query_balance");
        jsonArray.add("ACTBk37yzeFcJLqWG4s5Bp9WJqMMLhYvYHop");
        String data = httpClient.post(url, user, "call_contract_local_emit", jsonArray);
        System.out.println(data);
    }
}
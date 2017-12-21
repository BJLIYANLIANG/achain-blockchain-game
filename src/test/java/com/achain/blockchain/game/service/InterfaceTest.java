package com.achain.blockchain.game.service;

import com.achain.blockchain.game.domain.dto.OfflineSignDTO;
import com.achain.blockchain.game.utils.HttpUtils;
import com.ms.data.ACTPrivateKey;
import com.ms.data.Transaction;

/**
 * @author yujianjian
 * @since 2017-12-13 下午7:45
 */
public class InterfaceTest {

    private String basicUrl = "http://127.0.0.1:8340/api/act/";

    public static void main(String[] args) throws Exception {
        InterfaceTest test = new InterfaceTest();
        //String result = test.getBalance();
        //String result = test.networkBroadcastTransaction();
        String result = test.testOffLineSign();
        System.out.println(result);
    }


    public String networkBroadcastTransaction() throws Exception {
        String url = basicUrl + "network_broadcast_transaction";
        String result = HttpUtils.broadcastPost(url, "afdsaf");
        return result;
    }


    public String recharge() throws Exception{
        Transaction trx = new Transaction(
            new ACTPrivateKey("5JfdeAZaormibWETNUk3uxYpKZ6yxuY6vGYzP4aTZ9T4LU1yuiF"),
            "CON5svUGc6WGzy1oAGbZy4m5fPteax21DdCe",
            10000L,
            (long) (Double.parseDouble("5000") * 100000));
        String a = trx.toJSONString();
        System.out.println(a);
        return a;
    }

    private String testOffLineSign() throws Exception{
        String param ="ZcDmzUKyEMeJ1WJtlMEIxiDJbaO5OrKg9rWgeB0VOhnrKfcU/SFAoMqy1nabwT/OfIR0U/NFv6hmP" +
                      "/QONPzhlwOHhIqkBHIatuE7GkgOtEkB2W3DmMkDOPZk96TDVVUTz46AM1mrvXkeqaF5IZlu2qzrCCL264BWIGsWdHjWEUbk\n" +
                      "Va5hpzzEDC6cEf5oyktWQojbRXv4PmcXA6Z+ejMLWVdqEBHxEZQTIUW/idAAMSU=|true|2.3|1.2|2000|1513775070004";

        Transaction trx = new Transaction(new ACTPrivateKey("5JiB57vDbcXC6tzs9rxowwwysTf75z7Th9NPGTzh5GF2QYQwte4"), "CON5svUGc6WGzy1oAGbZy4m5fPteax21DdCe", "generate_zero_dog", param, 5000L, true);
        return trx.toJSONString();
    }


    public String offLineSign() throws Exception {
        String url = basicUrl + "offline/sign";
        OfflineSignDTO offlineSignDTO = new OfflineSignDTO();
        offlineSignDTO.setParam("1");
        offlineSignDTO.setMethod("query_dog");
        offlineSignDTO.setContractId("CON5svUGc6WGzy1oAGbZy4m5fPteax21DdCe");
        offlineSignDTO.setPrivateKey("5JfdeAZaormibWETNUk3uxYpKZ6yxuY6vGYzP4aTZ9T4LU1yuiF");
//        String result = HttpUtils.postJson(url, JSON.toJSONString(offlineSignDTO));
//        return result;
        Transaction trx = new Transaction(new ACTPrivateKey("5JfdeAZaormibWETNUk3uxYpKZ6yxuY6vGYzP4aTZ9T4LU1yuiF"), "CON5svUGc6WGzy1oAGbZy4m5fPteax21DdCe", "generate_zero_dog", "1", 5000L, true);
        return trx.toJSONString();
    }


    public String getBalance() throws Exception {
        String url = basicUrl + "balance?actAddress=fasdfas";
        String result = HttpUtils.get(url);
        return result;
    }
}

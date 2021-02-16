package com.system.eth;

import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import java.util.List;
import java.util.Set;


@Component
public class MyEth {
    public String getHash() throws Exception {
        Web3j web3 = Web3j.build(new HttpService("http://127.0.0.1:8545"));
         List<String> web3Accounts = web3.ethAccounts().send().getAccounts();
        return web3Accounts.get(0);
    }

}

package com.system.eth;

import com.system.domain.EthData;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;
import java.io.File;
import java.io.FileOutputStream;


@Component
public class MyEth {
    //web3j连接到以太坊(注：这里是连接的ganache-cli私链)
    private Web3j web3 = Web3j.build(new HttpService("http://127.0.0.1:8545"));
    //通过web3j对象获取到以太坊的账户
//    private List<String> web3Accounts = web3.ethAccounts().send().getAccounts();
//    public MyEth() throws Exception {
//
//    }

    /**
     * 通过上传数据时的交易hash，从以太坊获取到上传的数据
     * @param hash
     * @return
     * @throws Exception
     */
    public String getTransactionByHash(String hash,String path) throws Exception {
        Request<?, EthTransaction> result = web3.ethGetTransactionByHash(hash);
        Transaction transaction = result.send().getTransaction().get();
        System.out.println(transaction);
        String hex = transaction.getInput().replace("0x","");
        String data = hexToString(hex);
        JSONObject jsonData = JSONObject.fromObject(data);
        EthData ethFile = (EthData) JSONObject.toBean(jsonData,EthData.class);
        String filename = "tmp"+hexToString(ethFile.getType());
        hexToFile(ethFile.getFile(),filename,path);
        return filename;
    }

    /**
     * 将16进制字符串转为string
     * @param str
     * @return
     */
    public String hexToString(String str){
        byte[] baKeyword = new byte[str.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(str.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            str = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return str;
    }

    /**
     * 十六进制转成文件
     *
     * @param hex
     * @param filePath
     */
    public static void hexToFile(String hex, String filePath,String root) {
        StringBuilder sb = new StringBuilder();
        sb.append(hex);
        saveToFile(sb.toString().toUpperCase(), root + filePath);
    }
    /**
     * hex 转为文件
     *
     * @param src
     * @param output
     */
    public static void saveToFile(String src, String output) {
        if (src == null || src.length() == 0) {
            return;
        }
        try {
            FileOutputStream out = new FileOutputStream(new File(output));
            byte[] bytes = src.getBytes();
            for (int i = 0; i < bytes.length; i += 2) {
                out.write(charToInt(bytes[i]) * 16 + charToInt(bytes[i + 1]));
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int charToInt(byte ch) {
        int val = 0;
        if (ch >= 0x30 && ch <= 0x39) {
            val = ch - 0x30;
        } else if (ch >= 0x41 && ch <= 0x46) {
            val = ch - 0x41 + 10;
        }
        return val;
    }

    /**
     * 判断文件是否存在
     * @param filename
     * @param path
     * @return
     */
    public  boolean checkFile(String filename,String path){
        System.out.println(path+filename);
        //得到要下载的文件
        File file = new File(path+filename);
        if (!file.exists()) {
            return false;
        }
        return true;
    }

}

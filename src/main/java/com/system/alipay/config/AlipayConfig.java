package com.system.alipay.config;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
@Component
public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000117665340";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCfyTPXeUQC+cqI4Ny/20lAdH06FNicqABlEvSWsxaSFaCpotrJ8h0KPpdIQGVMIvbd2L10yOmx9FGzLNBpTNsmP4PZNj+qSAxwtDJf4gxhWgMQq2G41gzNJN3ik1KhoK+V/kvftkHP8E+c4ZHuQKyrcFqRQmjxQuMgyagsqpWlw7Z7fQEIdXTPY+E7evek1apE2uNlQEKaC43tkE+9kRXm0uBNNykhV7hgnlxjO6UGdvE9HaDKHO+mdwaylUOkSWuB9M2dGi46REynZcfhetYITH3Bu1RTIMiesWgiUOIxNX/QwX860vAl8BFmCCDxelO/ma7Yk0h8IdGgnbuLVfPvAgMBAAECggEAHqY+fndxV0zIz8aqMVcDzwxJNQnonuwO31RqfDmv+6eQYNmvNAtwDJYC+MX5aEhQqC858BvVz9+4iDDxbTFj3AtU5CZmwef+EuRNwVyf2VClw93L6eONIxCOSzbNI9mptJDJxwizyQoki0gtcNERDfWIOdLORHD7Fj/4NVEnUVtR+yFEWPOLiabW6mijn6oRpkCjynkj6XqfgyR6bLoXf4DPm6cSuwcII4PR0VgGI3wamcZXB6q2tHRvgHz69Lx6V7OmmlHPGVrd3vfEB77O5KGKlLwb/51AdUixsYRFRwFwnza4RDKsRujnp6+tcEjJOOtnNOXEspjdVQIjdR35iQKBgQDl/Bo5FpZ2+cly4qXblszL9ZCEQ7ICtIv8/DX1pKvC48leOcsvLROWdOWMQI3GsWQ7Nu/aRD2F//1J03wadwgIWdrCG/dQJmLZy+N7MuT3GE5qxBDP2VzVby/AtWPk+nlBINND7YZSFNdcW5ou+lv6e7dd3csbsSZI7fE2dm8fGwKBgQCx3EhrfH5FeNs9OSJfBuSdDk1S1Cb5ZyWoaEKtUnSKwd1v+59T0eVftyRe4tcYr6YQylmAbcQ9Nj6unjUsUZOYtg/GbeIujd0bQOPYP6oJ9VbmKdJ/gyss3edK95ZSnb5uctnbh7nNIyK3Bj992UKGmiestv1ViNPoC9wu6jrHvQJ/F1uDTkj8/xhfXUunjKXEJvLJuqUlbyDnFsqGsGwJBcIcsXUtLZ1mX+F/br8fpw5dH6Z0tbY4iWN2VgkkkYuFRVPa49vzQjCj2Xrbn6eLmYzeafl/u5xX0VV5hBjoDFlj9zleO4fkHHIBbV/gSOUkckweU2ozNpzHRqDj3qQopQKBgFeeOfrheF8G+5H8fGctwR9xtTvRkWWZn56HxhkWHp1ghZKt9T5Yj1OIqB0qSGY72WY6GL9A08SH3XcuLumnQjufrMPGy/69Gihb3iJgsoZ3WxjFj5ILbxCKPG/c6ivG09IbOCGCilB5N5fMNyjrB2/BrzvRZZlmb2XHue9OqihNAoGBAMCPGFrsOlqVys6GB6YKhQsjUrXmoxXOJ3CZSZD6F67tk1WaBVmdfsGkuZ1CyV8ZpEhkfWXm0W4LrZQ543WFekS8Rv9L/Y3QfjHyUb4jMhVRqhoACGXNOouU7i83Hb1Bx9bSHAavygD9nbs+mK73+Qf/09XymbWSkVHO8nKTciem";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhT8iQc3raKrnHBouLBr8DJ9XaQ/6j8pMiHBtOodwGJVAVq9iW+V11ciC0kxRPWeC9YpT6V1vVjH6LhWO2Es7fy2btjpA5ESheGvRgK5q737+NCKoLeRvue3I+srdFKgkqvzY1GN05VQQ6c5tZqoR9ByT8eQIRZEKHzY5Vat3dtYo9TMW+gznE++KXK0ctMbLkh46bcyLldI36KVYdP28i+g5S5fjZXaHfruaYE/OlfPQj8ZQOUHjDyXi3PbHqr6FdDjKLWYanPWb4GKrh2QQnTYJ6be4d+ZmT2UgxXP8S7gH5eCmHTOJQZjKaQyAtoaUDcHw7yKaEaj6SOiltBGHSQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/alipay/alipayNotifyNotice";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/alipay/alipayReturnNotice";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "/log";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


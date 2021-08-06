package com.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.system.alipay.config.AlipayConfig;
import com.system.domain.RechargeRecord;
import com.system.domain.User;
import com.system.service.UserService;
import com.system.utils.ToBufferedImage;
import jnr.ffi.annotations.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/alipay")
public class AlipayController {

    @Autowired
    private UserService userService;

    private Logger log = LoggerFactory.getLogger(AlipayController.class);

    /**
     * 第三方连接支付
     * @param tradeNo
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/rechargePay", produces = "text/html; charset=UTF-8", method = RequestMethod.POST)
    public void rechargePay(@RequestParam("tradeNo") String tradeNo, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = tradeNo.split("-")[0];
        String balance = tradeNo.split("-")[1];
        String header = request.getHeader("method");
        System.out.println(header);
        System.out.println("开始处理支付");
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = username+"-"+System.currentTimeMillis();
        //付款金额，必填
        String total_amount = balance;
        //订单名称，必填
        String subject = "充值金额："+balance+"元";
        //商品描述，可空
        String body = username;
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+timeout_express+"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        String result = alipayClient.pageExecute(alipayRequest).getBody();
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(result);
        out.flush();
        out.close();
    }

    /**
     * 获取二维码支付
     * @param tradeNo
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/rechargePay", produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
    public void rechargeQRPay(@RequestParam("tradeNo") String tradeNo,
                              HttpServletResponse response) throws Exception {
        String out_trade_no = tradeNo;
        String username = tradeNo.split("-")[0];
        String balance = tradeNo.split("-")[1];
        System.out.println("开始QR处理支付");
        System.out.println("out_trade_no="+out_trade_no);
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
//        String out_trade_no = username+"-"+System.currentTimeMillis();
        //付款金额，必填
        String total_amount = balance;
        //订单名称，必填
        String subject = "充值金额："+balance+"元";
        //商品描述，可空
        String body = username;
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+timeout_express+"\","
                + "\"product_code\":\"FACE_TO_FACE_PAYMENT\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        final AlipayTradePrecreateResponse payResponse = alipayClient.execute(alipayRequest);
        if(payResponse.isSuccess()){
            System.out.println("调用成功");
//            System.out.println(payResponse.getBody());
            //获取二维码内容字符串
            final String qrCode = payResponse.getQrCode();
//            System.out.println("payResponse.getQrCode() = "+payResponse.getQrCode());
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bt = writer.encode(qrCode, BarcodeFormat.QR_CODE ,300,300);
            final ServletOutputStream outputStream = response.getOutputStream();
            //生成二维码，将二维码写到输出流，返回页面
            BufferedImage image = ToBufferedImage.toBufferedImage(bt);
            //转换成png格式的IO流
            ImageIO.write(image, "png", response.getOutputStream());
        } else {
            System.out.println("调用失败");
        }
    }

    /**
     * @Description: 支付宝同步通知页面
     */
    @RequestMapping(value = "/alipayReturnNotice")
    public ModelAndView alipayReturnNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("支付成功, 进入同步通知接口...");

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = false;
        try{
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        }catch (Exception e) {
            System.out.println("SDK验证签名出现异常");
        }

        ModelAndView mv = new ModelAndView("success");
        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            String username = out_trade_no.split("-")[0];
            Integer balance = Integer.parseInt(out_trade_no.split("-")[1]);
            Long rechargeID = Long.parseLong(out_trade_no.split("-")[2]);
            System.out.println(username);
            Integer SqlBalance = userService.getBalanceByUsername(username);
            Integer balance1 = balance + SqlBalance;
            userService.rechargeMoney(username,balance1);
            User user = userService.findOneByName(username);
            RechargeRecord rechargeRecord = new RechargeRecord(rechargeID,username,new Date(),balance);
            userService.addRechargeRecord(rechargeRecord);
            mv.addObject("user",user);
            mv.addObject("rechargeFlag","success");
        }else {
            log.info("支付, 验签失败...");

            mv.addObject("rechargeFlag","false");
        }

        return mv;
    }

    /**
     * @Description: 支付宝异步 通知页面
     */
    @RequestMapping(value = "/alipayNotifyNotice")
    public void alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

        log.info("支付成功, 进入异步通知接口...");

        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        System.out.println(params);
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            String username = new String(request.getParameter("body").getBytes("ISO-8859-1"),"UTF-8");
            //交易状态
//            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            System.out.println("充值的账户："+username);
//
//            Integer SqlBalance = userService.getBalanceByUsername(username);
//            Integer balance1 = Integer.valueOf(total_amount) + SqlBalance;
//            userService.rechargeMoney(username,balance1);
//            User user = userService.findOneByName(username);
//            RechargeRecord rechargeRecord = new RechargeRecord(username,new Date(),Integer.valueOf(total_amount));
//            userService.addRechargeRecord(rechargeRecord);
//            ModelAndView rechargeMV = new ModelAndView();
//            rechargeMV.addObject("user",user);
//            rechargeMV.addObject("rechargeFlag","success");
//            rechargeMV.setViewName("success");
            log.info("111支付成功...");
//            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
//            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知

                // 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水

//            }

        }else {//验证失败
            log.info("支付, 验签失败...");
//            ModelAndView rechargeMV = new ModelAndView();
//            rechargeMV.addObject("rechargeFlag","false");
//            rechargeMV.setViewName("success");
//            return rechargeMV;
        }
    }


    @RequestMapping(value = "/checkPay", method = RequestMethod.POST)
    @ResponseBody
    public String checkPay(@RequestParam("tradeNo") String tradeNo, HttpServletRequest request) throws Exception {
        System.out.println("tradeNo = "+tradeNo);
        //解析出username和balance
        String username = tradeNo.split("-")[0];
        System.out.println(username);
        Integer balance = Integer.parseInt(tradeNo.split("-")[1]);
        Long rechargeID = Long.parseLong(tradeNo.split("-")[2]);

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ tradeNo +"\"}");
        //请求
        String result = alipayClient.execute(alipayRequest).getBody();

        JSONObject object = (JSONObject) JSON.parse(result);
        System.out.println(object.get("alipay_trade_query_response"));
        JSONObject msg = (JSONObject) JSON.parse(object.get("alipay_trade_query_response").toString());

        if (msg.get("msg").equals("Success")){
            Integer SqlBalance = userService.getBalanceByUsername(username);
            Integer balance1 = balance + SqlBalance;
            userService.rechargeMoney(username,balance1);
            User user = userService.findOneByName(username);
            RechargeRecord rechargeRecord = new RechargeRecord(rechargeID,username,new Date(),balance);
            userService.addRechargeRecord(rechargeRecord);
            return "success";
        }else {
            return "failure";
        }
    }
}

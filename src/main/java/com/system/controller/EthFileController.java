package com.system.controller;

import com.system.eth.MyEth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Controller
@RequestMapping("/eth")
public class EthFileController {
    //获取eth对象
    @Autowired
    private MyEth myEth;

    /**
     * 查看区块链中是否有文件
     * @param hash
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/searchForFile")
    @ResponseBody
    public String searchForFile(String hash, HttpServletRequest request) throws Exception{
        System.out.println(hash);
        String path = request.getSession().getServletContext().getRealPath("/tmp/");
        // 创建File对象，一会向该路径下上传文件
        File file = new File(path);
        // 判断路径是否存在，如果不存在，创建该路径
        if(!file.exists()) {
            file.mkdirs();
        }
        String filename = myEth.getTransactionByHash(hash,path);
        return filename;
    }

    /**
     * 下载文件
     * @param filename
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/downloadFile")
    public @ResponseBody
    ResponseEntity<byte[]> downloadFile(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getSession().getServletContext().getRealPath("/tmp/");
        boolean flag = myEth.checkFile(filename,path);
        if (!flag){
            response.setContentType("text/html; charset=UTF-8");//注意text/html，和application/html
            response.getWriter().print("<html><body><script type='text/javascript'>alert('您要下载的资源已被删除！');</script></body></html>");
            response.getWriter().close();
            System.out.println("您要下载的资源已被删除！！");
        }
        File file = new File(path+filename);
        byte[] body = null;
        InputStream is = new FileInputStream(file);
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;
    }

}

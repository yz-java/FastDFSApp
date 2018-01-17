package com.yz.servlet;

import com.yz.constant.ErrorInfo;
import com.yz.system.Application;
import com.yz.util.FastDFSAPI;
import com.yz.util.ResponseMessage;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yangzhao on 17/8/13.
 */
@WebServlet("/file/upload_by_url")
public class FastDFSUploadByUrlServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileUrl = request.getParameter("fileUrl");
        String fileType = request.getParameter("fileType");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(fileUrl);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity entity = httpResponse.getEntity();
        InputStream inputStream = entity.getContent();
        byte[] bytes = IOUtils.toByteArray(inputStream);
        String[] uploadFile = FastDFSAPI.uploadFile(bytes, fileType, null);
        inputStream.close();
        String responseData = uploadFile[0] + "/" +uploadFile[1];
        if (StringUtils.isEmpty(responseData)){
            writeMessage(response, ResponseMessage.error(ErrorInfo.FILE_UPLOAD_FAIL,"文件上传失败"));
            return;
        }
        String url = "http://"+ Application.systemConfig.getIp()+":"+Application.systemConfig.getPort()+"/"+responseData;
        writeMessage(response,ResponseMessage.success(url));
    }
}

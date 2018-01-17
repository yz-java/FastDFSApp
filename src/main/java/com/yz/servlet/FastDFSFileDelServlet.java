package com.yz.servlet;

import com.yz.constant.ErrorInfo;
import com.yz.util.FastDFSAPI;
import com.yz.util.ResponseMessage;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yangzhao on 17/8/11.
 */
@WebServlet("/file/delete")
public class FastDFSFileDelServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileId = request.getParameter("fileId");
        if (StringUtils.isEmpty(fileId)){
            writeMessage(response, ResponseMessage.error(ErrorInfo.REQUEST_PARAMS_EXCEPTION,"请求参数异常"));
        }
        int i = FastDFSAPI.deleteFile(fileId);
        if (i>=0)
            writeMessage(response,ResponseMessage.success());
        else
            writeMessage(response,ResponseMessage.error(ErrorInfo.FILE_DELETE_FAIL,"文件删除失败"));
    }
}

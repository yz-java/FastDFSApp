package com.yz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseServlet extends HttpServlet {

	public final Logger logger = LogManager.getLogger(BaseServlet.class);

	public void writeMessage(HttpServletResponse response,Object data) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type","text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		String jsonString = JSON.toJSONString(data);
		logger.info("返回数据："+jsonString);
		writer.write(jsonString);
		writer.flush();
		writer.close();
	}
	

}

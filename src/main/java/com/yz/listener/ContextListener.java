package com.yz.listener;

import com.alibaba.fastjson.JSON;
import com.yz.config.SystemConfig;
import com.yz.system.Application;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by yangzhao on 17/8/11.
 */
@WebListener
public class ContextListener implements ServletContextListener {

    private Logger logger = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("servlet容器启动");
        String path = this.getClass().getResource("/config.json").getPath();
        logger.info("config.json文件path路径："+path);
        try {
            String fileToString = FileUtils.readFileToString(new File(path), "utf-8");
            SystemConfig systemConfig = JSON.parseObject(fileToString, SystemConfig.class);
            Application.systemConfig = systemConfig;
        } catch (IOException e) {
            logger.error("读取config.json失败!");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("servlet容器关闭");
    }
}

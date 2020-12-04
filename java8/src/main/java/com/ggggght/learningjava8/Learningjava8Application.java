package com.ggggght.learningjava8;

import com.ggggght.learningjava8.generate.IdTestInnodb;
import com.ggggght.learningjava8.generate.IdTestInnodbDao;
import org.apache.ibatis.plugin.Intercepts;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;


@SpringBootApplication
@ComponentScan("com.ggggght.learningjava8.generate")
@MapperScan(basePackages="com.ggggght.learningjava8")
public class Learningjava8Application implements ApplicationRunner {
    private IdTestInnodbDao dao;
    @Autowired
    ApplicationContext context;
    public static void main(String[] args) throws IOException, DocumentException {
        SpringApplication.run(Learningjava8Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(dao.selectByPrimaryKey(1));
        // String[] beans = context.getBeanDefinitionNames();
        // for (String bean : beans) {
        //         System.out.println(bean);
        // }
    }

    @Autowired
    public void setDao(IdTestInnodbDao dao) {
        this.dao = dao;
    }
}

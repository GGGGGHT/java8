module learningjava {
  requires java.base;
  requires junit;
  requires hutool.all;
  requires spring.core;
  requires java.management;
  requires lombok;
  requires com.google.common;
  requires org.checkerframework.checker.qual;
  requires mybatis;
  requires fastjson;
  requires spring.context;
  requires spring.web;
  requires spring.beans;
  requires java.compiler;
  requires org.slf4j;
  requires java.desktop;
  requires beta.jboss.websocket.api_1_1;
  // requires org.objectweb.asm;
  requires spring.data.redis;
  requires spring.boot.autoconfigure;
  requires mybatis.spring;
  requires spring.boot;
  requires dom4j;
  requires spring.tx;
  requires commons.math3;
  requires annotations;
  requires spring.webmvc;
  requires wildfly.common;
  requires jmh.core;

  exports com.ggggght.learningjava8.ThreadPool;
}


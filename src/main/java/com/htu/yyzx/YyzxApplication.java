package com.htu.yyzx;


import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.htu.yyzx.mapper")
public class YyzxApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(YyzxApplication.class, args);
        System.out.println("启动成功：sa-token配置如下：" + SaManager.getConfig());
    }

}

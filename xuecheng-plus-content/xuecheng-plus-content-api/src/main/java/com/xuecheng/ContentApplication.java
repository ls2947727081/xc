package com.xuecheng;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description TOOD
 * @date 2023/10/24 20:30:03
 */
@EnableSwagger2Doc
@SpringBootApplication
public class ContentApplication {
  public static void main(String[] args) {

    SpringApplication.run(ContentApplication.class, args);

  }
}

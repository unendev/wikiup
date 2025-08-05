package com.example.ragservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class RagServiceApplication {

	public static void main(String[] args) {
		// 设置控制台编码为UTF-8，解决中文日志显示问题
		System.setProperty("file.encoding", "UTF-8");
		System.setProperty("sun.jnu.encoding", "UTF-8");
		
		SpringApplication.run(RagServiceApplication.class, args);
	}

}

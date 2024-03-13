package dev.cisnux.webservlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class WebServletApp {
    public static void main(String[] args) {
        SpringApplication.run(WebServletApp.class);
    }
}

package com.epkorea.backoffice;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class BackOfficeApplicationRunner implements org.springframework.boot.ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("test");
    }
}

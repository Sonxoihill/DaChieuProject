package com.example.managementproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class ManagementProjectApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "true");
        SpringApplication.run(ManagementProjectApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void autoOpenBrowser() {
        String url = "http://localhost:8080/dashboard/toi-danh";

        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

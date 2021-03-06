package pl.com.michalpolak.hyperbudget;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class TracingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TracingServiceApplication.class, args);
    }
}
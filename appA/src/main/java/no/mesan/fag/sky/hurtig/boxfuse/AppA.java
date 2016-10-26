package no.mesan.fag.sky.hurtig.boxfuse;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class AppA {

    public static void main(String[] args) throws SQLException {
        Flyway flyway = new Flyway();
        flyway.setDataSource(
                System.getProperty("BOXFUSE_DATABASE_URL"),
                System.getProperty("BOXFUSE_DATABASE_USER"),
                System.getProperty("BOXFUSE_DATABASE_PASSWORD"));
        flyway.migrate();

        SpringApplication.run(AppA.class, args);
    }
}

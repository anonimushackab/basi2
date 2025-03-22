package it.unimol.basi2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Basi2Application {

	public static void main(String[] args) {
		SpringApplication.run(Basi2Application.class, args);
	}

}

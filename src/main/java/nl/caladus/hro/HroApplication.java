package nl.caladus.hro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class HroApplication {

	public static void main(String[] args) {
		SpringApplication.run(HroApplication.class, args);
	}

}

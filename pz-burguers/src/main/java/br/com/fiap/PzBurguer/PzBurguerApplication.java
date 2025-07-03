package br.com.fiap.PzBurguer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "BzBurguers API", description = "Api rest de sistemas de pedido", version = "v1"))
public class PzBurguerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PzBurguerApplication.class, args);
	}

}

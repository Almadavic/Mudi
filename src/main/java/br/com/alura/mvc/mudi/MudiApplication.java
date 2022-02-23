package br.com.alura.mvc.mudi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching // Habilita o cache. ( poe os dados do banco em memoria por um tempo) p n ter q fazer consulta no banco toda hr
@SpringBootApplication   // varre todas as anotações da aplicacao e faz funcionar
public class MudiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MudiApplication.class, args);
	}

}

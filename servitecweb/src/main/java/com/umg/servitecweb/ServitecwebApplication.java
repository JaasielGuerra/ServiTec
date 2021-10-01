package com.umg.servitecweb;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@SpringBootApplication
public class ServitecwebApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ServitecwebApplication.class, args);
	}

	/**
	 * Se registra el modulo Hibernate5 para JackSon a nivel de SpringBoot para que
	 * JackSon se encargue de la gestion de relaciones de entidades de Hibernate
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof org.springframework.http.converter.json.MappingJackson2HttpMessageConverter) {
				ObjectMapper mapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
				mapper.registerModule(new Hibernate5Module()); // <-- aqui se registra el modulo para JackSon
			}
		}
	}

}

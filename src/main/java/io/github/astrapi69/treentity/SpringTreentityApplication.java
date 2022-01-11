package io.github.astrapi69.treentity;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.github.astrapi69.treentity.config.ApplicationConfiguration;
import io.github.astrapi69.treentity.config.ApplicationProperties;
import io.github.astrapi69.treentity.config.SwaggerConfiguration;

@EnableTransactionManagement
@Import({ ApplicationConfiguration.class, SwaggerConfiguration.class })
@EnableConfigurationProperties({ ApplicationProperties.class })
@SpringBootApplication
public class SpringTreentityApplication
{

	public static void main(String[] args)
	{
		SpringApplication application = new SpringApplication(SpringTreentityApplication.class);
		application.run(args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext ctx)
	{
		return args -> {
			ApplicationProperties applicationProperties = ctx.getBean(ApplicationProperties.class);
			if (applicationProperties.isPrintingModeOn())
			{
				System.out.println("Let's inspect the beans provided by Spring Boot:");
				String[] beanNames = ctx.getBeanDefinitionNames();
				Arrays.stream(beanNames).sorted().forEach(System.out::println);
			}
		};
	}

}

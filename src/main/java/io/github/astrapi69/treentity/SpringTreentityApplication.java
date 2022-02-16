/**
 * The MIT License
 *
 * Copyright (C) 2021 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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

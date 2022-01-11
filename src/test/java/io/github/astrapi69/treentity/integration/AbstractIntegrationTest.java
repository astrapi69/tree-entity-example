package io.github.astrapi69.treentity.integration;

import java.time.Duration;
import java.util.Map;
import java.util.stream.Stream;

import lombok.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public abstract class AbstractIntegrationTest
{

	/**
	 * see 'https://hub.docker.com/_/postgres?tab=tags&page=1&name=12.5'
	 */
	private static final String IMAGE_VERSION = "postgres:12.5";
	@Autowired
	protected TestEntityManager entityManager;

	static class Initializer
		implements
			ApplicationContextInitializer<ConfigurableApplicationContext>
	{

		static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(IMAGE_VERSION)
			.withDatabaseName("treentity").withUsername("postgres").withPassword("postgres")
			.withStartupTimeout(Duration.ofSeconds(600));

		private static void startContainers()
		{
			Startables.deepStart(Stream.of(postgres)).join();
			// we can add further containers
			// here like rabbitmq or other databases
		}

		private static @NonNull Map<String, Object> createConnectionConfiguration()
		{
			return ImmutableMap.of("spring.datasource.url", postgres.getJdbcUrl(),
				"spring.datasource.username", postgres.getUsername(), "spring.datasource.password",
				postgres.getPassword());
		}


		@Override
		public void initialize(@NonNull ConfigurableApplicationContext applicationContext)
		{
			startContainers();
			ConfigurableEnvironment environment = applicationContext.getEnvironment();
			MapPropertySource testcontainers = new MapPropertySource("testcontainers",
				createConnectionConfiguration());
			environment.getPropertySources().addFirst(testcontainers);
		}

	}
}

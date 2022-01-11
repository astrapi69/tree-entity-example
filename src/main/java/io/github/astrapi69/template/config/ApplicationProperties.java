package io.github.astrapi69.template.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ConfigurationProperties(prefix = "app")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationProperties
{

	String dbHost;
	String dbName;
	boolean printingModeOn;
	int dbPort;
	String dbUrlPrefix;
	String dbUsername;
	String dbPassword;
	String dir;
	String name;
	String basePackage;
	String apiInfoTitle;
	String apiInfoDescription;
	String contactName;
	String contactUrl;

}

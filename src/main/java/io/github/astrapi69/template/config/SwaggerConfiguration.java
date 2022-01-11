package io.github.astrapi69.template.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@AllArgsConstructor
@EnableSwagger2
public class SwaggerConfiguration extends AbstractSwaggerConfiguration
{

	ApplicationProperties applicationProperties;

	@Bean public Docket api()
	{
		return super.api();
	}

	protected ApiInfo metaData()
	{
		return super.metaData();
	}

	@Override public String getBasePackage()
	{
		return applicationProperties.getBasePackage();
	}

	@Override public String getApiInfoTitle()
	{
		return applicationProperties.getApiInfoTitle();
	}

	@Override public String getApiInfoDescription()
	{
		return applicationProperties.getApiInfoDescription();
	}

	@Override public String getApiInfoVersion()
	{
		return ApplicationConfiguration.VERSION_API_1;
	}

	@Override public String getContactName()
	{
		return applicationProperties.getContactName();
	}

	@Override public String getContactUrl()
	{
		return applicationProperties.getContactUrl();
	}

	@Override public String getDocketPathsPathRegex()
	{
		return ApplicationConfiguration.REST_VERSION + "/.*|";
	}

}

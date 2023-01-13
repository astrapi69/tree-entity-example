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
package io.github.astrapi69.treentity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@AllArgsConstructor
@EnableSwagger2
public class SwaggerConfiguration extends AbstractSwaggerConfiguration
{

	ApplicationProperties applicationProperties;

	@Bean
	public Docket api()
	{
		return super.api();
	}

	protected ApiInfo metaData()
	{
		return super.metaData();
	}

	@Override
	public String getBasePackage()
	{
		return applicationProperties.getBasePackage();
	}

	@Override
	public String getApiInfoTitle()
	{
		return applicationProperties.getApiInfoTitle();
	}

	@Override
	public String getApiInfoDescription()
	{
		return applicationProperties.getApiInfoDescription();
	}

	@Override
	public String getApiInfoVersion()
	{
		return ApplicationConfiguration.VERSION_API_1;
	}

	@Override
	public String getContactName()
	{
		return applicationProperties.getContactName();
	}

	@Override
	public String getContactUrl()
	{
		return applicationProperties.getContactUrl();
	}

	@Override
	public String getDocketPathsPathRegex()
	{
		return ApplicationConfiguration.REST_VERSION + "/.*|";
	}

}

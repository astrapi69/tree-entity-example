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

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public abstract class AbstractSwaggerConfiguration
{
	public abstract String getBasePackage();

	public abstract String getApiInfoTitle();

	public abstract String getApiInfoDescription();

	public abstract String getApiInfoVersion();

	public abstract String getContactName();

	public abstract String getContactUrl();

	public abstract String getDocketPathsPathRegex();

	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2).select()
			.apis(RequestHandlerSelectors.basePackage(getBasePackage()))
			.paths(regex(getDocketPathsPathRegex())).build().apiInfo(metaData());
	}

	protected ApiInfo metaData()
	{
		return new ApiInfo(getApiInfoTitle(), getApiInfoDescription(), getApiInfoVersion(),
			getApiInfoTermsOfServiceUrl(),
			new Contact(getContactName(), getContactUrl(), getContactEmail()), getContactLicense(),
			getContactLicenseUrl(), Collections.emptyList());
	}

	public String getContactEmail()
	{
		return "";
	}

	public String getContactLicense()
	{
		return "";
	}

	public String getContactLicenseUrl()
	{
		return "";
	}

	protected String getApiInfoTermsOfServiceUrl()
	{
		return "";
	}

}

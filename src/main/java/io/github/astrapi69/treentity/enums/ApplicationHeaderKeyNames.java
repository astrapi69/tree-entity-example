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
package io.github.astrapi69.treentity.enums;

public enum ApplicationHeaderKeyNames
{
	DEFAULT_TOKEN_ISSUER_VALUE(
		ApplicationHeaderKeyNames.DEFAULT_TOKEN_ISSUER), HEADER_KEY_ROLES_VALUE(
			ApplicationHeaderKeyNames.HEADER_KEY_ROLES), DEFAULT_TOKEN_AUDIENCE_VALUE(
				ApplicationHeaderKeyNames.DEFAULT_TOKEN_AUDIENCE);

	public static final String DEFAULT_TOKEN_ISSUER = "user-auth-api";
	public static final String DEFAULT_TOKEN_AUDIENCE = "user-auth-app";
	public static final String HEADER_KEY_ROLES = "u-roles";

	private final String name;

	ApplicationHeaderKeyNames(final String name)
	{
		this.name = name;
	}

	/**
	 * Gets the specific name
	 *
	 * @return the specific name
	 */
	public String getName()
	{
		return name;
	}
}

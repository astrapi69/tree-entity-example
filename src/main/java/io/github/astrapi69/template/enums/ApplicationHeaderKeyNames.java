package io.github.astrapi69.template.enums;

public enum ApplicationHeaderKeyNames
{
	DEFAULT_TOKEN_ISSUER_VALUE(ApplicationHeaderKeyNames.DEFAULT_TOKEN_ISSUER),
	HEADER_KEY_ROLES_VALUE(ApplicationHeaderKeyNames.HEADER_KEY_ROLES),
	DEFAULT_TOKEN_AUDIENCE_VALUE(ApplicationHeaderKeyNames.DEFAULT_TOKEN_AUDIENCE);
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

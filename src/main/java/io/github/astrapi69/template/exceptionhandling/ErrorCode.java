package io.github.astrapi69.template.exceptionhandling;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode
{
	USERNAME_OR_PASSWORD_VALIDATION_FAILED(0, "Username or password does not exist or password is not typed correctly");

	int code;
	String message;

}

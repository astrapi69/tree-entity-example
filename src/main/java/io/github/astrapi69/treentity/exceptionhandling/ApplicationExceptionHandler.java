package io.github.astrapi69.treentity.exceptionhandling;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import io.github.astrapi69.spring.exceptionhandling.ControllerExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationExceptionHandler extends ControllerExceptionHandler
{

	public ApplicationExceptionHandler(MessageSource messageSource)
	{
		super(messageSource);
	}

}

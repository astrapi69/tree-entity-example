package io.github.astrapi69.template.service;

import org.springframework.stereotype.Service;

import io.github.astrapi69.template.jpa.entity.Templates;
import io.github.astrapi69.template.jpa.repository.TemplatesRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TemplatesService
{
	TemplatesRepository repository;

	public Templates save(Templates templates)
	{
		return repository.save(templates);
	}

}

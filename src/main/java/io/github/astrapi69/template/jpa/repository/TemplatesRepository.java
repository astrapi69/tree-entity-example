package io.github.astrapi69.template.jpa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.astrapi69.template.jpa.entity.Templates;

public interface TemplatesRepository extends JpaRepository<Templates, UUID>
{
}

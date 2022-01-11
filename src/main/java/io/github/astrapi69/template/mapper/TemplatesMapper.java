package io.github.astrapi69.template.mapper;

import io.github.astrapi69.template.jpa.entity.Templates;
import io.github.astrapi69.template.viewmodel.Template;
import org.springframework.stereotype.Component;

import io.github.astrapi69.bean.mapper.AbstractGenericMapper;

@Component
public class TemplatesMapper extends AbstractGenericMapper<Templates, Template>
{
}

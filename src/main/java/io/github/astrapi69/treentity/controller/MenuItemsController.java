package io.github.astrapi69.treentity.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.astrapi69.treentity.config.ApplicationConfiguration;
import io.github.astrapi69.treentity.jpa.entity.MenuItems;
import io.github.astrapi69.treentity.mapper.MenuItemsMapper;
import io.github.astrapi69.treentity.service.MenuItemsService;
import io.github.astrapi69.treentity.viewmodel.MenuItem;

@RestController
@RequestMapping(ApplicationConfiguration.REST_VERSION + MenuItemsController.REST_PATH)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuItemsController
{
	public static final String REST_PATH = "/menuitems";
	MenuItemsService menuItemsService;
	MenuItemsMapper mapper;

	@RequestMapping(method = RequestMethod.POST)
	public MenuItem newTemplate()
	{
		MenuItems entity = menuItemsService
			.save(MenuItems.builder().parent(null).depth(0).node(true).value("foo").build());
		return mapper.toDto(entity);
	}
}

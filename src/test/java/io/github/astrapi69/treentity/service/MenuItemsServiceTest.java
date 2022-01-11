package io.github.astrapi69.treentity.service;

import io.github.astrapi69.treentity.integration.AbstractIntegrationTest;
import io.github.astrapi69.treentity.jpa.entity.MenuItems;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemsServiceTest extends AbstractIntegrationTest
{
	@Autowired
	MenuItemsService menuItemsService;

	@Test void save()
	{
		String value;
		value = "New";
		MenuItems root = MenuItems.builder().parent(null).depth(0).node(true)
			.value(value).build();

		value = "JPA";
		MenuItems newJpa = MenuItems.builder().parent(root).value(value)
			.node(true).depth(1).build();
		MenuItems savedRoot = menuItemsService.save(root);
		assertNotNull(savedRoot);

		MenuItems savedNewJpa = menuItemsService.save(newJpa);
		assertNotNull(savedNewJpa);
		assertFalse(menuItemsService.hasChildren(savedNewJpa));
		assertTrue(menuItemsService.hasChildren(savedRoot));
	}

	@Test void getAllChildren()
	{
	}

	@Test void hasChildren()
	{
	}

	@Test void hasParent()
	{
	}
}

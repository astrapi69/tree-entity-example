package io.github.astrapi69.treentity.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.astrapi69.treentity.integration.AbstractIntegrationTest;
import io.github.astrapi69.treentity.jpa.entity.MenuItems;

public class MenuItemsServiceTest extends AbstractIntegrationTest
{
	@Autowired
	MenuItemsService menuItemsService;

	@Test
	void save()
	{
		String value;
		value = "New";
		MenuItems root = MenuItems.builder().parent(null).depth(0).node(true).value(value).build();

		value = "JPA";
		MenuItems newJpa = MenuItems.builder().parent(root).value(value).node(true).depth(1)
			.build();
		MenuItems savedRoot = menuItemsService.save(root);
		assertNotNull(savedRoot);

		MenuItems savedNewJpa = menuItemsService.save(newJpa);
		assertNotNull(savedNewJpa);
	}

	@Test
	void getAllChildren()
	{
		String value;
		value = "New";
		MenuItems root = MenuItems.builder().parent(null).depth(0).node(true).value(value).build();

		value = "JPA";
		MenuItems newJpa = MenuItems.builder().parent(root).value(value).node(true).depth(1)
			.build();

		MenuItems savedRoot = menuItemsService.save(root);
		MenuItems savedNewJpa = menuItemsService.save(newJpa);

		value = "Project";
		MenuItems newProject = MenuItems.builder().parent(root).value(value).node(true).depth(1)
			.build();
		MenuItems savedNewProject = menuItemsService.save(newProject);

		value = "Entity";
		MenuItems newJpaEntity = MenuItems.builder().parent(savedNewJpa).value(value).node(true)
			.depth(2).build();
		MenuItems savedNewJpaEntity = menuItemsService.save(newJpaEntity);
		List<MenuItems> allChildren = menuItemsService.getAllChildren(savedRoot);

		assertNotNull(allChildren);
		assertEquals(3, allChildren.size());

		MenuItems firstMenuItem = allChildren.get(0);
		assertEquals(savedNewJpa, firstMenuItem);
		MenuItems parent = firstMenuItem.getParent();
		assertEquals(savedRoot, parent);
		MenuItems secondMenuItem = allChildren.get(1);
		assertEquals(savedNewProject, secondMenuItem);
		parent = secondMenuItem.getParent();
		assertEquals(savedRoot, parent);
		MenuItems thirdMenuItem = allChildren.get(2);
		assertEquals(savedNewJpaEntity, thirdMenuItem);
		parent = thirdMenuItem.getParent();
		assertEquals(savedNewJpa, parent);
	}

	@Test
	void hasChildren()
	{
		String value;
		value = "New";
		MenuItems root = MenuItems.builder().parent(null).depth(0).node(true).value(value).build();

		value = "JPA";
		MenuItems newJpa = MenuItems.builder().parent(root).value(value).node(true).depth(1)
			.build();
		MenuItems savedRoot = menuItemsService.save(root);
		assertNotNull(savedRoot);

		MenuItems savedNewJpa = menuItemsService.save(newJpa);
		assertNotNull(savedNewJpa);
		assertFalse(menuItemsService.hasChildren(savedNewJpa));
		assertTrue(menuItemsService.hasChildren(savedRoot));
	}

	@Test
	void hasParent()
	{
		String value;
		value = "New";
		MenuItems root = MenuItems.builder().parent(null).depth(0).node(true).value(value).build();

		value = "JPA";
		MenuItems newJpa = MenuItems.builder().parent(root).value(value).node(true).depth(1)
			.build();
		MenuItems savedRoot = menuItemsService.save(root);
		assertNotNull(savedRoot);

		MenuItems savedNewJpa = menuItemsService.save(newJpa);
		assertNotNull(savedNewJpa);
		assertTrue(menuItemsService.hasParent(savedNewJpa));
		assertFalse(menuItemsService.hasParent(savedRoot));
	}
}

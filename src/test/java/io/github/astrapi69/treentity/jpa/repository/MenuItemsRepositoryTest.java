package io.github.astrapi69.treentity.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.astrapi69.treentity.integration.AbstractIntegrationTest;
import io.github.astrapi69.treentity.jpa.entity.MenuItems;

public class MenuItemsRepositoryTest extends AbstractIntegrationTest
{

	@Autowired
	MenuItemsRepository repository;

	@Test
	public void whenFindAncestors()
	{
		String value;
		value = "New";
		MenuItems root = MenuItems.builder().parent(null).depth(0).node(true).value(value).build();

		value = "JPA";
		MenuItems newJpa = MenuItems.builder().parent(root).value(value).node(true).depth(1)
			.build();

		MenuItems savedRoot = repository.save(root);
		MenuItems savedNewJpa = repository.save(newJpa);

		List<MenuItems> newJpaList = repository.findByValue(value);
		assertNotNull(newJpaList);
		assertEquals(1, newJpaList.size());

		MenuItems firstMenuItem = newJpaList.get(0);
		assertEquals(savedNewJpa, firstMenuItem);
		MenuItems parent = firstMenuItem.getParent();
		assertEquals(savedRoot, parent);

		value = "Project";
		MenuItems newProject = MenuItems.builder().parent(root).value(value).node(true).depth(1)
			.build();
		MenuItems savedNewProject = repository.save(newProject);

		List<MenuItems> ancestors = repository.findAncestors(newProject.getId());
		assertNotNull(ancestors);
		ancestors.remove(savedNewProject);
		assertEquals(1, ancestors.size());
		assertEquals(savedRoot, ancestors.get(0));
	}
}

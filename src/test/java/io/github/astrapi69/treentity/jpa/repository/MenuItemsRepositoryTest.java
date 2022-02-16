/**
 * The MIT License
 *
 * Copyright (C) 2021 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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

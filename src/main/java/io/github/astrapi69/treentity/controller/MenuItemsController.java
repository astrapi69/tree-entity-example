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

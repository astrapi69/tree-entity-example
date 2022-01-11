package io.github.astrapi69.treentity.service;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Service;

import io.github.astrapi69.treentity.jpa.entity.MenuItems;
import io.github.astrapi69.treentity.jpa.repository.MenuItemsRepository;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuItemsService
{
	MenuItemsRepository repository;

	public MenuItems save(MenuItems menuItem)
	{
		return repository.save(menuItem);
	}

	public List<MenuItems> getAllChildren(MenuItems parent)
	{
		List<MenuItems> allChildrenWithParent = repository.getAllChildrenWithParent(parent.getId());
		allChildrenWithParent.remove(parent);
		return allChildrenWithParent;
	}

	public boolean hasChildren(final MenuItems menuItem)
	{
		boolean empty = repository.getChildren(menuItem.getId()).isEmpty();
		return !empty;
	}

	public boolean hasParent(final MenuItems menuItem)
	{
		return null != menuItem.getParent();
	}

}

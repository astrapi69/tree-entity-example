package io.github.astrapi69.treentity.jpa.repository;

import io.github.astrapi69.treentity.jpa.entity.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuItemsRepository extends JpaRepository<MenuItems, UUID>
{

	List<MenuItems> findByValue(String value);

	@Transactional
	@Query("select entity from MenuItems entity where entity.depth=:depth "
		+ " and entity.value=:value") List<MenuItems> findByDepthAndValue(@Param("depth") int depth,
		@Param("value") String value);

	@Transactional
	@Query("select entity from MenuItems entity where entity.depth=:depth "
		+ " and entity.value=:value " + " and entity.parent=:parent")
	List<MenuItems> findByDepthAndValueAndParent(@Param("depth") int depth,
		@Param("value") String value, @Param("parent") MenuItems parent);

	@Transactional
	@Query("select entity from MenuItems entity where entity.value=:value "
		+ " and entity.parent is null") Optional<MenuItems> findRootByValue(@Param("value") String value);

	@Query("select entity from MenuItems entity where entity.depth=:depth "
		+ " and entity.value=:value " + " and entity.parent=:parent " +
		"and entity.node=:node")
	Optional<MenuItems> findByDepthAndValueAndParentAndNode(@Param("depth") int depth,
		@Param("value") String value, @Param("parent") MenuItems parent, @Param("node") boolean node);

	@Query(value = "WITH RECURSIVE ancestors(id, parent_id, value, level) AS ("
		+ " SELECT pkp.id, pkp.parent_id, pkp.value, 1 AS level "
		+ " FROM menu_items pkp "
		+ " WHERE pkp.id = :treeId "
		+ " UNION ALL "
		+ " SELECT parent.id, parent.parent_id, parent.value, child.level + 1 AS level "
		+ " FROM menu_items parent " + " JOIN ancestors child "
		+ " ON parent.id = child.parent_id " + " )"
		+ "SELECT * from ancestors ORDER BY level DESC", nativeQuery = true)
	List<MenuItems> findAncestors(@Param("treeId") UUID treeId);

	@Query(value = "WITH RECURSIVE children(id, parent_id, value) AS ("
		+ " SELECT pkp.id, pkp.parent_id, pkp.value, 1 AS level "
		+ " FROM menu_items pkp "
		+ " WHERE pkp.id=:treeId "
		+ " UNION ALL "
		+ " SELECT parent.id, parent.parent_id, parent.value, child.level + 1 AS level "
		+ " FROM menu_items parent " + " JOIN children child "
		+ " ON child.id = parent.parent_id) "
		+ " SELECT * FROM children "
		, nativeQuery = true)
	List<MenuItems> getAllChildrenWithParent(@Param("treeId") UUID treeId);

	@Query(value = "select * from menu_items pkp where pkp.parent_id =:parent", nativeQuery = true)
	List<MenuItems> getChildren(@Param("parent") UUID parent);
}

package io.github.astrapi69.template.jpa.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.github.astrapi69.entity.identifiable.Identifiable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = Templates.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Templates
{
	static final String SINGULAR_ENTITY_NAME = "template";
	static final String TABLE_NAME = SINGULAR_ENTITY_NAME + "s";
	@Id
	@GeneratedValue(generator = "UUID")
	@Column(name = Identifiable.COLUMN_NAME_ID, updatable = false, nullable = false)
	private UUID id;

	/** The optional filepath from this resource bunlde. */
	@Column(name = "name", length = 128)
	String name;
}

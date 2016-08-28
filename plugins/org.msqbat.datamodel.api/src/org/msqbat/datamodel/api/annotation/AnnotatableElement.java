package org.msqbat.datamodel.api.annotation;

import java.util.Collection;

/**
 *
 * An object to which meta-information can be attached.
 * </p>
 * This information is stored in an {@link Annotation} object.
 *
 */
public interface AnnotatableElement {

	/**
	 *
	 * Returns annotations which are assigned to this {@code AnnotatableElement}
	 *
	 * @return annotations which are assigned to this {@code AnnotatableElement}
	 */
	Collection<AnnotationSerializable> getAnnotation();

	void setAnnotation(Collection<AnnotationSerializable> annotation);
}

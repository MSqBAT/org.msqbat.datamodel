
package org.msqbat.datamodel.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.msqbat.datamodel.api.annotation.AnnotatableElement;
import org.msqbat.datamodel.api.annotation.AnnotationSerializable;

/**
 *
 * Prototype implementation for {@link AnnotatableElement}.
 *
 *
 *
 *
 */
public class AnnotatableElementProto implements AnnotatableElement, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6039273072419562954L;
	private Collection<AnnotationSerializable> annotation = new ArrayList<AnnotationSerializable>(0);

	@Override
	public Collection<AnnotationSerializable> getAnnotation() {

		return annotation;
	}

	public void setAnnotation(final AnnotationSerializable annotation) {

		this.setAnnotation(Arrays.asList(annotation));
	}

	@Override
	public void setAnnotation(final Collection<AnnotationSerializable> annotation) {

		this.annotation = annotation;
	}
}

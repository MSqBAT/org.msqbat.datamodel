
package org.msqbat.datamodel.api.annotation;

import java.io.Serializable;
import java.util.Map;

import net.sf.kerner.utils.Cloneable;

public interface AnnotationSerializable
		extends Annotation<Serializable, Serializable>, Serializable, Cloneable<AnnotationSerializable> {

	Map<Serializable, Serializable> getAll();
}

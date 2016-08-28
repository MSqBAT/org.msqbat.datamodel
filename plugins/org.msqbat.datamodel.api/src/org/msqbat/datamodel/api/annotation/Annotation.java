package org.msqbat.datamodel.api.annotation;

/**
 *
 * A collection of additional meta-informations which are assigned to an
 * {@link AnnotatableElement}.
 *
 *
 * @param <K>
 *            the type of annotation keys/ identifiers maintained by this
 *            {@code Annotation}
 * @param <V>
 *            the type of annotation values maintained by this
 *            {@code Annotation}
 *
 * @see AnnotatableElement
 * @see AnnotationSerializable
 */
public interface Annotation<K, V> {

	V get(K key);

	Annotation<K, V> put(K key, V value);
}

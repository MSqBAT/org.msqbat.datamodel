
package org.msqbat.datamodel.api.sample;

import java.io.File;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.msqbat.datamodel.api.annotation.AnnotatableElement;

import com.google.common.collect.Range;

import net.sf.kerner.utils.Cloneable;

public interface SampleMSqBAT extends Cloneable<SampleMSqBAT>, Serializable, AnnotatableElement {

	SampleMSqBAT addFiles(Collection<? extends File> files);

	SampleMSqBAT addFiles(File... files);

	@Override
	SampleMSqBAT clone();

	List<File> getFiles();

	String getName();

	Range<Double> getRangeMz();

	Range<Double> getRangeRetentionTime();

	SampleMSqBAT setName(String name);

}

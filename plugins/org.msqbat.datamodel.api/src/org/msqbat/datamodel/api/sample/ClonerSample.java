
package org.msqbat.datamodel.api.sample;

import net.sf.kerner.utils.Cloner;

public interface ClonerSample extends Cloner<SampleMSqBAT> {

	public static enum CloneType {
		ALL, NO_IONS, NO_RAW
	}

	SampleMSqBAT clone(SampleMSqBAT sample, CloneType... types);
}

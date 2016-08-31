
package org.msqbat.datamodel.api.ion;

import java.util.List;

public interface FactoryIon {

	IonMSqBAT build(double mz, float intensity);

	List<IonMSqBAT> build(double[] mzValues, float[] intensityValues);
}

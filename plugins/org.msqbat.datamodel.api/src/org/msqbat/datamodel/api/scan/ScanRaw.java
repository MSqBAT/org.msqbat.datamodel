
package org.msqbat.datamodel.api.scan;

import org.msqbat.datamodel.api.provider.ProviderIntensity;

import com.google.common.collect.Range;

public interface ScanRaw extends ScanMSqBAT, ProviderIntensity {

	float[] getIntensityValues();

	double[] getMzValues();

	Range<Double> getRangeMz();

	ScanRaw setIntensityValues(float[] intensityValues);

	ScanRaw setMzValues(double[] mzValues);
}


package org.msqbat.datamodel.impl.ion;

import java.util.ArrayList;
import java.util.List;

import org.msqbat.datamodel.api.ion.FactoryIon;
import org.msqbat.datamodel.api.ion.IonMSqBAT;
import org.msqbat.datamodel.api.provider.FactoryProviderMz;
import org.msqbat.datamodel.api.provider.ProviderMz;

public class FactoryIonImpl implements FactoryIon, FactoryProviderMz {

	public final static double DEFAULT_INTENSITY = 1;

	public static final double[] DEFAULT_RETENTION_TIMES = new double[] { 0 };

	public static final int DEFAULT_SCAN_NUMBER = 0;

	private final double[] retentionTimes;

	private final int scanNumber;

	public FactoryIonImpl() {
		this(DEFAULT_SCAN_NUMBER, DEFAULT_RETENTION_TIMES);
	}

	public FactoryIonImpl(final int scanNumber, final double[] retentionTimes) {
		this.retentionTimes = retentionTimes;
		this.scanNumber = scanNumber;
	}

	@Override
	public ProviderMz build(final double mz) {
		return new IonImpl(mz, DEFAULT_INTENSITY);
	}

	@Override
	public synchronized IonMSqBAT build(final double mz, final float intensity) {

		return new IonImpl(mz, intensity).setRetentionTimes(retentionTimes).setScanNumber(scanNumber);
	}

	@Override
	public synchronized List<IonMSqBAT> build(final double[] mzValues, final float[] intensityValues) {
		final List<IonMSqBAT> result = new ArrayList<>();
		if (mzValues.length == intensityValues.length) {
			for (int i = 0; i < mzValues.length; i++) {
				result.add(build(mzValues[i], intensityValues[i]));
			}
			return result;
		}
		throw new IllegalArgumentException("Arrays must have same size");
	}
}

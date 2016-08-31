package org.msqbat.datamodel.api.sample;

import java.math.BigDecimal;
import java.util.Collection;

import org.msqbat.datamodel.api.scan.ScanIons;
import org.msqbat.datamodel.api.scan.ScanMSqBAT;

public class Samples {

	public static BigDecimal getHighestIntensity(final Collection<? extends SampleMSqBAT> samples) {
		BigDecimal result = BigDecimal.ZERO;
		for (final SampleMSqBAT s : samples) {
			final BigDecimal i = getTotalIntensity(s);
			if (i.compareTo(result) > 0) {
				result = i;
			}
		}
		return result;
	}

	public static BigDecimal getTotalIntensity(final Collection<? extends SampleMSqBAT> samples) {
		BigDecimal result = BigDecimal.ZERO;
		for (final SampleMSqBAT s : samples) {
			if (s instanceof SampleIons) {
				result = result.add(getTotalIntensity(s));
			}
		}
		return result;
	}

	public static BigDecimal getTotalIntensity(final SampleMSqBAT sample) {
		BigDecimal result = BigDecimal.ZERO;
		if (sample instanceof SampleIons) {
			for (final ScanMSqBAT s : ((SampleIons) sample).getScans()) {
				if (s instanceof ScanIons) {
					result = result.add(new BigDecimal(((ScanIons) s).getIntensity()));
				}
			}
		}
		return result;
	}

}

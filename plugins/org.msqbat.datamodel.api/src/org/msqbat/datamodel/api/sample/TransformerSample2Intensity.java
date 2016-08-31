package org.msqbat.datamodel.api.sample;

import java.math.BigDecimal;

import net.sf.kerner.utils.collections.list.AbstractTransformingListFactory;

public class TransformerSample2Intensity extends AbstractTransformingListFactory<SampleMSqBAT, BigDecimal> {

	@Override
	public BigDecimal transform(final SampleMSqBAT element) {
		if (element instanceof SampleIons) {
			return Samples.getTotalIntensity(element);
		}
		return BigDecimal.ZERO;
	}

}

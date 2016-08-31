
package org.msqbat.datamodel.api.scan;

import org.msqbat.datamodel.api.sample.SampleMSqBAT;

import net.sf.kerner.utils.collections.list.AbstractTransformingListFactory;

public class TransformerScanToSample extends AbstractTransformingListFactory<ScanMSqBAT, SampleMSqBAT> {

	@Override
	public SampleMSqBAT transform(final ScanMSqBAT element) {

		return element.getSample();
	}
}

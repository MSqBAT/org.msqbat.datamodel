
package org.msqbat.datamodel.api.sample;

import net.sf.kerner.utils.collections.list.AbstractTransformingListFactory;

public class TransformerSample2Name extends AbstractTransformingListFactory<SampleMSqBAT, String> {

	@Override
	public String transform(final SampleMSqBAT element) {

		return element.getName();
	}
}

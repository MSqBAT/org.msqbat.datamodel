
package org.msqbat.datamodel.api.provider;

import net.sf.kerner.utils.collections.list.AbstractTransformingListFactory;

public class TransformerProviderScanNumber2ScanNumber
		extends AbstractTransformingListFactory<ProviderScanNumber, Integer> {

	@Override
	public synchronized Integer transform(final ProviderScanNumber element) {

		return element.getScanNumber();
	}
}

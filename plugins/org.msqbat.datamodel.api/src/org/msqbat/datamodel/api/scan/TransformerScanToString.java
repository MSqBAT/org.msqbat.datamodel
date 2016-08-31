
package org.msqbat.datamodel.api.scan;

import net.sf.kerner.utils.collections.list.AbstractTransformingListFactory;

public class TransformerScanToString extends AbstractTransformingListFactory<ScanMSqBAT, String> {

	@Override
	public String transform(final ScanMSqBAT element) {

		return element.getName();
	}
}

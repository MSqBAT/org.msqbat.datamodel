
package org.msqbat.datamodel.api.scan;

import java.util.Collection;
import java.util.HashSet;

import org.msqbat.datamodel.api.ion.IonMSqBAT;

import net.sf.kerner.utils.collections.list.AbstractTransformingListFactory;

public class TransformerScan2Ions extends AbstractTransformingListFactory<ScanMSqBAT, Collection<IonMSqBAT>> {

	@Override
	public Collection<IonMSqBAT> transform(final ScanMSqBAT element) {
		final Collection<IonMSqBAT> result = new HashSet<>();
		if (element instanceof ScanIons) {
			for (final IonMSqBAT p : ((ScanIons) element).getIons()) {
				result.add(p);
			}
		}
		return result;
	}
}

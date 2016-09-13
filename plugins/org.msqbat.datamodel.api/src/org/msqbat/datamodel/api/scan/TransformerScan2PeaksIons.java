package org.msqbat.datamodel.api.scan;

import java.util.ArrayList;
import java.util.Collection;

import org.msqbat.datamodel.api.ion.IonMSqBAT;
import org.msqbat.datamodel.api.peak.FeatureMSqBAT;

import net.sf.kerner.utils.collections.list.AbstractTransformingListFactory;

public class TransformerScan2PeaksIons extends AbstractTransformingListFactory<ScanMSqBAT, Collection<IonMSqBAT>> {

	@Override
	public Collection<IonMSqBAT> transform(final ScanMSqBAT element) {
		final Collection<IonMSqBAT> result = new ArrayList<>();
		if (element instanceof ScanIons) {
			for (final IonMSqBAT p : ((ScanIons) element).getIons()) {
				result.add(p);
				if (p instanceof FeatureMSqBAT) {
					result.addAll(((FeatureMSqBAT) p).getIons());
				}
			}
		}
		return result;
	}
}


package org.msqbat.datamodel.api.ion;

import java.util.Collection;
import java.util.Collections;

import org.msqbat.datamodel.api.ComparatorIntensity;
import org.msqbat.datamodel.api.SelectorProviderIntensity;

public class SelectorIonHighestIntensity implements SelectorProviderIntensity<IonMSqBAT>, SelectorIon {

	private static final long serialVersionUID = -889138179354956489L;

	@Override
	public synchronized IonMSqBAT select(final Collection<? extends IonMSqBAT> elements) {
		if (elements == null || elements.isEmpty()) {
			throw new IllegalArgumentException();
		}
		final IonMSqBAT result = Collections.max(elements, new ComparatorIntensity<>());
		return result;
	}
}

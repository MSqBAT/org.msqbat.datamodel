
package org.msqbat.datamodel.api.ion;

import org.msqbat.datamodel.api.provider.ProviderIntensity;

import net.sf.kerner.utils.collections.list.AbstractTransformingListFactory;

public class TransformerIon2Intensity extends AbstractTransformingListFactory<ProviderIntensity, Double> {

	@Override
	public synchronized Double transform(final ProviderIntensity element) {

		return element.getIntensity();
	}
}


package org.msqbat.datamodel.api.ion;

import org.msqbat.datamodel.api.provider.ProviderIntensity;

import net.sf.kerner.utils.collections.list.AbstractTransformingListFactory;
import net.sf.kerner.utils.pair.Pair;
import net.sf.kerner.utils.pair.PairImpl;

public class TransformerIon2IntensityView<T extends ProviderIntensity>
		extends AbstractTransformingListFactory<T, Pair<Double, T>> {

	@Override
	public synchronized Pair<Double, T> transform(final T element) {

		return new PairImpl<>(element.getIntensity(), element);
	}
}

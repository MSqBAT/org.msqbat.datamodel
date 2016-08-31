
package org.msqbat.datamodel.api.ion;

import org.msqbat.datamodel.api.provider.ProviderRetentionTime;

import net.sf.kerner.utils.UtilArray;
import net.sf.kerner.utils.collections.list.AbstractTransformingListFactory;
import net.sf.kerner.utils.pair.Pair;
import net.sf.kerner.utils.pair.PairImpl;

public class TransformerIon2RetentionTimeView<T extends ProviderRetentionTime>
		extends AbstractTransformingListFactory<T, Pair<Double[], T>> {

	@Override
	public Pair<Double[], T> transform(final T element) {

		return new PairImpl<>(UtilArray.toObject(element.getRetentionTimes()), element);
	}

}

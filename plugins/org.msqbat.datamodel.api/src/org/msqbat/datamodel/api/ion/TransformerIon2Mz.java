
package org.msqbat.datamodel.api.ion;

import org.msqbat.datamodel.api.provider.ProviderMz;

import net.sf.kerner.utils.collections.list.AbstractTransformingListFactory;

public class TransformerIon2Mz extends AbstractTransformingListFactory<ProviderMz, Double> {

	@Override
	public synchronized Double transform(final ProviderMz element) {

		return element.getMz();
	}
}

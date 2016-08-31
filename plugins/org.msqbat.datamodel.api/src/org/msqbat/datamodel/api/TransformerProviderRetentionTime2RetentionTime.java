
package org.msqbat.datamodel.api;

import java.io.Serializable;

import org.msqbat.datamodel.api.provider.ProviderRetentionTime;

import net.sf.kerner.utils.UtilArray;
import net.sf.kerner.utils.collections.list.AbstractTransformingListFactory;

public class TransformerProviderRetentionTime2RetentionTime
		extends AbstractTransformingListFactory<ProviderRetentionTime, Double[]> implements Serializable {

	private static final long serialVersionUID = -2910653398202240919L;

	@Override
	public synchronized Double[] transform(final ProviderRetentionTime element) {

		return UtilArray.toObject(element.getRetentionTimes());
	}
}

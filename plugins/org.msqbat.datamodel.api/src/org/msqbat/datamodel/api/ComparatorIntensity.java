
package org.msqbat.datamodel.api;

import java.util.Comparator;

import org.msqbat.datamodel.api.provider.ProviderIntensity;

public class ComparatorIntensity<T extends ProviderIntensity> implements Comparator<T> {

	@Override
	public int compare(final T o1, final T o2) {

		return Double.compare(o1.getIntensity(), o2.getIntensity());
	}
}

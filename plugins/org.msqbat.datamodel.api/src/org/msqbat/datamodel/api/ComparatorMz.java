
package org.msqbat.datamodel.api;

import java.io.Serializable;
import java.util.Comparator;

import org.msqbat.datamodel.api.provider.ProviderMz;

public class ComparatorMz<T extends ProviderMz> implements Comparator<T>, Serializable {

	private static final long serialVersionUID = -3559958077856172736L;

	@Override
	public int compare(final T o1, final T o2) {

		return Double.compare(o1.getMz(), o2.getMz());
	}
}

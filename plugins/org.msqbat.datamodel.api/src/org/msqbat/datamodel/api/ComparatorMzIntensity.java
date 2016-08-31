
package org.msqbat.datamodel.api;

import java.io.Serializable;
import java.util.Comparator;

import org.msqbat.datamodel.api.ion.IonMSqBAT;

import net.sf.kerner.utils.comparator.ComparatorMulti;

public class ComparatorMzIntensity<T extends IonMSqBAT> implements Comparator<T>, Serializable {

	private static final long serialVersionUID = -3335067316786596445L;
	private final ComparatorMulti<T> delegate;

	@SuppressWarnings("unchecked")
	public ComparatorMzIntensity() {
		this.delegate = new ComparatorMulti<T>(new ComparatorMz<T>(), new ComparatorIntensity<T>());
	}

	@Override
	public int compare(final T o1, final T o2) {

		return delegate.compare(o1, o2);
	}
}


package org.msqbat.datamodel.impl.scan;

import java.io.Serializable;
import java.util.Comparator;

import org.msqbat.datamodel.api.ComparatorIntensity;
import org.msqbat.datamodel.api.ComparatorMz;
import org.msqbat.datamodel.api.ComparatorScanNumber;
import org.msqbat.datamodel.api.ion.IonMSqBAT;

import net.sf.kerner.utils.comparator.ComparatorMulti;

public class ComparatorScanNumberMzIntensity<T extends IonMSqBAT> implements Comparator<T>, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2733837800236459176L;
	private final ComparatorMulti<T> delegate;

	@SuppressWarnings("unchecked")
	public ComparatorScanNumberMzIntensity() {
		this.delegate = new ComparatorMulti<T>(new ComparatorScanNumber<T>(), new ComparatorMz<T>(),
				new ComparatorIntensity<T>());
	}

	@Override
	public int compare(final T o1, final T o2) {

		return delegate.compare(o1, o2);
	}
}

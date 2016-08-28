package org.msqbat.datamodel.api.peak;

import java.util.Collection;
import java.util.List;

import org.msqbat.datamodel.api.ion.IonMSqBAT;

public interface PeakMSqBAT extends IonMSqBAT {

	PeakMSqBAT cloneNewIons(Collection<? extends IonMSqBAT> newIons);

	List<IonMSqBAT> getIons();

	@Override
	double[] getRetentionTimes();

	double[] getRetentionTimesFirst();

	double[] getRetentionTimesLast();

	@Override
	int getScanNumber();

	int getScanNumberFirst();

	int getScanNumberLast();
}

package org.msqbat.datamodel.api.provider;

import java.util.Collection;

import org.msqbat.datamodel.api.ion.IonMSqBAT;

public interface ProviderIons<T extends IonMSqBAT> {

	ProviderIons<T> addIons(Collection<? extends T> ions);

	ProviderIons<T> addIons(@SuppressWarnings("unchecked") T... ions);

	ProviderIons<T> clear();

	Collection<T> getIons();

	ProviderIons<T> setIons(Collection<? extends T> ions);
}

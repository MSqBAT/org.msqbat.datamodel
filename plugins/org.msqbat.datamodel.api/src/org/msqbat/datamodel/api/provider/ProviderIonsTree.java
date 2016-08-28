package org.msqbat.datamodel.api.provider;

import java.util.NavigableSet;

import org.msqbat.datamodel.api.ion.IonMSqBAT;

public interface ProviderIonsTree<T extends IonMSqBAT> extends ProviderIons<T> {

	NavigableSet<T> getIonsTreeScanNumber();

	NavigableSet<T> getIonsTreeMz();

}

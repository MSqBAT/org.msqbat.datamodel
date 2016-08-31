
package org.msqbat.datamodel.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.msqbat.datamodel.api.TransformerProviderRetentionTime2RetentionTime;
import org.msqbat.datamodel.api.annotation.AnnotationSerializable;
import org.msqbat.datamodel.api.annotation.IonAnnotatable;
import org.msqbat.datamodel.api.annotation.PeakAnnotatable;
import org.msqbat.datamodel.api.ion.IonMSqBAT;
import org.msqbat.datamodel.api.ion.SelectorIon;
import org.msqbat.datamodel.api.ion.SelectorIonHighestIntensity;
import org.msqbat.datamodel.api.peak.PeakMSqBAT;
import org.msqbat.datamodel.api.provider.TransformerProviderScanNumber2ScanNumber;
import org.msqbat.datamodel.api.sample.SampleMSqBAT;
import org.msqbat.datamodel.api.scan.ScanMSqBAT;

public class PeakSimple implements PeakAnnotatable, Serializable {

	final static SelectorIon SELECTOR_DEFAULT = new SelectorIonHighestIntensity();
	/**
	 *
	 */
	private static final long serialVersionUID = -5505338262586040341L;
	final static TransformerProviderRetentionTime2RetentionTime TRANSFORMER_RETENTION_TIME = new TransformerProviderRetentionTime2RetentionTime();
	final static TransformerProviderScanNumber2ScanNumber TRANSFORMER_RETENTION_TIME_INDEX = new TransformerProviderScanNumber2ScanNumber();
	private Collection<AnnotationSerializable> annotation = new ArrayList<>();
	private transient Integer hashCode = null;
	private transient double intensity = -1;
	private final List<IonMSqBAT> ions = new ArrayList<>();
	private transient IonMSqBAT masterIon;
	private transient double mz = -1;
	private String name;

	private ScanMSqBAT scan;

	private SelectorIon selector;

	public PeakSimple(final Collection<? extends IonMSqBAT> ions) {
		if (ions == null || ions.isEmpty()) {
			throw new IllegalArgumentException("Member ions must not be empty");
		}
		selector = SELECTOR_DEFAULT;
		for (final IonMSqBAT i : ions) {
			if (i instanceof PeakMSqBAT) {
				throw new IllegalArgumentException("Member ions must not be peaks");
			}
			this.ions.add(i);
			i.setPeak(this);
		}
	}

	@Override
	public synchronized PeakSimple clone() {
		return cloneNewIons(getIons());
	}

	@Override
	public IonMSqBAT cloneNewIntensity(final double newIntensity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public PeakSimple cloneNewIons(final Collection<? extends IonMSqBAT> newIons) {
		final PeakSimple result = new PeakSimple(newIons);
		result.setName(getName());
		return result;
	}

	@Override
	public synchronized boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PeakSimple)) {
			return false;
		}
		final PeakSimple other = (PeakSimple) obj;
		if (ions == null) {
			if (other.ions != null) {
				return false;
			}
		} else if (!ions.equals(other.ions)) {
			return false;
		}
		return true;
	}

	@Override
	public Collection<AnnotationSerializable> getAnnotation() {
		final Set<AnnotationSerializable> result = new LinkedHashSet<>();
		result.addAll(annotation);
		for (final IonMSqBAT ion : getIons()) {
			if (ion instanceof IonAnnotatable) {
				result.addAll(((IonAnnotatable) ion).getAnnotation());
			}
		}
		return result;
	}

	@Override
	public synchronized double getIntensity() {

		if (intensity <= 0) {
			intensity = getIons().stream().mapToDouble(p -> p.getIntensity()).sum();
		}
		return intensity;

	}

	@Override
	public synchronized List<IonMSqBAT> getIons() {

		return Collections.unmodifiableList(ions);
	}

	public synchronized IonMSqBAT getMasterIon() {

		if (masterIon == null) {
			masterIon = getSelector().select(getIons());
		}
		return masterIon;

	}

	@Override
	public synchronized double getMz() {

		if (mz <= 0) {
			mz = getMasterIon().getMz();
		}
		return mz;

	}

	@Override
	public synchronized String getName() {

		return name;
	}

	@Override
	public PeakMSqBAT getPeak() {

		return this;
	}

	@Override
	public synchronized double[] getRetentionTimes() {

		return getMasterIon().getRetentionTimes();
	}

	@Override
	public synchronized double[] getRetentionTimesFirst() {

		final double[] result = new double[getRetentionTimes().length];
		Arrays.fill(result, Double.MAX_VALUE);
		final List<Double[]> rts = TRANSFORMER_RETENTION_TIME.transformCollection(getIons());
		for (final Double[] rt : rts) {
			if (rt.length != result.length) {
				throw new IllegalArgumentException(rt.length + " != " + result.length);
			}
			for (int i = 0; i < rt.length; i++) {
				if (rt[i] < result[i]) {
					result[i] = rt[i];
				}
			}
		}
		return result;
	}

	@Override
	public synchronized double[] getRetentionTimesLast() {

		final double[] result = new double[getRetentionTimes().length];
		Arrays.fill(result, Double.MIN_VALUE);
		final List<Double[]> rts = TRANSFORMER_RETENTION_TIME.transformCollection(getIons());
		for (final Double[] rt : rts) {
			if (rt.length != result.length) {
				throw new IllegalArgumentException(rt.length + " != " + result.length);
			}
			for (int i = 0; i < rt.length; i++) {
				if (rt[i] > result[i]) {
					result[i] = rt[i];
				}
			}
		}
		return result;
	}

	@Override
	public synchronized SampleMSqBAT getSample() {

		return getScan().getSample();
	}

	@Override
	public synchronized ScanMSqBAT getScan() {
		return scan;
	}

	@Override
	public synchronized int getScanNumber() {

		return getMasterIon().getScanNumber();
	}

	@Override
	public synchronized int getScanNumberFirst() {
		return Collections.min(TRANSFORMER_RETENTION_TIME_INDEX.transformCollection(getIons()));
	}

	@Override
	public synchronized int getScanNumberLast() {
		return Collections.max(TRANSFORMER_RETENTION_TIME_INDEX.transformCollection(getIons()));
	}

	public synchronized SelectorIon getSelector() {

		return selector;
	}

	@Override
	public synchronized int hashCode() {
		if (hashCode == null) {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((ions == null) ? 0 : ions.hashCode());
			hashCode = Integer.valueOf(result);
		}
		return hashCode.intValue();
	}

	@Override
	public void setAnnotation(final Collection<AnnotationSerializable> annotation) {
		this.annotation = new ArrayList<>(annotation);

	}

	@Override
	public synchronized PeakSimple setName(final String name) {

		this.name = name;
		return this;
	}

	@Override
	public synchronized PeakSimple setPeak(final PeakMSqBAT peak) {
		throw new UnsupportedOperationException();
	}

	@Override
	public PeakSimple setRetentionTimes(final double[] retentionTimes) {
		// ignore
		return this;
	}

	@Override
	public synchronized PeakSimple setSample(final SampleMSqBAT sample) {

		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized PeakSimple setScan(final ScanMSqBAT scan) {
		this.scan = scan;
		return this;
	}

	@Override
	public PeakSimple setScanNumber(final int scanNumber) {
		// ignore
		return this;
	}

	public synchronized void setSelector(final SelectorIon selector) {

		this.selector = selector;
	}

	@Override
	public String toString() {

		return "scan:" + getScanNumber() + ",mz:" + String.format("%8.4f", getMz());
	}
}

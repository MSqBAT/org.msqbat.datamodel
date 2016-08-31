
package org.msqbat.datamodel.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import org.msqbat.datamodel.api.ComparatorMzIntensity;
import org.msqbat.datamodel.api.annotation.IonAnnotatable;
import org.msqbat.datamodel.api.ion.IonMSqBAT;
import org.msqbat.datamodel.api.provider.ProviderIonsTree;
import org.msqbat.datamodel.api.sample.SampleMSqBAT;
import org.msqbat.datamodel.api.scan.ScanIons;
import org.msqbat.datamodel.impl.ion.FactoryIonImpl;

import com.google.common.collect.Range;
import com.google.common.primitives.Doubles;

public class ScanTree implements ScanIons, ProviderIonsTree<IonMSqBAT> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5405127789548137207L;

	private float[] intensityValues = new float[0];

	private final Set<IonMSqBAT> ions = new HashSet<>();

	private transient TreeSet<IonMSqBAT> ionsMz;

	private double[] mzValues = new double[0];

	private String name;

	private transient Range<Double> rangeMz;

	private double[] retentionTimes;

	private SampleMSqBAT sample;

	private int scanNumber;

	private transient double totalIntensity = 0;

	public ScanTree() {
		initTrees();
	}

	@Override
	public synchronized ScanTree addIons(final Collection<? extends IonMSqBAT> ions) {
		clearTrees();
		totalIntensity = 0;
		for (final IonMSqBAT i : ions) {
			i.setScan(this);
			i.setRetentionTimes(getRetentionTimes());
			i.setScanNumber(getScanNumber());
		}
		this.ions.addAll(ions);
		return this;
	}

	@Override
	public synchronized ScanTree addIons(final IonMSqBAT... ions) {
		return addIons(Arrays.asList(ions));
	}

	@Override
	public synchronized ScanTree clear() {
		clearTrees();
		if (!this.ions.isEmpty()) {
			System.err.println("Clearing " + ions.size() + " ions");
		}
		for (final IonMSqBAT i : ions) {
			i.setScan(null);
		}
		ions.clear();
		totalIntensity = 0;
		return this;
	}

	private void clearTrees() {
		if (ionsMz != null) {
			ionsMz.clear();
		}

	}

	@Override
	public synchronized ScanTree clone() {
		final ScanTree result = new ScanTree();
		result.setIntensityValues(Arrays.copyOf(getIntensityValues(), getIntensityValues().length));
		result.setMzValues(Arrays.copyOf(getMzValues(), getMzValues().length));
		result.setName(getName());
		if (retentionTimes != null) {
			result.setRetentionTimes(Arrays.copyOf(retentionTimes, retentionTimes.length));
		}
		result.setScanNumber(getScanNumber());
		final Collection<IonMSqBAT> ions = new ArrayList<>();
		for (final IonMSqBAT i : getIons()) {
			ions.add(i.clone());
		}
		result.setIons(ions);
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
		if (!(obj instanceof ScanTree)) {
			return false;
		}
		final ScanTree other = (ScanTree) obj;
		if (!Arrays.equals(intensityValues, other.intensityValues)) {
			return false;
		}
		if (ions == null) {
			if (other.ions != null) {
				return false;
			}
		} else if (!ions.equals(other.ions)) {
			return false;
		}
		if (!Arrays.equals(mzValues, other.mzValues)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (!Arrays.equals(retentionTimes, other.retentionTimes)) {
			return false;
		}
		if (scanNumber != other.scanNumber) {
			return false;
		}
		return true;
	}

	@Override
	public synchronized double getIntensity() {
		double result = totalIntensity;
		if (result <= 0) {
			if (ions == null || ions.isEmpty()) {
				for (final float i : intensityValues) {
					result += i;
				}
				return result;
			}
			for (final IonMSqBAT i : getIons()) {
				result += i.getIntensity();
			}
			totalIntensity = result;
		}
		return result;
	}

	@Override
	public synchronized float[] getIntensityValues() {
		return intensityValues;
	}

	@Override
	public synchronized Collection<IonMSqBAT> getIons() {
		if (ions == null || ions.isEmpty()) {
			initIons();
		}
		return Collections.unmodifiableCollection(ions);
	}

	@Override
	public synchronized NavigableSet<IonMSqBAT> getIonsTreeMz() {
		if (ionsMz == null) {
			initTrees();
		}
		if (ionsMz.isEmpty()) {
			ionsMz.addAll(ions);
		}
		return ionsMz;
	}

	@Override
	public synchronized NavigableSet<IonMSqBAT> getIonsTreeScanNumber() {
		// all ions anyway with the same scan number
		return ionsMz;
	}

	@Override
	public synchronized double[] getMzValues() {
		return mzValues;
	}

	@Override
	public synchronized String getName() {
		return name;
	}

	@Override
	public synchronized Range<Double> getRangeMz() {

		if (rangeMz == null) {
			rangeMz = Range.closed(Doubles.min(mzValues), Doubles.max(mzValues));
		}
		return rangeMz;
	}

	@Override
	public synchronized double[] getRetentionTimes() {
		return retentionTimes;
	}

	@Override
	public synchronized SampleMSqBAT getSample() {
		return sample;
	}

	@Override
	public synchronized int getScanNumber() {
		return scanNumber;
	}

	@Override
	public synchronized int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(intensityValues);
		result = prime * result + ((ions == null) ? 0 : ions.hashCode());
		result = prime * result + Arrays.hashCode(mzValues);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(retentionTimes);
		result = prime * result + scanNumber;
		return result;
	}

	public synchronized ScanTree initIons() {

		if (!ions.isEmpty()) {
			return this;
		}
		if (mzValues.length != intensityValues.length) {
			throw new IllegalStateException("mzValues and intensityValues must be the same length");
		}
		addIons(new FactoryIonImpl(getScanNumber(), getRetentionTimes()).build(mzValues, intensityValues));
		return this;

	}

	private void initTrees() {
		ionsMz = new TreeSet<>(new ComparatorMzIntensity<>());
	}

	@Override
	public synchronized ScanTree setIntensityValues(final float[] intensityValues) {
		clear();
		this.intensityValues = intensityValues;
		return this;
	}

	@Override
	public synchronized ScanTree setIons(final Collection<? extends IonMSqBAT> ions) {
		clear();
		addIons(ions);
		return this;
	}

	@Override
	public synchronized ScanTree setMzValues(final double[] mzValues) {
		clear();
		this.mzValues = mzValues;
		return this;
	}

	@Override
	public synchronized ScanTree setName(final String name) {
		this.name = name;
		return this;
	}

	@Override
	public synchronized ScanTree setRetentionTimes(final double[] retentionTimes) {
		this.retentionTimes = retentionTimes;
		return this;
	}

	@Override
	public synchronized ScanTree setSample(final SampleMSqBAT sample) {
		this.sample = sample;
		return this;
	}

	@Override
	public synchronized ScanTree setScanNumber(final int scanNumber) {
		this.scanNumber = scanNumber;
		return this;
	}

	@Override
	public String toString() {

		String rt = "";
		if (getRetentionTimes() != null) {
			rt = ",RT=" + String.format("%4.4f", getRetentionTimes()[0]);
		}
		return "scan=" + getScanNumber() + rt + ", " + getIons().size() + ", " + getIons().stream()
				.filter(i -> (i instanceof IonAnnotatable && !((IonAnnotatable) i).getAnnotation().isEmpty())).count();
	}

}

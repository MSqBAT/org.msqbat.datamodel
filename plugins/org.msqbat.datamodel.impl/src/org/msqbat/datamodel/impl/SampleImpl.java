
package org.msqbat.datamodel.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.msqbat.datamodel.api.ComparatorMzIntensityScanNumber;
import org.msqbat.datamodel.api.annotation.AnnotationSerializable;
import org.msqbat.datamodel.api.ion.IonMSqBAT;
import org.msqbat.datamodel.api.sample.SampleIons;
import org.msqbat.datamodel.api.sample.SampleMSqBAT;
import org.msqbat.datamodel.api.scan.ScanIons;
import org.msqbat.datamodel.api.scan.ScanMSqBAT;
import org.msqbat.datamodel.api.scan.ScanRaw;
import org.msqbat.datamodel.impl.scan.ComparatorScanNumberMzIntensity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Range;

import net.sf.kerner.utils.collections.ClonerCollection;
import net.sf.kerner.utils.collections.UtilCollection;

public class SampleImpl implements SampleIons {

	private final static Logger log = LoggerFactory.getLogger(SampleImpl.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -8319393607974017676L;

	private final AnnotatableElementProto annoDelegate = new AnnotatableElementProto();

	private final List<File> files = new LinkedList<>();

	private transient NavigableSet<IonMSqBAT> ionsTreeMz;

	private transient NavigableSet<IonMSqBAT> ionsTreeScanNumber;

	private String name;

	private transient Range<Double> rangeMz;

	private transient Range<Double> rangeRt;

	private final Map<Integer, ScanMSqBAT> scans = new HashMap<>();

	public SampleImpl() {
	}

	/**
	 * Creates a deep copy from template.
	 *
	 */
	public SampleImpl(final SampleMSqBAT template) {
		setName(template.getName());
		addFiles(template.getFiles());
		if (template instanceof SampleIons) {
			addScans(ClonerCollection.clone(((SampleIons) template).getScans()));
		}
	}

	@Override
	public synchronized SampleImpl addFiles(final Collection<? extends File> files) {

		this.files.addAll(files);
		return this;

	}

	@Override
	public synchronized SampleImpl addFiles(final File... files) {

		return addFiles(Arrays.asList(files));
	}

	@Override
	public synchronized SampleImpl addIons(final Collection<? extends IonMSqBAT> ions) {
		ionsTreeMz = null;
		ionsTreeScanNumber = null;
		for (final IonMSqBAT ion : ions) {
			ScanMSqBAT scan = scans.get(ion.getScanNumber());
			if (scan == null) {
				scan = new ScanTree();
				scan.setScanNumber(ion.getScanNumber());
				addScan(scan);
			}
			if (scan instanceof ScanIons) {
				((ScanIons) scan).addIons(ion);
			}
		}
		return this;
	}

	@Override
	public SampleImpl addIons(final IonMSqBAT... ions) {
		return addIons(Arrays.asList(ions));
	}

	public synchronized SampleImpl addScan(final ScanMSqBAT scan) {
		ionsTreeMz = null;
		ionsTreeScanNumber = null;
		scans.put(scan.getScanNumber(), scan);
		scan.setSample(this);
		return this;

	}

	@Override
	public synchronized SampleImpl addScans(final Collection<? extends ScanMSqBAT> scans) {

		for (final ScanMSqBAT s : scans) {
			addScan(s);
			s.setSample(this);
		}
		return this;

	}

	@Override
	public synchronized SampleImpl addScans(final ScanMSqBAT... scans) {

		return addScans(Arrays.asList(scans));
	}

	@Override
	public SampleImpl clear() {
		clearIons();
		return this;
	}

	public synchronized SampleImpl clearIons() {
		ionsTreeMz = null;
		ionsTreeScanNumber = null;

		getScans().stream().forEach(t -> {
			if (t instanceof ScanIons) {
				((ScanIons) t).clear();
			}

		});
		return this;
	}

	@Override
	public synchronized SampleImpl clone() {

		return new SampleImpl(this);
	}

	@Override
	public synchronized SampleImpl cloneWithoutIons() {
		final SampleImpl result = new SampleImpl();
		result.setName(getName());
		result.addFiles(getFiles());
		return result;

	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SampleImpl)) {
			return false;
		}
		final SampleImpl other = (SampleImpl) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (scans == null) {
			if (other.scans != null) {
				return false;
			}
		} else if (!scans.equals(other.scans)) {
			return false;
		}
		return true;
	}

	@Override
	public Collection<AnnotationSerializable> getAnnotation() {
		return annoDelegate.getAnnotation();
	}

	@Override
	public synchronized List<File> getFiles() {

		return Collections.unmodifiableList(files);
	}

	@Override
	public synchronized Collection<IonMSqBAT> getIons() {
		final List<Collection<? extends IonMSqBAT>> result = getScans().stream().map(t -> {
			if (t instanceof ScanIons) {
				return ((ScanIons) t).getIons();
			} else {
				return new ArrayList<IonMSqBAT>();
			}
		}).collect(Collectors.toList());
		return UtilCollection.append(result);
	}

	@Override
	public synchronized NavigableSet<IonMSqBAT> getIonsTreeMz() {
		if (ionsTreeMz == null) {
			ionsTreeMz = new TreeSet<>(new ComparatorMzIntensityScanNumber<>());
			ionsTreeMz.addAll(getIons());
		}
		return ionsTreeMz;
	}

	@Override
	public NavigableSet<IonMSqBAT> getIonsTreeScanNumber() {
		if (ionsTreeScanNumber == null) {
			ionsTreeScanNumber = new TreeSet<>(new ComparatorScanNumberMzIntensity<>());
			ionsTreeScanNumber.addAll(getIons());
		}
		return ionsTreeScanNumber;
	}

	@Override
	public synchronized String getName() {

		return name;

	}

	@Override
	public synchronized Range<Double> getRangeMz() {

		if (rangeMz == null) {
			rangeMz = Range.singleton(0.0);
			for (final ScanMSqBAT s : getScans()) {
				if (s instanceof ScanRaw) {
					final Range<Double> r = ((ScanRaw) s).getRangeMz();
					rangeMz = rangeMz.span(r);
				}
			}
		}
		return rangeMz;
	}

	@Override
	public synchronized Range<Double> getRangeRetentionTime() {

		if (rangeRt == null) {
			for (final ScanMSqBAT s : getScans()) {
				if (s instanceof ScanRaw) {
					final Range<Double> r = Range.singleton(((ScanRaw) s).getRetentionTimes()[0]);
					rangeRt = rangeRt.span(r);
				}
			}
		}
		return rangeRt;
	}

	@Override
	public synchronized Collection<ScanMSqBAT> getScans() {

		return Collections.unmodifiableCollection(scans.values());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((scans == null) ? 0 : scans.hashCode());
		return result;
	}

	public void setAnnotation(final AnnotationSerializable annotation) {
		annoDelegate.setAnnotation(annotation);
	}

	@Override
	public void setAnnotation(final Collection<AnnotationSerializable> annotation) {
		annoDelegate.setAnnotation(annotation);
	}

	@Override
	public synchronized SampleImpl setIons(final Collection<? extends IonMSqBAT> ions) {
		clearIons();
		ions.stream().forEach(ion -> {

			ScanMSqBAT scan = scans.get(ion.getScanNumber());
			if (scan == null) {
				scan = new ScanTree();
				scan.setScanNumber(ion.getScanNumber());
				scan.setRetentionTimes(ion.getRetentionTimes());
				addScan(scan);
			}
			if (scan instanceof ScanIons) {
				((ScanIons) scan).addIons(ion);
			}

		});
		return this;
	}

	@Override
	public synchronized SampleMSqBAT setName(final String name) {

		this.name = name;
		return this;

	}

	@Override
	public String toString() {

		return getName();
	}
}

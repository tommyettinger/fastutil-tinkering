/*
	* Copyright (C) 2002-2017 Sebastiano Vigna
	*
	* Licensed under the Apache License, Version 2.0 (the "License");
	* you may not use this file except in compliance with the License.
	* You may obtain a copy of the License at
	*
	*     http://www.apache.org/licenses/LICENSE-2.0
	*
	* Unless required by applicable law or agreed to in writing, software
	* distributed under the License is distributed on an "AS IS" BASIS,
	* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	* See the License for the specific language governing permissions and
	* limitations under the License.
	*/
package it.unimi.dsi.fastutil.doubles;
import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
import it.unimi.dsi.fastutil.bytes.ByteIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map;
/** An abstract class providing basic methods for sorted maps implementing a type-specific interface. */
public abstract class AbstractDouble2ByteSortedMap extends AbstractDouble2ByteMap implements Double2ByteSortedMap {
	private static final long serialVersionUID = -1773560792952436569L;
	protected AbstractDouble2ByteSortedMap() {}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Double2ByteSortedMap headMap(final Double to) {
	 return headMap(((to).doubleValue()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Double2ByteSortedMap tailMap(final Double from) {
	 return tailMap(((from).doubleValue()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Double2ByteSortedMap subMap(final Double from, final Double to) {
	 return subMap(((from).doubleValue()), ((to).doubleValue()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Double firstKey() {
	 return (Double.valueOf(firstDoubleKey()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Double lastKey() {
	 return (Double.valueOf(lastDoubleKey()));
	}
	/** {@inheritDoc}
	 *
	 * <P>The view is backed by the sorted set returned by {@link #entrySet()}. Note that
	 * <em>no attempt is made at caching the result of this method</em>, as this would
	 * require adding some attributes that lightweight implementations would
	 * not need. Subclasses may easily override this policy by calling
	 * this method and caching the result, but implementors are encouraged to
	 * write more efficient ad-hoc implementations.
	 *
	 * @return a sorted set view of the keys of this map; it may be safely cast to a type-specific interface.
	 */
	@Override
	public DoubleSortedSet keySet() {
	 return new KeySet();
	}
	/** A wrapper exhibiting the keys of a map. */
	protected class KeySet extends AbstractDoubleSortedSet {
	 @Override
	 public boolean contains(final double k) { return containsKey(k); }
	 @Override
	 public int size() { return AbstractDouble2ByteSortedMap.this.size(); }
	 @Override
	 public void clear() { AbstractDouble2ByteSortedMap.this.clear(); }
	 @Override
	 public DoubleComparator comparator() { return AbstractDouble2ByteSortedMap.this.comparator(); }
	 @Override
	 public double firstDouble() { return firstDoubleKey(); }
	 @Override
	 public double lastDouble() { return lastDoubleKey(); }
	 @Override
	 public DoubleSortedSet headSet(final double to) { return headMap(to).keySet(); }
	 @Override
	 public DoubleSortedSet tailSet(final double from) { return tailMap(from).keySet(); }
	 @Override
	 public DoubleSortedSet subSet(final double from, final double to) { return subMap(from, to).keySet(); }
	 @Override
	 public DoubleBidirectionalIterator iterator(final double from) { return new KeySetIterator (double2ByteEntrySet().iterator(new BasicEntry (from, ((byte)0)))); }
	 @Override
	 public DoubleBidirectionalIterator iterator() { return new KeySetIterator (double2ByteEntrySet().iterator()); }
	}
	/** A wrapper exhibiting a map iterator as an iterator on keys.
	 *
	 * <P>To provide an iterator on keys, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
	protected static class KeySetIterator extends AbstractDoubleBidirectionalIterator {
	 protected final ObjectBidirectionalIterator<Double2ByteMap.Entry > i;
	 public KeySetIterator(ObjectBidirectionalIterator<Double2ByteMap.Entry > i) {
	  this.i = i;
	 }
	 @Override
	 public double nextDouble() { return i.next().getDoubleKey(); };
	 @Override
	 public double previousDouble() { return i.previous().getDoubleKey(); };
	 @Override
	 public boolean hasNext() { return i.hasNext(); }
	 @Override
	 public boolean hasPrevious() { return i.hasPrevious(); }
	}
	/** {@inheritDoc}
	 *
	 * <P>The view is backed by the sorted set returned by {@link #entrySet()}. Note that
	 * <em>no attempt is made at caching the result of this method</em>, as this would
	 * require adding some attributes that lightweight implementations would
	 * not need. Subclasses may easily override this policy by calling
	 * this method and caching the result, but implementors are encouraged to
	 * write more efficient ad-hoc implementations.
	 *
	 * @return a type-specific collection view of the values contained in this map.
	 */
	@Override
	public ByteCollection values() {
	 return new ValuesCollection();
	}
	/** A wrapper exhibiting the values of a map. */
	protected class ValuesCollection extends AbstractByteCollection {
	 @Override
	 public ByteIterator iterator() { return new ValuesIterator (double2ByteEntrySet().iterator()); }
	 @Override
	 public boolean contains(final byte k) { return containsValue(k); }
	 @Override
	 public int size() { return AbstractDouble2ByteSortedMap.this.size(); }
	 @Override
	 public void clear() { AbstractDouble2ByteSortedMap.this.clear(); }
	}
	/** A wrapper exhibiting a map iterator as an iterator on values.
	 *
	 * <P>To provide an iterator on values, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
	protected static class ValuesIterator extends AbstractByteIterator {
	 protected final ObjectBidirectionalIterator<Double2ByteMap.Entry > i;
	 public ValuesIterator(ObjectBidirectionalIterator<Double2ByteMap.Entry > i) {
	  this.i = i;
	 }
	 @Override
	 public byte nextByte() { return i.next().getByteValue(); };
	 @Override
	 public boolean hasNext() { return i.hasNext(); }
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ObjectSortedSet<Map.Entry<Double, Byte>> entrySet() {
	 return (ObjectSortedSet)double2ByteEntrySet();
	}
}

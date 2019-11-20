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
package it.unimi.dsi.fastutil.bytes;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map;
/** An abstract class providing basic methods for sorted maps implementing a type-specific interface. */
public abstract class AbstractByte2ReferenceSortedMap <V> extends AbstractByte2ReferenceMap <V> implements Byte2ReferenceSortedMap <V> {
	private static final long serialVersionUID = -1773560792952436569L;
	protected AbstractByte2ReferenceSortedMap() {}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Byte2ReferenceSortedMap <V> headMap(final Byte to) {
	 return headMap(((to).byteValue()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Byte2ReferenceSortedMap <V> tailMap(final Byte from) {
	 return tailMap(((from).byteValue()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Byte2ReferenceSortedMap <V> subMap(final Byte from, final Byte to) {
	 return subMap(((from).byteValue()), ((to).byteValue()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Byte firstKey() {
	 return (Byte.valueOf(firstByteKey()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Byte lastKey() {
	 return (Byte.valueOf(lastByteKey()));
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
	public ByteSortedSet keySet() {
	 return new KeySet();
	}
	/** A wrapper exhibiting the keys of a map. */
	protected class KeySet extends AbstractByteSortedSet {
	 @Override
	 public boolean contains(final byte k) { return containsKey(k); }
	 @Override
	 public int size() { return AbstractByte2ReferenceSortedMap.this.size(); }
	 @Override
	 public void clear() { AbstractByte2ReferenceSortedMap.this.clear(); }
	 @Override
	 public ByteComparator comparator() { return AbstractByte2ReferenceSortedMap.this.comparator(); }
	 @Override
	 public byte firstByte() { return firstByteKey(); }
	 @Override
	 public byte lastByte() { return lastByteKey(); }
	 @Override
	 public ByteSortedSet headSet(final byte to) { return headMap(to).keySet(); }
	 @Override
	 public ByteSortedSet tailSet(final byte from) { return tailMap(from).keySet(); }
	 @Override
	 public ByteSortedSet subSet(final byte from, final byte to) { return subMap(from, to).keySet(); }
	 @Override
	 public ByteBidirectionalIterator iterator(final byte from) { return new KeySetIterator <V>(byte2ReferenceEntrySet().iterator(new BasicEntry <V>(from, (null)))); }
	 @Override
	 public ByteBidirectionalIterator iterator() { return new KeySetIterator <V>(byte2ReferenceEntrySet().iterator()); }
	}
	/** A wrapper exhibiting a map iterator as an iterator on keys.
	 *
	 * <P>To provide an iterator on keys, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
	protected static class KeySetIterator <V> extends AbstractByteBidirectionalIterator {
	 protected final ObjectBidirectionalIterator<Byte2ReferenceMap.Entry <V> > i;
	 public KeySetIterator(ObjectBidirectionalIterator<Byte2ReferenceMap.Entry <V> > i) {
	  this.i = i;
	 }
	 @Override
	 public byte nextByte() { return i.next().getByteKey(); };
	 @Override
	 public byte previousByte() { return i.previous().getByteKey(); };
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
	public ReferenceCollection <V> values() {
	 return new ValuesCollection();
	}
	/** A wrapper exhibiting the values of a map. */
	protected class ValuesCollection extends AbstractReferenceCollection <V> {
	 @Override
	 public ObjectIterator <V> iterator() { return new ValuesIterator <V>(byte2ReferenceEntrySet().iterator()); }
	 @Override
	 public boolean contains(final Object k) { return containsValue(k); }
	 @Override
	 public int size() { return AbstractByte2ReferenceSortedMap.this.size(); }
	 @Override
	 public void clear() { AbstractByte2ReferenceSortedMap.this.clear(); }
	}
	/** A wrapper exhibiting a map iterator as an iterator on values.
	 *
	 * <P>To provide an iterator on values, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
	protected static class ValuesIterator <V> extends AbstractObjectIterator <V> {
	 protected final ObjectBidirectionalIterator<Byte2ReferenceMap.Entry <V> > i;
	 public ValuesIterator(ObjectBidirectionalIterator<Byte2ReferenceMap.Entry <V> > i) {
	  this.i = i;
	 }
	 @Override
	 public V next() { return i.next().getValue(); };
	 @Override
	 public boolean hasNext() { return i.hasNext(); }
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ObjectSortedSet<Map.Entry<Byte, V>> entrySet() {
	 return (ObjectSortedSet)byte2ReferenceEntrySet();
	}
}

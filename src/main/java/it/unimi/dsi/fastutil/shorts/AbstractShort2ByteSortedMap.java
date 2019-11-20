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
package it.unimi.dsi.fastutil.shorts;
import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
import it.unimi.dsi.fastutil.bytes.ByteIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map;
/** An abstract class providing basic methods for sorted maps implementing a type-specific interface. */
public abstract class AbstractShort2ByteSortedMap extends AbstractShort2ByteMap implements Short2ByteSortedMap {
	private static final long serialVersionUID = -1773560792952436569L;
	protected AbstractShort2ByteSortedMap() {}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Short2ByteSortedMap headMap(final Short to) {
	 return headMap(((to).shortValue()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Short2ByteSortedMap tailMap(final Short from) {
	 return tailMap(((from).shortValue()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Short2ByteSortedMap subMap(final Short from, final Short to) {
	 return subMap(((from).shortValue()), ((to).shortValue()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Short firstKey() {
	 return (Short.valueOf(firstShortKey()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Short lastKey() {
	 return (Short.valueOf(lastShortKey()));
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
	public ShortSortedSet keySet() {
	 return new KeySet();
	}
	/** A wrapper exhibiting the keys of a map. */
	protected class KeySet extends AbstractShortSortedSet {
	 @Override
	 public boolean contains(final short k) { return containsKey(k); }
	 @Override
	 public int size() { return AbstractShort2ByteSortedMap.this.size(); }
	 @Override
	 public void clear() { AbstractShort2ByteSortedMap.this.clear(); }
	 @Override
	 public ShortComparator comparator() { return AbstractShort2ByteSortedMap.this.comparator(); }
	 @Override
	 public short firstShort() { return firstShortKey(); }
	 @Override
	 public short lastShort() { return lastShortKey(); }
	 @Override
	 public ShortSortedSet headSet(final short to) { return headMap(to).keySet(); }
	 @Override
	 public ShortSortedSet tailSet(final short from) { return tailMap(from).keySet(); }
	 @Override
	 public ShortSortedSet subSet(final short from, final short to) { return subMap(from, to).keySet(); }
	 @Override
	 public ShortBidirectionalIterator iterator(final short from) { return new KeySetIterator (short2ByteEntrySet().iterator(new BasicEntry (from, ((byte)0)))); }
	 @Override
	 public ShortBidirectionalIterator iterator() { return new KeySetIterator (short2ByteEntrySet().iterator()); }
	}
	/** A wrapper exhibiting a map iterator as an iterator on keys.
	 *
	 * <P>To provide an iterator on keys, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
	protected static class KeySetIterator extends AbstractShortBidirectionalIterator {
	 protected final ObjectBidirectionalIterator<Short2ByteMap.Entry > i;
	 public KeySetIterator(ObjectBidirectionalIterator<Short2ByteMap.Entry > i) {
	  this.i = i;
	 }
	 @Override
	 public short nextShort() { return i.next().getShortKey(); };
	 @Override
	 public short previousShort() { return i.previous().getShortKey(); };
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
	 public ByteIterator iterator() { return new ValuesIterator (short2ByteEntrySet().iterator()); }
	 @Override
	 public boolean contains(final byte k) { return containsValue(k); }
	 @Override
	 public int size() { return AbstractShort2ByteSortedMap.this.size(); }
	 @Override
	 public void clear() { AbstractShort2ByteSortedMap.this.clear(); }
	}
	/** A wrapper exhibiting a map iterator as an iterator on values.
	 *
	 * <P>To provide an iterator on values, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
	protected static class ValuesIterator extends AbstractByteIterator {
	 protected final ObjectBidirectionalIterator<Short2ByteMap.Entry > i;
	 public ValuesIterator(ObjectBidirectionalIterator<Short2ByteMap.Entry > i) {
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
	public ObjectSortedSet<Map.Entry<Short, Byte>> entrySet() {
	 return (ObjectSortedSet)short2ByteEntrySet();
	}
}
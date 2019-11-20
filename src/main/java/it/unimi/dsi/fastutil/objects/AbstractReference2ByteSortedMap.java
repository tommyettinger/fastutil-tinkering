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
package it.unimi.dsi.fastutil.objects;
import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
import it.unimi.dsi.fastutil.bytes.ByteIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map;
import java.util.Comparator;
/** An abstract class providing basic methods for sorted maps implementing a type-specific interface. */
public abstract class AbstractReference2ByteSortedMap <K> extends AbstractReference2ByteMap <K> implements Reference2ByteSortedMap <K> {
	private static final long serialVersionUID = -1773560792952436569L;
	protected AbstractReference2ByteSortedMap() {}
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
	public ReferenceSortedSet <K> keySet() {
	 return new KeySet();
	}
	/** A wrapper exhibiting the keys of a map. */
	protected class KeySet extends AbstractReferenceSortedSet <K> {
	 @Override
	 public boolean contains(final Object k) { return containsKey(k); }
	 @Override
	 public int size() { return AbstractReference2ByteSortedMap.this.size(); }
	 @Override
	 public void clear() { AbstractReference2ByteSortedMap.this.clear(); }
	 @Override
	 public Comparator <? super K> comparator() { return AbstractReference2ByteSortedMap.this.comparator(); }
	 @Override
	 public K first() { return firstKey(); }
	 @Override
	 public K last() { return lastKey(); }
	 @Override
	 public ReferenceSortedSet <K> headSet(final K to) { return headMap(to).keySet(); }
	 @Override
	 public ReferenceSortedSet <K> tailSet(final K from) { return tailMap(from).keySet(); }
	 @Override
	 public ReferenceSortedSet <K> subSet(final K from, final K to) { return subMap(from, to).keySet(); }
	 @Override
	 public ObjectBidirectionalIterator <K> iterator(final K from) { return new KeySetIterator <K>(reference2ByteEntrySet().iterator(new BasicEntry <K>(from, ((byte)0)))); }
	 @Override
	 public ObjectBidirectionalIterator <K> iterator() { return new KeySetIterator <K>(reference2ByteEntrySet().iterator()); }
	}
	/** A wrapper exhibiting a map iterator as an iterator on keys.
	 *
	 * <P>To provide an iterator on keys, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
	protected static class KeySetIterator <K> extends AbstractObjectBidirectionalIterator <K> {
	 protected final ObjectBidirectionalIterator<Reference2ByteMap.Entry <K> > i;
	 public KeySetIterator(ObjectBidirectionalIterator<Reference2ByteMap.Entry <K> > i) {
	  this.i = i;
	 }
	 @Override
	 public K next() { return i.next().getKey(); };
	 @Override
	 public K previous() { return i.previous().getKey(); };
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
	 public ByteIterator iterator() { return new ValuesIterator <K>(reference2ByteEntrySet().iterator()); }
	 @Override
	 public boolean contains(final byte k) { return containsValue(k); }
	 @Override
	 public int size() { return AbstractReference2ByteSortedMap.this.size(); }
	 @Override
	 public void clear() { AbstractReference2ByteSortedMap.this.clear(); }
	}
	/** A wrapper exhibiting a map iterator as an iterator on values.
	 *
	 * <P>To provide an iterator on values, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
	protected static class ValuesIterator <K> extends AbstractByteIterator {
	 protected final ObjectBidirectionalIterator<Reference2ByteMap.Entry <K> > i;
	 public ValuesIterator(ObjectBidirectionalIterator<Reference2ByteMap.Entry <K> > i) {
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
	public ObjectSortedSet<Map.Entry<K, Byte>> entrySet() {
	 return (ObjectSortedSet)reference2ByteEntrySet();
	}
}

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
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map;
import java.util.Comparator;
/** An abstract class providing basic methods for sorted maps implementing a type-specific interface. */
public abstract class AbstractObject2ReferenceSortedMap <K,V> extends AbstractObject2ReferenceMap <K,V> implements Object2ReferenceSortedMap <K,V> {
	private static final long serialVersionUID = -1773560792952436569L;
	protected AbstractObject2ReferenceSortedMap() {}
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
	public ObjectSortedSet <K> keySet() {
	 return new KeySet();
	}
	/** A wrapper exhibiting the keys of a map. */
	protected class KeySet extends AbstractObjectSortedSet <K> {
	 @Override
	 public boolean contains(final Object k) { return containsKey(k); }
	 @Override
	 public int size() { return AbstractObject2ReferenceSortedMap.this.size(); }
	 @Override
	 public void clear() { AbstractObject2ReferenceSortedMap.this.clear(); }
	 @Override
	 public Comparator <? super K> comparator() { return AbstractObject2ReferenceSortedMap.this.comparator(); }
	 @Override
	 public K first() { return firstKey(); }
	 @Override
	 public K last() { return lastKey(); }
	 @Override
	 public ObjectSortedSet <K> headSet(final K to) { return headMap(to).keySet(); }
	 @Override
	 public ObjectSortedSet <K> tailSet(final K from) { return tailMap(from).keySet(); }
	 @Override
	 public ObjectSortedSet <K> subSet(final K from, final K to) { return subMap(from, to).keySet(); }
	 @Override
	 public ObjectBidirectionalIterator <K> iterator(final K from) { return new KeySetIterator <K,V>(object2ReferenceEntrySet().iterator(new BasicEntry <K,V>(from, (null)))); }
	 @Override
	 public ObjectBidirectionalIterator <K> iterator() { return new KeySetIterator <K,V>(object2ReferenceEntrySet().iterator()); }
	}
	/** A wrapper exhibiting a map iterator as an iterator on keys.
	 *
	 * <P>To provide an iterator on keys, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
	protected static class KeySetIterator <K,V> extends AbstractObjectBidirectionalIterator <K> {
	 protected final ObjectBidirectionalIterator<Object2ReferenceMap.Entry <K,V> > i;
	 public KeySetIterator(ObjectBidirectionalIterator<Object2ReferenceMap.Entry <K,V> > i) {
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
	public ReferenceCollection <V> values() {
	 return new ValuesCollection();
	}
	/** A wrapper exhibiting the values of a map. */
	protected class ValuesCollection extends AbstractReferenceCollection <V> {
	 @Override
	 public ObjectIterator <V> iterator() { return new ValuesIterator <K,V>(object2ReferenceEntrySet().iterator()); }
	 @Override
	 public boolean contains(final Object k) { return containsValue(k); }
	 @Override
	 public int size() { return AbstractObject2ReferenceSortedMap.this.size(); }
	 @Override
	 public void clear() { AbstractObject2ReferenceSortedMap.this.clear(); }
	}
	/** A wrapper exhibiting a map iterator as an iterator on values.
	 *
	 * <P>To provide an iterator on values, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
	protected static class ValuesIterator <K,V> extends AbstractObjectIterator <V> {
	 protected final ObjectBidirectionalIterator<Object2ReferenceMap.Entry <K,V> > i;
	 public ValuesIterator(ObjectBidirectionalIterator<Object2ReferenceMap.Entry <K,V> > i) {
	  this.i = i;
	 }
	 @Override
	 public V next() { return i.next().getValue(); };
	 @Override
	 public boolean hasNext() { return i.hasNext(); }
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method. */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ObjectSortedSet<Map.Entry<K, V>> entrySet() {
	 return (ObjectSortedSet)object2ReferenceEntrySet();
	}
}

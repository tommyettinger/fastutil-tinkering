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
package it.unimi.dsi.fastutil.floats;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
import it.unimi.dsi.fastutil.booleans.BooleanIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map;
/** An abstract class providing basic methods for sorted maps implementing a type-specific interface. */
public abstract class AbstractFloat2BooleanSortedMap extends AbstractFloat2BooleanMap implements Float2BooleanSortedMap {
	private static final long serialVersionUID = -1773560792952436569L;
	protected AbstractFloat2BooleanSortedMap() {}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Float2BooleanSortedMap headMap(final Float to) {
	 return headMap(((to).floatValue()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Float2BooleanSortedMap tailMap(final Float from) {
	 return tailMap(((from).floatValue()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Float2BooleanSortedMap subMap(final Float from, final Float to) {
	 return subMap(((from).floatValue()), ((to).floatValue()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Float firstKey() {
	 return (Float.valueOf(firstFloatKey()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Float lastKey() {
	 return (Float.valueOf(lastFloatKey()));
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
	public FloatSortedSet keySet() {
	 return new KeySet();
	}
	/** A wrapper exhibiting the keys of a map. */
	protected class KeySet extends AbstractFloatSortedSet {
	 @Override
	 public boolean contains(final float k) { return containsKey(k); }
	 @Override
	 public int size() { return AbstractFloat2BooleanSortedMap.this.size(); }
	 @Override
	 public void clear() { AbstractFloat2BooleanSortedMap.this.clear(); }
	 @Override
	 public FloatComparator comparator() { return AbstractFloat2BooleanSortedMap.this.comparator(); }
	 @Override
	 public float firstFloat() { return firstFloatKey(); }
	 @Override
	 public float lastFloat() { return lastFloatKey(); }
	 @Override
	 public FloatSortedSet headSet(final float to) { return headMap(to).keySet(); }
	 @Override
	 public FloatSortedSet tailSet(final float from) { return tailMap(from).keySet(); }
	 @Override
	 public FloatSortedSet subSet(final float from, final float to) { return subMap(from, to).keySet(); }
	 @Override
	 public FloatBidirectionalIterator iterator(final float from) { return new KeySetIterator (float2BooleanEntrySet().iterator(new BasicEntry (from, (false)))); }
	 @Override
	 public FloatBidirectionalIterator iterator() { return new KeySetIterator (float2BooleanEntrySet().iterator()); }
	}
	/** A wrapper exhibiting a map iterator as an iterator on keys.
	 *
	 * <P>To provide an iterator on keys, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
	protected static class KeySetIterator extends AbstractFloatBidirectionalIterator {
	 protected final ObjectBidirectionalIterator<Float2BooleanMap.Entry > i;
	 public KeySetIterator(ObjectBidirectionalIterator<Float2BooleanMap.Entry > i) {
	  this.i = i;
	 }
	 @Override
	 public float nextFloat() { return i.next().getFloatKey(); };
	 @Override
	 public float previousFloat() { return i.previous().getFloatKey(); };
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
	public BooleanCollection values() {
	 return new ValuesCollection();
	}
	/** A wrapper exhibiting the values of a map. */
	protected class ValuesCollection extends AbstractBooleanCollection {
	 @Override
	 public BooleanIterator iterator() { return new ValuesIterator (float2BooleanEntrySet().iterator()); }
	 @Override
	 public boolean contains(final boolean k) { return containsValue(k); }
	 @Override
	 public int size() { return AbstractFloat2BooleanSortedMap.this.size(); }
	 @Override
	 public void clear() { AbstractFloat2BooleanSortedMap.this.clear(); }
	}
	/** A wrapper exhibiting a map iterator as an iterator on values.
	 *
	 * <P>To provide an iterator on values, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
	protected static class ValuesIterator extends AbstractBooleanIterator {
	 protected final ObjectBidirectionalIterator<Float2BooleanMap.Entry > i;
	 public ValuesIterator(ObjectBidirectionalIterator<Float2BooleanMap.Entry > i) {
	  this.i = i;
	 }
	 @Override
	 public boolean nextBoolean() { return i.next().getBooleanValue(); };
	 @Override
	 public boolean hasNext() { return i.hasNext(); }
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ObjectSortedSet<Map.Entry<Float, Boolean>> entrySet() {
	 return (ObjectSortedSet)float2BooleanEntrySet();
	}
}

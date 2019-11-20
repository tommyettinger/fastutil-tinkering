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
package it.unimi.dsi.fastutil.chars;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
import it.unimi.dsi.fastutil.booleans.BooleanIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map;
/** An abstract class providing basic methods for sorted maps implementing a type-specific interface. */
public abstract class AbstractChar2BooleanSortedMap extends AbstractChar2BooleanMap implements Char2BooleanSortedMap {
	private static final long serialVersionUID = -1773560792952436569L;
	protected AbstractChar2BooleanSortedMap() {}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Char2BooleanSortedMap headMap(final Character to) {
	 return headMap(((to).charValue()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Char2BooleanSortedMap tailMap(final Character from) {
	 return tailMap(((from).charValue()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Char2BooleanSortedMap subMap(final Character from, final Character to) {
	 return subMap(((from).charValue()), ((to).charValue()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Character firstKey() {
	 return (Character.valueOf(firstCharKey()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Character lastKey() {
	 return (Character.valueOf(lastCharKey()));
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
	public CharSortedSet keySet() {
	 return new KeySet();
	}
	/** A wrapper exhibiting the keys of a map. */
	protected class KeySet extends AbstractCharSortedSet {
	 @Override
	 public boolean contains(final char k) { return containsKey(k); }
	 @Override
	 public int size() { return AbstractChar2BooleanSortedMap.this.size(); }
	 @Override
	 public void clear() { AbstractChar2BooleanSortedMap.this.clear(); }
	 @Override
	 public CharComparator comparator() { return AbstractChar2BooleanSortedMap.this.comparator(); }
	 @Override
	 public char firstChar() { return firstCharKey(); }
	 @Override
	 public char lastChar() { return lastCharKey(); }
	 @Override
	 public CharSortedSet headSet(final char to) { return headMap(to).keySet(); }
	 @Override
	 public CharSortedSet tailSet(final char from) { return tailMap(from).keySet(); }
	 @Override
	 public CharSortedSet subSet(final char from, final char to) { return subMap(from, to).keySet(); }
	 @Override
	 public CharBidirectionalIterator iterator(final char from) { return new KeySetIterator (char2BooleanEntrySet().iterator(new BasicEntry (from, (false)))); }
	 @Override
	 public CharBidirectionalIterator iterator() { return new KeySetIterator (char2BooleanEntrySet().iterator()); }
	}
	/** A wrapper exhibiting a map iterator as an iterator on keys.
	 *
	 * <P>To provide an iterator on keys, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
	protected static class KeySetIterator extends AbstractCharBidirectionalIterator {
	 protected final ObjectBidirectionalIterator<Char2BooleanMap.Entry > i;
	 public KeySetIterator(ObjectBidirectionalIterator<Char2BooleanMap.Entry > i) {
	  this.i = i;
	 }
	 @Override
	 public char nextChar() { return i.next().getCharKey(); };
	 @Override
	 public char previousChar() { return i.previous().getCharKey(); };
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
	 public BooleanIterator iterator() { return new ValuesIterator (char2BooleanEntrySet().iterator()); }
	 @Override
	 public boolean contains(final boolean k) { return containsValue(k); }
	 @Override
	 public int size() { return AbstractChar2BooleanSortedMap.this.size(); }
	 @Override
	 public void clear() { AbstractChar2BooleanSortedMap.this.clear(); }
	}
	/** A wrapper exhibiting a map iterator as an iterator on values.
	 *
	 * <P>To provide an iterator on values, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
	protected static class ValuesIterator extends AbstractBooleanIterator {
	 protected final ObjectBidirectionalIterator<Char2BooleanMap.Entry > i;
	 public ValuesIterator(ObjectBidirectionalIterator<Char2BooleanMap.Entry > i) {
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
	public ObjectSortedSet<Map.Entry<Character, Boolean>> entrySet() {
	 return (ObjectSortedSet)char2BooleanEntrySet();
	}
}

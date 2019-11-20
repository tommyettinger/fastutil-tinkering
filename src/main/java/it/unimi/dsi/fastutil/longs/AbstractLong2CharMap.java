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
package it.unimi.dsi.fastutil.longs;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
import it.unimi.dsi.fastutil.chars.CharIterator;
import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Iterator;
import java.util.Map;
/** An abstract class providing basic methods for maps implementing a type-specific interface.
	*
	* <P>Optional operations just throw an {@link
	* UnsupportedOperationException}. Generic versions of accessors delegate to
	* the corresponding type-specific counterparts following the interface rules
	* (they take care of returning {@code null} on a missing key).
	*
	* <P>As a further help, this class provides a {@link BasicEntry BasicEntry} inner class
	* that implements a type-specific version of {@link java.util.Map.Entry}; it
	* is particularly useful for those classes that do not implement their own
	* entries (e.g., most immutable maps).
	*/
public abstract class AbstractLong2CharMap extends AbstractLong2CharFunction implements Long2CharMap , java.io.Serializable {
	private static final long serialVersionUID = -4940583368468432370L;
	protected AbstractLong2CharMap() {}
	@Override
	public boolean containsValue(char v) {
	 return values().contains(v);
	}
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public boolean containsValue(Object ov) {
	 if (ov == null) return false;
	 return containsValue(((((Character)(ov)).charValue())));
	}
	@Override
	public boolean containsKey(long k) {
	 ObjectIterator<Long2CharMap.Entry > i = long2CharEntrySet().iterator();
	 while(i.hasNext())
	  if (i.next().getLongKey() == k)
	   return true;
	 return false;
	}
	/** Puts all pairs in the given map.
	 * If the map implements the interface of this map,
	 * it uses the faster iterators.
	 *
	 * @param m a map.
	 */
	 @SuppressWarnings("deprecation")
	public void putAll(Map<? extends Long,? extends Character> m) {
	 int n = m.size();
	 final Iterator<? extends Map.Entry<? extends Long,? extends Character>> i = m.entrySet().iterator();
	 if (m instanceof Long2CharMap) {
	  Long2CharMap.Entry e;
	  while(n-- != 0) {
	   e = (Long2CharMap.Entry )i.next();
	   put(e.getLongKey(), e.getCharValue());
	  }
	 }
	 else {
	  Map.Entry<? extends Long,? extends Character> e;
	  while(n-- != 0) {
	   e = i.next();
	   put(e.getKey(), e.getValue());
	  }
	 }
	}
	public boolean isEmpty() {
	 return size() == 0;
	}
	/** This class provides a basic but complete type-specific entry class for all those maps implementations
	 * that do not have entries on their own (e.g., most immutable maps).
	 *
	 * <P>This class does not implement {@link java.util.Map.Entry#setValue(Object) setValue()}, as the modification
	 * would not be reflected in the base map.
	 */
	public static class BasicEntry implements Long2CharMap.Entry {
	 protected long key;
	 protected char value;
	 public BasicEntry(final Long key, final Character value) {
	  this.key = ((key).longValue());
	  this.value = ((value).charValue());
	 }
	 public BasicEntry(final long key, final char value) {
	  this.key = key;
	  this.value = value;
	 }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Long getKey() {
	  return (Long.valueOf(key));
	 }
	 @Override
	 public long getLongKey() {
	  return key;
	 }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Character getValue() {
	  return (Character.valueOf(value));
	 }
	 @Override
	 public char getCharValue() {
	  return value;
	 }
	 @Override
	 public char setValue(final char value) {
	  throw new UnsupportedOperationException();
	 }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Character setValue(final Character value) {
	  return Character.valueOf(setValue(value.charValue()));
	 }
	 @Override
	 public boolean equals(final Object o) {
	  if (!(o instanceof Map.Entry)) return false;
	  final Map.Entry<?,?> e = (Map.Entry<?,?>)o;
	  if (e.getKey() == null || ! (e.getKey() instanceof Long)) return false;
	  if (e.getValue() == null || ! (e.getValue() instanceof Character)) return false;
	  return ( (key) == (((((Long)(e.getKey())).longValue()))) ) && ( (value) == (((((Character)(e.getValue())).charValue()))) );
	 }
	 @Override
	 public int hashCode() {
	  return it.unimi.dsi.fastutil.HashCommon.long2int(key) ^ (value);
	 }
	 @Override
	 public String toString() {
	  return key + "->" + value;
	 }
	}
	/** Returns a type-specific-set view of the keys of this map.
	 *
	 * <P>The view is backed by the set returned by {@link #entrySet()}. Note that
	 * <em>no attempt is made at caching the result of this method</em>, as this would
	 * require adding some attributes that lightweight implementations would
	 * not need. Subclasses may easily override this policy by calling
	 * this method and caching the result, but implementors are encouraged to
	 * write more efficient ad-hoc implementations.
	 *
	 * @return a set view of the keys of this map; it may be safely cast to a type-specific interface.
	 */
	@Override
	public LongSet keySet() {
	 return new AbstractLongSet () {
	   public boolean contains(final long k) { return containsKey(k); }
	   public int size() { return AbstractLong2CharMap.this.size(); }
	   public void clear() { AbstractLong2CharMap.this.clear(); }
	   public LongIterator iterator() {
	    return new AbstractLongIterator () {
	      final ObjectIterator<Map.Entry<Long,Character>> i = entrySet().iterator();
	      @Override
	      public long nextLong() { return ((Long2CharMap.Entry )i.next()).getLongKey(); };
	      @Override
	      public boolean hasNext() { return i.hasNext(); }
	      @Override
	      public void remove() { i.remove(); }
	     };
	   }
	  };
	}
	/** Returns a type-specific-set view of the values of this map.
	 *
	 * <P>The view is backed by the set returned by {@link #entrySet()}. Note that
	 * <em>no attempt is made at caching the result of this method</em>, as this would
	 * require adding some attributes that lightweight implementations would
	 * not need. Subclasses may easily override this policy by calling
	 * this method and caching the result, but implementors are encouraged to
	 * write more efficient ad-hoc implementations.
	 *
	 * @return a set view of the values of this map; it may be safely cast to a type-specific interface.
	 */
	@Override
	public CharCollection values() {
	 return new AbstractCharCollection () {
	   public boolean contains(final char k) { return containsValue(k); }
	   public int size() { return AbstractLong2CharMap.this.size(); }
	   public void clear() { AbstractLong2CharMap.this.clear(); }
	   public CharIterator iterator() {
	    return new AbstractCharIterator () {
	      final ObjectIterator<Map.Entry<Long,Character>> i = entrySet().iterator();
	      /** {@inheritDoc}
							 * @deprecated Please use the corresponding type-specific method instead. */
	      @Deprecated
	      public char nextChar() { return ((Long2CharMap.Entry )i.next()).getCharValue(); };
	      public boolean hasNext() { return i.hasNext(); }
	     };
	   }
	  };
	}
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ObjectSet<Map.Entry<Long, Character>> entrySet() {
	 return (ObjectSet)long2CharEntrySet();
	}
	/** Returns a hash code for this map.
	 *
	 * The hash code of a map is computed by summing the hash codes of its entries.
	 *
	 * @return a hash code for this map.
	 */
	@Override
	public int hashCode() {
	 int h = 0, n = size();
	 final ObjectIterator<? extends Map.Entry<Long,Character>> i = entrySet().iterator();
	 while(n-- != 0) h += i.next().hashCode();
	 return h;
	}
	@Override
	public boolean equals(Object o) {
	 if (o == this) return true;
	 if (! (o instanceof Map)) return false;
	 Map<?,?> m = (Map<?,?>)o;
	 if (m.size() != size()) return false;
	 return entrySet().containsAll(m.entrySet());
	}
	@Override
	public String toString() {
	 final StringBuilder s = new StringBuilder();
	 final ObjectIterator<? extends Map.Entry<Long,Character>> i = entrySet().iterator();
	 int n = size();
	 Long2CharMap.Entry e;
	 boolean first = true;
	 s.append("{");
	 while(n-- != 0) {
	  if (first) first = false;
	  else s.append(", ");
	  e = (Long2CharMap.Entry )i.next();
	   s.append(String.valueOf(e.getLongKey()));
	  s.append("=>");
	   s.append(String.valueOf(e.getCharValue()));
	 }
	 s.append("}");
	 return s.toString();
	}
}

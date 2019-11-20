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
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
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
public abstract class AbstractChar2IntMap extends AbstractChar2IntFunction implements Char2IntMap , java.io.Serializable {
	private static final long serialVersionUID = -4940583368468432370L;
	protected AbstractChar2IntMap() {}
	@Override
	public boolean containsValue(int v) {
	 return values().contains(v);
	}
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public boolean containsValue(Object ov) {
	 if (ov == null) return false;
	 return containsValue(((((Integer)(ov)).intValue())));
	}
	@Override
	public boolean containsKey(char k) {
	 ObjectIterator<Char2IntMap.Entry > i = char2IntEntrySet().iterator();
	 while(i.hasNext())
	  if (i.next().getCharKey() == k)
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
	public void putAll(Map<? extends Character,? extends Integer> m) {
	 int n = m.size();
	 final Iterator<? extends Map.Entry<? extends Character,? extends Integer>> i = m.entrySet().iterator();
	 if (m instanceof Char2IntMap) {
	  Char2IntMap.Entry e;
	  while(n-- != 0) {
	   e = (Char2IntMap.Entry )i.next();
	   put(e.getCharKey(), e.getIntValue());
	  }
	 }
	 else {
	  Map.Entry<? extends Character,? extends Integer> e;
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
	public static class BasicEntry implements Char2IntMap.Entry {
	 protected char key;
	 protected int value;
	 public BasicEntry(final Character key, final Integer value) {
	  this.key = ((key).charValue());
	  this.value = ((value).intValue());
	 }
	 public BasicEntry(final char key, final int value) {
	  this.key = key;
	  this.value = value;
	 }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Character getKey() {
	  return (Character.valueOf(key));
	 }
	 @Override
	 public char getCharKey() {
	  return key;
	 }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Integer getValue() {
	  return (Integer.valueOf(value));
	 }
	 @Override
	 public int getIntValue() {
	  return value;
	 }
	 @Override
	 public int setValue(final int value) {
	  throw new UnsupportedOperationException();
	 }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Integer setValue(final Integer value) {
	  return Integer.valueOf(setValue(value.intValue()));
	 }
	 @Override
	 public boolean equals(final Object o) {
	  if (!(o instanceof Map.Entry)) return false;
	  final Map.Entry<?,?> e = (Map.Entry<?,?>)o;
	  if (e.getKey() == null || ! (e.getKey() instanceof Character)) return false;
	  if (e.getValue() == null || ! (e.getValue() instanceof Integer)) return false;
	  return ( (key) == (((((Character)(e.getKey())).charValue()))) ) && ( (value) == (((((Integer)(e.getValue())).intValue()))) );
	 }
	 @Override
	 public int hashCode() {
	  return (key) ^ (value);
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
	public CharSet keySet() {
	 return new AbstractCharSet () {
	   public boolean contains(final char k) { return containsKey(k); }
	   public int size() { return AbstractChar2IntMap.this.size(); }
	   public void clear() { AbstractChar2IntMap.this.clear(); }
	   public CharIterator iterator() {
	    return new AbstractCharIterator () {
	      final ObjectIterator<Map.Entry<Character,Integer>> i = entrySet().iterator();
	      @Override
	      public char nextChar() { return ((Char2IntMap.Entry )i.next()).getCharKey(); };
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
	public IntCollection values() {
	 return new AbstractIntCollection () {
	   public boolean contains(final int k) { return containsValue(k); }
	   public int size() { return AbstractChar2IntMap.this.size(); }
	   public void clear() { AbstractChar2IntMap.this.clear(); }
	   public IntIterator iterator() {
	    return new AbstractIntIterator () {
	      final ObjectIterator<Map.Entry<Character,Integer>> i = entrySet().iterator();
	      /** {@inheritDoc}
							 * @deprecated Please use the corresponding type-specific method instead. */
	      @Deprecated
	      public int nextInt() { return ((Char2IntMap.Entry )i.next()).getIntValue(); };
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
	public ObjectSet<Map.Entry<Character, Integer>> entrySet() {
	 return (ObjectSet)char2IntEntrySet();
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
	 final ObjectIterator<? extends Map.Entry<Character,Integer>> i = entrySet().iterator();
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
	 final ObjectIterator<? extends Map.Entry<Character,Integer>> i = entrySet().iterator();
	 int n = size();
	 Char2IntMap.Entry e;
	 boolean first = true;
	 s.append("{");
	 while(n-- != 0) {
	  if (first) first = false;
	  else s.append(", ");
	  e = (Char2IntMap.Entry )i.next();
	   s.append(String.valueOf(e.getCharKey()));
	  s.append("=>");
	   s.append(String.valueOf(e.getIntValue()));
	 }
	 s.append("}");
	 return s.toString();
	}
}
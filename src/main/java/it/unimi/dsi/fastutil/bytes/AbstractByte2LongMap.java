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
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
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
public abstract class AbstractByte2LongMap extends AbstractByte2LongFunction implements Byte2LongMap , java.io.Serializable {
	private static final long serialVersionUID = -4940583368468432370L;
	protected AbstractByte2LongMap() {}
	@Override
	public boolean containsValue(long v) {
	 return values().contains(v);
	}
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public boolean containsValue(Object ov) {
	 if (ov == null) return false;
	 return containsValue(((((Long)(ov)).longValue())));
	}
	@Override
	public boolean containsKey(byte k) {
	 ObjectIterator<Byte2LongMap.Entry > i = byte2LongEntrySet().iterator();
	 while(i.hasNext())
	  if (i.next().getByteKey() == k)
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
	public void putAll(Map<? extends Byte,? extends Long> m) {
	 int n = m.size();
	 final Iterator<? extends Map.Entry<? extends Byte,? extends Long>> i = m.entrySet().iterator();
	 if (m instanceof Byte2LongMap) {
	  Byte2LongMap.Entry e;
	  while(n-- != 0) {
	   e = (Byte2LongMap.Entry )i.next();
	   put(e.getByteKey(), e.getLongValue());
	  }
	 }
	 else {
	  Map.Entry<? extends Byte,? extends Long> e;
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
	public static class BasicEntry implements Byte2LongMap.Entry {
	 protected byte key;
	 protected long value;
	 public BasicEntry(final Byte key, final Long value) {
	  this.key = ((key).byteValue());
	  this.value = ((value).longValue());
	 }
	 public BasicEntry(final byte key, final long value) {
	  this.key = key;
	  this.value = value;
	 }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte getKey() {
	  return (Byte.valueOf(key));
	 }
	 @Override
	 public byte getByteKey() {
	  return key;
	 }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Long getValue() {
	  return (Long.valueOf(value));
	 }
	 @Override
	 public long getLongValue() {
	  return value;
	 }
	 @Override
	 public long setValue(final long value) {
	  throw new UnsupportedOperationException();
	 }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Long setValue(final Long value) {
	  return Long.valueOf(setValue(value.longValue()));
	 }
	 @Override
	 public boolean equals(final Object o) {
	  if (!(o instanceof Map.Entry)) return false;
	  final Map.Entry<?,?> e = (Map.Entry<?,?>)o;
	  if (e.getKey() == null || ! (e.getKey() instanceof Byte)) return false;
	  if (e.getValue() == null || ! (e.getValue() instanceof Long)) return false;
	  return ( (key) == (((((Byte)(e.getKey())).byteValue()))) ) && ( (value) == (((((Long)(e.getValue())).longValue()))) );
	 }
	 @Override
	 public int hashCode() {
	  return (key) ^ it.unimi.dsi.fastutil.HashCommon.long2int(value);
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
	public ByteSet keySet() {
	 return new AbstractByteSet () {
	   public boolean contains(final byte k) { return containsKey(k); }
	   public int size() { return AbstractByte2LongMap.this.size(); }
	   public void clear() { AbstractByte2LongMap.this.clear(); }
	   public ByteIterator iterator() {
	    return new AbstractByteIterator () {
	      final ObjectIterator<Map.Entry<Byte,Long>> i = entrySet().iterator();
	      @Override
	      public byte nextByte() { return ((Byte2LongMap.Entry )i.next()).getByteKey(); };
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
	public LongCollection values() {
	 return new AbstractLongCollection () {
	   public boolean contains(final long k) { return containsValue(k); }
	   public int size() { return AbstractByte2LongMap.this.size(); }
	   public void clear() { AbstractByte2LongMap.this.clear(); }
	   public LongIterator iterator() {
	    return new AbstractLongIterator () {
	      final ObjectIterator<Map.Entry<Byte,Long>> i = entrySet().iterator();
	      /** {@inheritDoc}
							 * @deprecated Please use the corresponding type-specific method instead. */
	      @Deprecated
	      public long nextLong() { return ((Byte2LongMap.Entry )i.next()).getLongValue(); };
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
	public ObjectSet<Map.Entry<Byte, Long>> entrySet() {
	 return (ObjectSet)byte2LongEntrySet();
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
	 final ObjectIterator<? extends Map.Entry<Byte,Long>> i = entrySet().iterator();
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
	 final ObjectIterator<? extends Map.Entry<Byte,Long>> i = entrySet().iterator();
	 int n = size();
	 Byte2LongMap.Entry e;
	 boolean first = true;
	 s.append("{");
	 while(n-- != 0) {
	  if (first) first = false;
	  else s.append(", ");
	  e = (Byte2LongMap.Entry )i.next();
	   s.append(String.valueOf(e.getByteKey()));
	  s.append("=>");
	   s.append(String.valueOf(e.getLongValue()));
	 }
	 s.append("}");
	 return s.toString();
	}
}

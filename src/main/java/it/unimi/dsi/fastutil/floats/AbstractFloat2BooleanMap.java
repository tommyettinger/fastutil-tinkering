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
import it.unimi.dsi.fastutil.booleans.BooleanIterator;
import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
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
public abstract class AbstractFloat2BooleanMap extends AbstractFloat2BooleanFunction implements Float2BooleanMap , java.io.Serializable {
	private static final long serialVersionUID = -4940583368468432370L;
	protected AbstractFloat2BooleanMap() {}
	@Override
	public boolean containsValue(boolean v) {
	 return values().contains(v);
	}
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public boolean containsValue(Object ov) {
	 if (ov == null) return false;
	 return containsValue(((((Boolean)(ov)).booleanValue())));
	}
	@Override
	public boolean containsKey(float k) {
	 ObjectIterator<Float2BooleanMap.Entry > i = float2BooleanEntrySet().iterator();
	 while(i.hasNext())
	  if (i.next().getFloatKey() == k)
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
	public void putAll(Map<? extends Float,? extends Boolean> m) {
	 int n = m.size();
	 final Iterator<? extends Map.Entry<? extends Float,? extends Boolean>> i = m.entrySet().iterator();
	 if (m instanceof Float2BooleanMap) {
	  Float2BooleanMap.Entry e;
	  while(n-- != 0) {
	   e = (Float2BooleanMap.Entry )i.next();
	   put(e.getFloatKey(), e.getBooleanValue());
	  }
	 }
	 else {
	  Map.Entry<? extends Float,? extends Boolean> e;
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
	public static class BasicEntry implements Float2BooleanMap.Entry {
	 protected float key;
	 protected boolean value;
	 public BasicEntry(final Float key, final Boolean value) {
	  this.key = ((key).floatValue());
	  this.value = ((value).booleanValue());
	 }
	 public BasicEntry(final float key, final boolean value) {
	  this.key = key;
	  this.value = value;
	 }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Float getKey() {
	  return (Float.valueOf(key));
	 }
	 @Override
	 public float getFloatKey() {
	  return key;
	 }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Boolean getValue() {
	  return (Boolean.valueOf(value));
	 }
	 @Override
	 public boolean getBooleanValue() {
	  return value;
	 }
	 @Override
	 public boolean setValue(final boolean value) {
	  throw new UnsupportedOperationException();
	 }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Boolean setValue(final Boolean value) {
	  return Boolean.valueOf(setValue(value.booleanValue()));
	 }
	 @Override
	 public boolean equals(final Object o) {
	  if (!(o instanceof Map.Entry)) return false;
	  final Map.Entry<?,?> e = (Map.Entry<?,?>)o;
	  if (e.getKey() == null || ! (e.getKey() instanceof Float)) return false;
	  if (e.getValue() == null || ! (e.getValue() instanceof Boolean)) return false;
	  return ( Float.floatToIntBits(key) == Float.floatToIntBits(((((Float)(e.getKey())).floatValue()))) ) && ( (value) == (((((Boolean)(e.getValue())).booleanValue()))) );
	 }
	 @Override
	 public int hashCode() {
	  return it.unimi.dsi.fastutil.HashCommon.float2int(key) ^ (value ? 1231 : 1237);
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
	public FloatSet keySet() {
	 return new AbstractFloatSet () {
	   public boolean contains(final float k) { return containsKey(k); }
	   public int size() { return AbstractFloat2BooleanMap.this.size(); }
	   public void clear() { AbstractFloat2BooleanMap.this.clear(); }
	   public FloatIterator iterator() {
	    return new AbstractFloatIterator () {
	      final ObjectIterator<Map.Entry<Float,Boolean>> i = entrySet().iterator();
	      @Override
	      public float nextFloat() { return ((Float2BooleanMap.Entry )i.next()).getFloatKey(); };
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
	public BooleanCollection values() {
	 return new AbstractBooleanCollection () {
	   public boolean contains(final boolean k) { return containsValue(k); }
	   public int size() { return AbstractFloat2BooleanMap.this.size(); }
	   public void clear() { AbstractFloat2BooleanMap.this.clear(); }
	   public BooleanIterator iterator() {
	    return new AbstractBooleanIterator () {
	      final ObjectIterator<Map.Entry<Float,Boolean>> i = entrySet().iterator();
	      /** {@inheritDoc}
							 * @deprecated Please use the corresponding type-specific method instead. */
	      @Deprecated
	      public boolean nextBoolean() { return ((Float2BooleanMap.Entry )i.next()).getBooleanValue(); };
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
	public ObjectSet<Map.Entry<Float, Boolean>> entrySet() {
	 return (ObjectSet)float2BooleanEntrySet();
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
	 final ObjectIterator<? extends Map.Entry<Float,Boolean>> i = entrySet().iterator();
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
	 final ObjectIterator<? extends Map.Entry<Float,Boolean>> i = entrySet().iterator();
	 int n = size();
	 Float2BooleanMap.Entry e;
	 boolean first = true;
	 s.append("{");
	 while(n-- != 0) {
	  if (first) first = false;
	  else s.append(", ");
	  e = (Float2BooleanMap.Entry )i.next();
	   s.append(String.valueOf(e.getFloatKey()));
	  s.append("=>");
	   s.append(String.valueOf(e.getBooleanValue()));
	 }
	 s.append("}");
	 return s.toString();
	}
}

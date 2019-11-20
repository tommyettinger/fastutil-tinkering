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
package it.unimi.dsi.fastutil.ints;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharCollections;
import it.unimi.dsi.fastutil.chars.CharSets;
import java.util.Map;
/** A class providing static methods and objects that do useful things with type-specific maps.
	*
	* @see it.unimi.dsi.fastutil.Maps
	* @see java.util.Collections
	*/
public class Int2CharMaps {
	private Int2CharMaps() {}
	/** An immutable class representing an empty type-specific map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific map.
	 */
	public static class EmptyMap extends Int2CharFunctions.EmptyFunction implements Int2CharMap , java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected EmptyMap() {}
	 @Override
	 public boolean containsValue(final char v) { return false; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 public boolean containsValue(final Object ov) { return false; }
	 @Override
	 public void putAll(final Map<? extends Integer, ? extends Character> m) { throw new UnsupportedOperationException(); }
	 @SuppressWarnings("unchecked")
	 @Override
	 public ObjectSet<Int2CharMap.Entry > int2CharEntrySet() { return ObjectSets.EMPTY_SET; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSet<Map.Entry<Integer, Character>> entrySet() { return (ObjectSet)int2CharEntrySet(); }
	
	 @Override
	 public IntSet keySet() { return IntSets.EMPTY_SET; }
	
	 @Override
	 public CharCollection values() { return CharSets.EMPTY_SET; }
	 private Object readResolve() { return EMPTY_MAP; }
	 @Override
	 public Object clone() { return EMPTY_MAP; }
	 @Override
	 public boolean isEmpty() { return true; }
	 @Override
	 public int hashCode() { return 0; }
	 @Override
	 public boolean equals(final Object o) {
	  if (! (o instanceof Map)) return false;
	  return ((Map<?,?>)o).isEmpty();
	 }
	 @Override
	 public String toString() { return "{}"; }
	}
	/** An empty type-specific map (immutable). It is serializable and cloneable.
	 */

	public static final EmptyMap EMPTY_MAP = new EmptyMap();
	/** An immutable class representing a type-specific singleton map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific map.
	 */
	public static class Singleton extends Int2CharFunctions.Singleton implements Int2CharMap , java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected transient ObjectSet<Int2CharMap.Entry > entries;
	 protected transient IntSet keys;
	 protected transient CharCollection values;
	 protected Singleton(final int key, final char value) {
	  super(key, value);
	 }
	 @Override
	 public boolean containsValue(final char v) { return ( (value) == (v) ); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 public boolean containsValue(final Object ov) { return ( (((((Character)(ov)).charValue()))) == (value) ); }
	 @Override
	 public void putAll(final Map<? extends Integer, ? extends Character> m) { throw new UnsupportedOperationException(); }
	 @Override
	 public ObjectSet<Int2CharMap.Entry > int2CharEntrySet() { if (entries == null) entries = ObjectSets.singleton((Int2CharMap.Entry )new AbstractInt2CharMap.BasicEntry (key, value)); return entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSet<Map.Entry<Integer, Character>> entrySet() { return (ObjectSet)int2CharEntrySet(); }
	 @Override
	 public IntSet keySet() { if (keys == null) keys = IntSets.singleton(key); return keys; }
	 @Override
	 public CharCollection values() { if (values == null) values = CharSets.singleton(value); return values; }
	 @Override
	 public boolean isEmpty() { return false; }
	 @Override
	 public int hashCode() { return (key) ^ (value); }
	 @Override
	 public boolean equals(final Object o) {
	  if (o == this) return true;
	  if (! (o instanceof Map)) return false;
	  Map<?,?> m = (Map<?,?>)o;
	  if (m.size() != 1) return false;
	  return m.entrySet().iterator().next().equals(entrySet().iterator().next());
	 }
	 @Override
	 public String toString() { return "{" + key + "=>" + value + "}"; }
	}
	/** Returns a type-specific immutable map containing only the specified pair. The returned map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned map.
	 * @param value the only value of the returned map.
	 * @return a type-specific immutable map containing just the pair <code>&lt;key,value&gt;</code>.
	 */
	public static Int2CharMap singleton(final int key, char value) {
	 return new Singleton (key, value);
	}
	/** Returns a type-specific immutable map containing only the specified pair. The returned map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned map.
	 * @param value the only value of the returned map.
	 * @return a type-specific immutable map containing just the pair <code>&lt;key,value&gt;</code>.
	 */
	public static Int2CharMap singleton(final Integer key, final Character value) {
	 return new Singleton (((key).intValue()), ((value).charValue()));
	}
	/** A synchronized wrapper class for maps. */
	public static class SynchronizedMap extends Int2CharFunctions.SynchronizedFunction implements Int2CharMap , java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final Int2CharMap map;
	 protected transient ObjectSet<Int2CharMap.Entry > entries;
	 protected transient IntSet keys;
	 protected transient CharCollection values;
	 protected SynchronizedMap(final Int2CharMap m, final Object sync) {
	  super(m, sync);
	  this.map = m;
	 }
	 protected SynchronizedMap(final Int2CharMap m) {
	  super(m);
	  this.map = m;
	 }
	 @Override
	 public boolean containsValue(final char v) { synchronized(sync) { return map.containsValue(v); } }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public boolean containsValue(final Object ov) { synchronized(sync) { return map.containsValue(ov); } }
	 @Override
	 public void putAll(final Map<? extends Integer, ? extends Character> m) { synchronized(sync) { map.putAll(m); } }
	 @Override
	 public ObjectSet<Int2CharMap.Entry > int2CharEntrySet() {
	  synchronized(sync) { if (entries == null) entries = ObjectSets.synchronize(map.int2CharEntrySet(), sync); return entries; }
	 }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 public ObjectSet<Map.Entry<Integer, Character>> entrySet() { return (ObjectSet)int2CharEntrySet(); }
	 @Override
	 public IntSet keySet() {
	  synchronized(sync) { if (keys == null) keys = IntSets.synchronize(map.keySet(), sync); return keys; }
	 }
	 @Override
	 public CharCollection values() {
	  synchronized(sync) { if (values == null) return CharCollections.synchronize(map.values(), sync); return values; }
	 }
	 @Override
	 public boolean isEmpty() { synchronized(sync) { return map.isEmpty(); } }
	 @Override
	 public int hashCode() { synchronized(sync) { return map.hashCode(); } }
	 @Override
	 public boolean equals(final Object o) { if (o == this) return true; synchronized(sync) { return map.equals(o); } }
	 private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
	  synchronized(sync) { s.defaultWriteObject(); }
	 }
	}
	/** Returns a synchronized type-specific map backed by the given type-specific map.
	 *
	 * @param m the map to be wrapped in a synchronized map.
	 * @return a synchronized view of the specified map.
	 * @see java.util.Collections#synchronizedMap(Map)
	 */
	public static Int2CharMap synchronize(final Int2CharMap m) { return new SynchronizedMap (m); }
	/** Returns a synchronized type-specific map backed by the given type-specific map, using an assigned object to synchronize.
	 *
	 * @param m the map to be wrapped in a synchronized map.
	 * @param sync an object that will be used to synchronize the access to the map.
	 * @return a synchronized view of the specified map.
	 * @see java.util.Collections#synchronizedMap(Map)
	 */
	public static Int2CharMap synchronize(final Int2CharMap m, final Object sync) { return new SynchronizedMap (m, sync); }
	/** An unmodifiable wrapper class for maps. */
	public static class UnmodifiableMap extends Int2CharFunctions.UnmodifiableFunction implements Int2CharMap , java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final Int2CharMap map;
	 protected transient ObjectSet<Int2CharMap.Entry > entries;
	 protected transient IntSet keys;
	 protected transient CharCollection values;
	 protected UnmodifiableMap(final Int2CharMap m) {
	  super(m);
	  this.map = m;
	 }
	 @Override
	 public boolean containsValue(final char v) { return map.containsValue(v); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public boolean containsValue(final Object ov) { return map.containsValue(ov); }
	 @Override
	 public boolean isEmpty() { return map.isEmpty(); }
	 @Override
	 public void putAll(final Map<? extends Integer, ? extends Character> m) { throw new UnsupportedOperationException(); }
	 @Override
	 public ObjectSet<Int2CharMap.Entry > int2CharEntrySet() { if (entries == null) entries = ObjectSets.unmodifiable(map.int2CharEntrySet()); return entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 public ObjectSet<Map.Entry<Integer, Character>> entrySet() { return (ObjectSet)int2CharEntrySet(); }
	 @Override
	 public IntSet keySet() { if (keys == null) keys = IntSets.unmodifiable(map.keySet()); return keys; }
	 @Override
	 public CharCollection values() { if (values == null) return CharCollections.unmodifiable(map.values()); return values; }
	}
	/** Returns an unmodifiable type-specific map backed by the given type-specific map.
	 *
	 * @param m the map to be wrapped in an unmodifiable map.
	 * @return an unmodifiable view of the specified map.
	 * @see java.util.Collections#unmodifiableMap(Map)
	 */
	public static Int2CharMap unmodifiable(final Int2CharMap m) { return new UnmodifiableMap (m); }
}

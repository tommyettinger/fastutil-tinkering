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
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import it.unimi.dsi.fastutil.objects.ReferenceCollections;
import it.unimi.dsi.fastutil.objects.ReferenceSets;
import java.util.Map;
/** A class providing static methods and objects that do useful things with type-specific maps.
	*
	* @see it.unimi.dsi.fastutil.Maps
	* @see java.util.Collections
	*/
public class Byte2ReferenceMaps {
	private Byte2ReferenceMaps() {}
	/** An immutable class representing an empty type-specific map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific map.
	 */
	public static class EmptyMap <V> extends Byte2ReferenceFunctions.EmptyFunction <V> implements Byte2ReferenceMap <V>, java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected EmptyMap() {}
	 @Override
	 public boolean containsValue(final Object v) { return false; }
	 @Override
	 public void putAll(final Map<? extends Byte, ? extends V> m) { throw new UnsupportedOperationException(); }
	 @SuppressWarnings("unchecked")
	 @Override
	 public ObjectSet<Byte2ReferenceMap.Entry <V> > byte2ReferenceEntrySet() { return ObjectSets.EMPTY_SET; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSet<Map.Entry<Byte, V>> entrySet() { return (ObjectSet)byte2ReferenceEntrySet(); }
	
	 @Override
	 public ByteSet keySet() { return ByteSets.EMPTY_SET; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public ReferenceCollection <V> values() { return ReferenceSets.EMPTY_SET; }
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
	@SuppressWarnings("rawtypes")
	public static final EmptyMap EMPTY_MAP = new EmptyMap();
	/** Returns an empty map (immutable). It is serializable and cloneable.
	 *
	 * <P>This method provides a typesafe access to {@link #EMPTY_MAP}.
	 * @return an empty map (immutable).
	 */
	@SuppressWarnings("unchecked")
	public static <V> Byte2ReferenceMap <V> emptyMap() {
	 return EMPTY_MAP;
	}
	/** An immutable class representing a type-specific singleton map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific map.
	 */
	public static class Singleton <V> extends Byte2ReferenceFunctions.Singleton <V> implements Byte2ReferenceMap <V>, java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected transient ObjectSet<Byte2ReferenceMap.Entry <V> > entries;
	 protected transient ByteSet keys;
	 protected transient ReferenceCollection <V> values;
	 protected Singleton(final byte key, final V value) {
	  super(key, value);
	 }
	 @Override
	 public boolean containsValue(final Object v) { return ( (value) == (v) ); }
	 @Override
	 public void putAll(final Map<? extends Byte, ? extends V> m) { throw new UnsupportedOperationException(); }
	 @Override
	 public ObjectSet<Byte2ReferenceMap.Entry <V> > byte2ReferenceEntrySet() { if (entries == null) entries = ObjectSets.singleton((Byte2ReferenceMap.Entry <V>)new AbstractByte2ReferenceMap.BasicEntry <V>(key, value)); return entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSet<Map.Entry<Byte, V>> entrySet() { return (ObjectSet)byte2ReferenceEntrySet(); }
	 @Override
	 public ByteSet keySet() { if (keys == null) keys = ByteSets.singleton(key); return keys; }
	 @Override
	 public ReferenceCollection <V> values() { if (values == null) values = ReferenceSets.singleton(value); return values; }
	 @Override
	 public boolean isEmpty() { return false; }
	 @Override
	 public int hashCode() { return (key) ^ ( (value) == null ? 0 : System.identityHashCode(value) ); }
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
	public static <V> Byte2ReferenceMap <V> singleton(final byte key, V value) {
	 return new Singleton <V>(key, value);
	}
	/** Returns a type-specific immutable map containing only the specified pair. The returned map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned map.
	 * @param value the only value of the returned map.
	 * @return a type-specific immutable map containing just the pair <code>&lt;key,value&gt;</code>.
	 */
	public static <V> Byte2ReferenceMap <V> singleton(final Byte key, final V value) {
	 return new Singleton <V>(((key).byteValue()), (value));
	}
	/** A synchronized wrapper class for maps. */
	public static class SynchronizedMap <V> extends Byte2ReferenceFunctions.SynchronizedFunction <V> implements Byte2ReferenceMap <V>, java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final Byte2ReferenceMap <V> map;
	 protected transient ObjectSet<Byte2ReferenceMap.Entry <V> > entries;
	 protected transient ByteSet keys;
	 protected transient ReferenceCollection <V> values;
	 protected SynchronizedMap(final Byte2ReferenceMap <V> m, final Object sync) {
	  super(m, sync);
	  this.map = m;
	 }
	 protected SynchronizedMap(final Byte2ReferenceMap <V> m) {
	  super(m);
	  this.map = m;
	 }
	 @Override
	 public boolean containsValue(final Object v) { synchronized(sync) { return map.containsValue(v); } }
	 @Override
	 public void putAll(final Map<? extends Byte, ? extends V> m) { synchronized(sync) { map.putAll(m); } }
	 @Override
	 public ObjectSet<Byte2ReferenceMap.Entry <V> > byte2ReferenceEntrySet() {
	  synchronized(sync) { if (entries == null) entries = ObjectSets.synchronize(map.byte2ReferenceEntrySet(), sync); return entries; }
	 }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 public ObjectSet<Map.Entry<Byte, V>> entrySet() { return (ObjectSet)byte2ReferenceEntrySet(); }
	 @Override
	 public ByteSet keySet() {
	  synchronized(sync) { if (keys == null) keys = ByteSets.synchronize(map.keySet(), sync); return keys; }
	 }
	 @Override
	 public ReferenceCollection <V> values() {
	  synchronized(sync) { if (values == null) return ReferenceCollections.synchronize(map.values(), sync); return values; }
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
	public static <V> Byte2ReferenceMap <V> synchronize(final Byte2ReferenceMap <V> m) { return new SynchronizedMap <V>(m); }
	/** Returns a synchronized type-specific map backed by the given type-specific map, using an assigned object to synchronize.
	 *
	 * @param m the map to be wrapped in a synchronized map.
	 * @param sync an object that will be used to synchronize the access to the map.
	 * @return a synchronized view of the specified map.
	 * @see java.util.Collections#synchronizedMap(Map)
	 */
	public static <V> Byte2ReferenceMap <V> synchronize(final Byte2ReferenceMap <V> m, final Object sync) { return new SynchronizedMap <V>(m, sync); }
	/** An unmodifiable wrapper class for maps. */
	public static class UnmodifiableMap <V> extends Byte2ReferenceFunctions.UnmodifiableFunction <V> implements Byte2ReferenceMap <V>, java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final Byte2ReferenceMap <V> map;
	 protected transient ObjectSet<Byte2ReferenceMap.Entry <V> > entries;
	 protected transient ByteSet keys;
	 protected transient ReferenceCollection <V> values;
	 protected UnmodifiableMap(final Byte2ReferenceMap <V> m) {
	  super(m);
	  this.map = m;
	 }
	 @Override
	 public boolean containsValue(final Object v) { return map.containsValue(v); }
	 @Override
	 public boolean isEmpty() { return map.isEmpty(); }
	 @Override
	 public void putAll(final Map<? extends Byte, ? extends V> m) { throw new UnsupportedOperationException(); }
	 @Override
	 public ObjectSet<Byte2ReferenceMap.Entry <V> > byte2ReferenceEntrySet() { if (entries == null) entries = ObjectSets.unmodifiable(map.byte2ReferenceEntrySet()); return entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 public ObjectSet<Map.Entry<Byte, V>> entrySet() { return (ObjectSet)byte2ReferenceEntrySet(); }
	 @Override
	 public ByteSet keySet() { if (keys == null) keys = ByteSets.unmodifiable(map.keySet()); return keys; }
	 @Override
	 public ReferenceCollection <V> values() { if (values == null) return ReferenceCollections.unmodifiable(map.values()); return values; }
	}
	/** Returns an unmodifiable type-specific map backed by the given type-specific map.
	 *
	 * @param m the map to be wrapped in an unmodifiable map.
	 * @return an unmodifiable view of the specified map.
	 * @see java.util.Collections#unmodifiableMap(Map)
	 */
	public static <V> Byte2ReferenceMap <V> unmodifiable(final Byte2ReferenceMap <V> m) { return new UnmodifiableMap <V>(m); }
}

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
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortCollections;
import it.unimi.dsi.fastutil.shorts.ShortSets;
import java.util.Map;
/** A class providing static methods and objects that do useful things with type-specific maps.
	*
	* @see it.unimi.dsi.fastutil.Maps
	* @see java.util.Collections
	*/
public class Reference2ShortMaps {
	private Reference2ShortMaps() {}
	/** An immutable class representing an empty type-specific map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific map.
	 */
	public static class EmptyMap <K> extends Reference2ShortFunctions.EmptyFunction <K> implements Reference2ShortMap <K>, java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected EmptyMap() {}
	 @Override
	 public boolean containsValue(final short v) { return false; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 public boolean containsValue(final Object ov) { return false; }
	 @Override
	 public void putAll(final Map<? extends K, ? extends Short> m) { throw new UnsupportedOperationException(); }
	 @SuppressWarnings("unchecked")
	 @Override
	 public ObjectSet<Reference2ShortMap.Entry <K> > reference2ShortEntrySet() { return ObjectSets.EMPTY_SET; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSet<Map.Entry<K, Short>> entrySet() { return (ObjectSet)reference2ShortEntrySet(); }
	 @SuppressWarnings("unchecked")
	 @Override
	 public ReferenceSet <K> keySet() { return ReferenceSets.EMPTY_SET; }
	
	 @Override
	 public ShortCollection values() { return ShortSets.EMPTY_SET; }
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
	public static <K> Reference2ShortMap <K> emptyMap() {
	 return EMPTY_MAP;
	}
	/** An immutable class representing a type-specific singleton map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific map.
	 */
	public static class Singleton <K> extends Reference2ShortFunctions.Singleton <K> implements Reference2ShortMap <K>, java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected transient ObjectSet<Reference2ShortMap.Entry <K> > entries;
	 protected transient ReferenceSet <K> keys;
	 protected transient ShortCollection values;
	 protected Singleton(final K key, final short value) {
	  super(key, value);
	 }
	 @Override
	 public boolean containsValue(final short v) { return ( (value) == (v) ); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 public boolean containsValue(final Object ov) { return ( (((((Short)(ov)).shortValue()))) == (value) ); }
	 @Override
	 public void putAll(final Map<? extends K, ? extends Short> m) { throw new UnsupportedOperationException(); }
	 @Override
	 public ObjectSet<Reference2ShortMap.Entry <K> > reference2ShortEntrySet() { if (entries == null) entries = ObjectSets.singleton((Reference2ShortMap.Entry <K>)new AbstractReference2ShortMap.BasicEntry <K>(key, value)); return entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSet<Map.Entry<K, Short>> entrySet() { return (ObjectSet)reference2ShortEntrySet(); }
	 @Override
	 public ReferenceSet <K> keySet() { if (keys == null) keys = ReferenceSets.singleton(key); return keys; }
	 @Override
	 public ShortCollection values() { if (values == null) values = ShortSets.singleton(value); return values; }
	 @Override
	 public boolean isEmpty() { return false; }
	 @Override
	 public int hashCode() { return ( System.identityHashCode(key) ) ^ (value); }
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
	public static <K> Reference2ShortMap <K> singleton(final K key, short value) {
	 return new Singleton <K>(key, value);
	}
	/** Returns a type-specific immutable map containing only the specified pair. The returned map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned map.
	 * @param value the only value of the returned map.
	 * @return a type-specific immutable map containing just the pair <code>&lt;key,value&gt;</code>.
	 */
	public static <K> Reference2ShortMap <K> singleton(final K key, final Short value) {
	 return new Singleton <K>((key), ((value).shortValue()));
	}
	/** A synchronized wrapper class for maps. */
	public static class SynchronizedMap <K> extends Reference2ShortFunctions.SynchronizedFunction <K> implements Reference2ShortMap <K>, java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final Reference2ShortMap <K> map;
	 protected transient ObjectSet<Reference2ShortMap.Entry <K> > entries;
	 protected transient ReferenceSet <K> keys;
	 protected transient ShortCollection values;
	 protected SynchronizedMap(final Reference2ShortMap <K> m, final Object sync) {
	  super(m, sync);
	  this.map = m;
	 }
	 protected SynchronizedMap(final Reference2ShortMap <K> m) {
	  super(m);
	  this.map = m;
	 }
	 @Override
	 public boolean containsValue(final short v) { synchronized(sync) { return map.containsValue(v); } }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public boolean containsValue(final Object ov) { synchronized(sync) { return map.containsValue(ov); } }
	 @Override
	 public void putAll(final Map<? extends K, ? extends Short> m) { synchronized(sync) { map.putAll(m); } }
	 @Override
	 public ObjectSet<Reference2ShortMap.Entry <K> > reference2ShortEntrySet() {
	  synchronized(sync) { if (entries == null) entries = ObjectSets.synchronize(map.reference2ShortEntrySet(), sync); return entries; }
	 }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 public ObjectSet<Map.Entry<K, Short>> entrySet() { return (ObjectSet)reference2ShortEntrySet(); }
	 @Override
	 public ReferenceSet <K> keySet() {
	  synchronized(sync) { if (keys == null) keys = ReferenceSets.synchronize(map.keySet(), sync); return keys; }
	 }
	 @Override
	 public ShortCollection values() {
	  synchronized(sync) { if (values == null) return ShortCollections.synchronize(map.values(), sync); return values; }
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
	public static <K> Reference2ShortMap <K> synchronize(final Reference2ShortMap <K> m) { return new SynchronizedMap <K>(m); }
	/** Returns a synchronized type-specific map backed by the given type-specific map, using an assigned object to synchronize.
	 *
	 * @param m the map to be wrapped in a synchronized map.
	 * @param sync an object that will be used to synchronize the access to the map.
	 * @return a synchronized view of the specified map.
	 * @see java.util.Collections#synchronizedMap(Map)
	 */
	public static <K> Reference2ShortMap <K> synchronize(final Reference2ShortMap <K> m, final Object sync) { return new SynchronizedMap <K>(m, sync); }
	/** An unmodifiable wrapper class for maps. */
	public static class UnmodifiableMap <K> extends Reference2ShortFunctions.UnmodifiableFunction <K> implements Reference2ShortMap <K>, java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final Reference2ShortMap <K> map;
	 protected transient ObjectSet<Reference2ShortMap.Entry <K> > entries;
	 protected transient ReferenceSet <K> keys;
	 protected transient ShortCollection values;
	 protected UnmodifiableMap(final Reference2ShortMap <K> m) {
	  super(m);
	  this.map = m;
	 }
	 @Override
	 public boolean containsValue(final short v) { return map.containsValue(v); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public boolean containsValue(final Object ov) { return map.containsValue(ov); }
	 @Override
	 public boolean isEmpty() { return map.isEmpty(); }
	 @Override
	 public void putAll(final Map<? extends K, ? extends Short> m) { throw new UnsupportedOperationException(); }
	 @Override
	 public ObjectSet<Reference2ShortMap.Entry <K> > reference2ShortEntrySet() { if (entries == null) entries = ObjectSets.unmodifiable(map.reference2ShortEntrySet()); return entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 public ObjectSet<Map.Entry<K, Short>> entrySet() { return (ObjectSet)reference2ShortEntrySet(); }
	 @Override
	 public ReferenceSet <K> keySet() { if (keys == null) keys = ReferenceSets.unmodifiable(map.keySet()); return keys; }
	 @Override
	 public ShortCollection values() { if (values == null) return ShortCollections.unmodifiable(map.values()); return values; }
	}
	/** Returns an unmodifiable type-specific map backed by the given type-specific map.
	 *
	 * @param m the map to be wrapped in an unmodifiable map.
	 * @return an unmodifiable view of the specified map.
	 * @see java.util.Collections#unmodifiableMap(Map)
	 */
	public static <K> Reference2ShortMap <K> unmodifiable(final Reference2ShortMap <K> m) { return new UnmodifiableMap <K>(m); }
}

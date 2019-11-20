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
package it.unimi.dsi.fastutil.shorts;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.NoSuchElementException;
/** A class providing static methods and objects that do useful things with type-specific sorted maps.
	*
	* @see java.util.Collections
	*/
public class Short2ObjectSortedMaps {
	private Short2ObjectSortedMaps() {}
	/** Returns a comparator for entries based on a given comparator on keys.
	 *
	 * @param comparator a comparator on keys.
	 * @return the associated comparator on entries.
	 */
	public static Comparator<? super Map.Entry<Short, ?>> entryComparator(final ShortComparator comparator) {
	 return new Comparator<Map.Entry<Short, ?>>() {
	  public int compare(Map.Entry<Short, ?> x, Map.Entry<Short, ?> y) {
	   return comparator.compare(x.getKey(), y.getKey());
	  }
	 };
	}
	/** An immutable class representing an empty type-specific sorted map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */
	public static class EmptySortedMap <V> extends Short2ObjectMaps.EmptyMap <V> implements Short2ObjectSortedMap <V>, java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected EmptySortedMap() {}
	 @Override
	 public ShortComparator comparator() { return null; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public ObjectSortedSet<Short2ObjectMap.Entry <V> > short2ObjectEntrySet() { return ObjectSortedSets.EMPTY_SET; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings("unchecked")
	 public ObjectSortedSet<Map.Entry<Short, V>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
	
	 @Override
	 public ShortSortedSet keySet() { return ShortSortedSets.EMPTY_SET; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Short2ObjectSortedMap <V> subMap(final short from, final short to) { return EMPTY_MAP; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Short2ObjectSortedMap <V> headMap(final short to) { return EMPTY_MAP; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Short2ObjectSortedMap <V> tailMap(final short from) { return EMPTY_MAP; }
	 @Override
	 public short firstShortKey() { throw new NoSuchElementException(); }
	 @Override
	 public short lastShortKey() { throw new NoSuchElementException(); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short2ObjectSortedMap <V> headMap(Short oto) { return headMap(((oto).shortValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short2ObjectSortedMap <V> tailMap(Short ofrom) { return tailMap(((ofrom).shortValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short2ObjectSortedMap <V> subMap(Short ofrom, Short oto) { return subMap(((ofrom).shortValue()), ((oto).shortValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short firstKey() { return (Short.valueOf(firstShortKey())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short lastKey() { return (Short.valueOf(lastShortKey())); }
	}
	/** An empty sorted map (immutable). It is serializable and cloneable.
	 */
	@SuppressWarnings("rawtypes")
	public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
	/** Returns an empty sorted map (immutable). It is serializable and cloneable.
	 *
	 * <P>This method provides a typesafe access to {@link #EMPTY_MAP}.
	 * @return an empty sorted map (immutable).
	 */
	@SuppressWarnings("unchecked")
	public static <V> Short2ObjectSortedMap <V> emptyMap() {
	 return EMPTY_MAP;
	}
	/** An immutable class representing a type-specific singleton sorted map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */
	public static class Singleton <V> extends Short2ObjectMaps.Singleton <V> implements Short2ObjectSortedMap <V>, java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final ShortComparator comparator;
	 protected Singleton(final short key, final V value, ShortComparator comparator) {
	  super(key, value);
	  this.comparator = comparator;
	 }
	 protected Singleton(final short key, final V value) {
	  this(key, value, null);
	 }
	
	 final int compare(final short k1, final short k2) {
	  return comparator == null ? ( Short.compare((k1),(k2)) ) : comparator.compare(k1, k2);
	 }
	 @Override
	 public ShortComparator comparator() { return comparator; }
	
	 @Override
	 public ObjectSortedSet<Short2ObjectMap.Entry <V> > short2ObjectEntrySet() { if (entries == null) entries = ObjectSortedSets.singleton((Short2ObjectMap.Entry <V>)new AbstractShort2ObjectMap.BasicEntry <V>(key, value), (Comparator<? super Short2ObjectMap.Entry <V> >)entryComparator(comparator)); return (ObjectSortedSet<Short2ObjectMap.Entry <V> >)entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSortedSet<Map.Entry<Short, V>> entrySet() { return (ObjectSortedSet)short2ObjectEntrySet(); }
	 @Override
	 public ShortSortedSet keySet() { if (keys == null) keys = ShortSortedSets.singleton(key, comparator); return (ShortSortedSet )keys; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Short2ObjectSortedMap <V> subMap(final short from, final short to) { if (compare(from, key) <= 0 && compare(key, to) < 0) return this; return EMPTY_MAP; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Short2ObjectSortedMap <V> headMap(final short to) { if (compare(key, to) < 0) return this; return EMPTY_MAP; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Short2ObjectSortedMap <V> tailMap(final short from) { if (compare(from, key) <= 0) return this; return EMPTY_MAP; }
	 @Override
	 public short firstShortKey() { return key; }
	 @Override
	 public short lastShortKey() { return key; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short2ObjectSortedMap <V> headMap(Short oto) { return headMap(((oto).shortValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short2ObjectSortedMap <V> tailMap(Short ofrom) { return tailMap(((ofrom).shortValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short2ObjectSortedMap <V> subMap(Short ofrom, Short oto) { return subMap(((ofrom).shortValue()), ((oto).shortValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short firstKey() { return (Short.valueOf(firstShortKey())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short lastKey() { return (Short.valueOf(lastShortKey())); }
	}
	/** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value&gt;</code>.
	 */
	public static <V> Short2ObjectSortedMap <V> singleton(final Short key, V value) {
	 return new Singleton <V>(((key).shortValue()), (value));
	}
	/** RETURNS a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @param comparator the comparator to use in the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value&gt;</code>.
	 */
	public static <V> Short2ObjectSortedMap <V> singleton(final Short key, V value, ShortComparator comparator) {
	 return new Singleton <V>(((key).shortValue()), (value), comparator);
	}
	/** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value&gt;</code>.
	 */
	public static <V> Short2ObjectSortedMap <V> singleton(final short key, final V value) {
	 return new Singleton <V>(key, value);
	}
	/** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @param comparator the comparator to use in the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value&gt;</code>.
	 */
	public static <V> Short2ObjectSortedMap <V> singleton(final short key, final V value, ShortComparator comparator) {
	 return new Singleton <V>(key, value, comparator);
	}
	 /** A synchronized wrapper class for sorted maps. */
	public static class SynchronizedSortedMap <V> extends Short2ObjectMaps.SynchronizedMap <V> implements Short2ObjectSortedMap <V>, java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final Short2ObjectSortedMap <V> sortedMap;
	 protected SynchronizedSortedMap(final Short2ObjectSortedMap <V> m, final Object sync) {
	  super(m, sync);
	  sortedMap = m;
	 }
	 protected SynchronizedSortedMap(final Short2ObjectSortedMap <V> m) {
	  super(m);
	  sortedMap = m;
	 }
	 @Override
	 public ShortComparator comparator() { synchronized(sync) { return sortedMap.comparator(); } }
	 @Override
	 public ObjectSortedSet<Short2ObjectMap.Entry <V> > short2ObjectEntrySet() { if (entries == null) entries = ObjectSortedSets.synchronize(sortedMap.short2ObjectEntrySet(), sync); return (ObjectSortedSet<Short2ObjectMap.Entry <V> >)entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSortedSet<Map.Entry<Short, V>> entrySet() { return (ObjectSortedSet)short2ObjectEntrySet(); }
	 @Override
	 public ShortSortedSet keySet() { if (keys == null) keys = ShortSortedSets.synchronize(sortedMap.keySet(), sync); return (ShortSortedSet )keys; }
	 @Override
	 public Short2ObjectSortedMap <V> subMap(final short from, final short to) { return new SynchronizedSortedMap <V>(sortedMap.subMap(from, to), sync); }
	 @Override
	 public Short2ObjectSortedMap <V> headMap(final short to) { return new SynchronizedSortedMap <V>(sortedMap.headMap(to), sync); }
	 @Override
	 public Short2ObjectSortedMap <V> tailMap(final short from) { return new SynchronizedSortedMap <V>(sortedMap.tailMap(from), sync); }
	 @Override
	 public short firstShortKey() { synchronized(sync) { return sortedMap.firstShortKey(); } }
	 @Override
	 public short lastShortKey() { synchronized(sync) { return sortedMap.lastShortKey(); } }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short firstKey() { synchronized(sync) { return sortedMap.firstKey(); } }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short lastKey() { synchronized(sync) { return sortedMap.lastKey(); } }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short2ObjectSortedMap <V> subMap(final Short from, final Short to) { return new SynchronizedSortedMap <V>(sortedMap.subMap(from, to), sync); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short2ObjectSortedMap <V> headMap(final Short to) { return new SynchronizedSortedMap <V>(sortedMap.headMap(to), sync); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short2ObjectSortedMap <V> tailMap(final Short from) { return new SynchronizedSortedMap <V>(sortedMap.tailMap(from), sync); }
	}
	/** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
	public static <V> Short2ObjectSortedMap <V> synchronize(final Short2ObjectSortedMap <V> m) { return new SynchronizedSortedMap <V>(m); }
	/** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map, using an assigned object to synchronize.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @param sync an object that will be used to synchronize the access to the sorted sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
	public static <V> Short2ObjectSortedMap <V> synchronize(final Short2ObjectSortedMap <V> m, final Object sync) { return new SynchronizedSortedMap <V>(m, sync); }
	/** An unmodifiable wrapper class for sorted maps. */
	public static class UnmodifiableSortedMap <V> extends Short2ObjectMaps.UnmodifiableMap <V> implements Short2ObjectSortedMap <V>, java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final Short2ObjectSortedMap <V> sortedMap;
	 protected UnmodifiableSortedMap(final Short2ObjectSortedMap <V> m) {
	  super(m);
	  sortedMap = m;
	 }
	 @Override
	 public ShortComparator comparator() { return sortedMap.comparator(); }
	 @Override
	 public ObjectSortedSet<Short2ObjectMap.Entry <V> > short2ObjectEntrySet() { if (entries == null) entries = ObjectSortedSets.unmodifiable(sortedMap.short2ObjectEntrySet()); return (ObjectSortedSet<Short2ObjectMap.Entry <V> >)entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSortedSet<Map.Entry<Short, V>> entrySet() { return (ObjectSortedSet)short2ObjectEntrySet(); }
	 @Override
	 public ShortSortedSet keySet() { if (keys == null) keys = ShortSortedSets.unmodifiable(sortedMap.keySet()); return (ShortSortedSet )keys; }
	 @Override
	 public Short2ObjectSortedMap <V> subMap(final short from, final short to) { return new UnmodifiableSortedMap <V>(sortedMap.subMap(from, to)); }
	 @Override
	 public Short2ObjectSortedMap <V> headMap(final short to) { return new UnmodifiableSortedMap <V>(sortedMap.headMap(to)); }
	 @Override
	 public Short2ObjectSortedMap <V> tailMap(final short from) { return new UnmodifiableSortedMap <V>(sortedMap.tailMap(from)); }
	 @Override
	 public short firstShortKey() { return sortedMap.firstShortKey(); }
	 @Override
	 public short lastShortKey() { return sortedMap.lastShortKey(); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short firstKey() { return sortedMap.firstKey(); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short lastKey() { return sortedMap.lastKey(); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short2ObjectSortedMap <V> subMap(final Short from, final Short to) { return new UnmodifiableSortedMap <V>(sortedMap.subMap(from, to)); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short2ObjectSortedMap <V> headMap(final Short to) { return new UnmodifiableSortedMap <V>(sortedMap.headMap(to)); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Short2ObjectSortedMap <V> tailMap(final Short from) { return new UnmodifiableSortedMap <V>(sortedMap.tailMap(from)); }
	}
	/** Returns an unmodifiable type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in an unmodifiable sorted map.
	 * @return an unmodifiable view of the specified sorted map.
	 * @see java.util.Collections#unmodifiableSortedMap(SortedMap)
	 */
	public static <V> Short2ObjectSortedMap <V> unmodifiable(final Short2ObjectSortedMap <V> m) { return new UnmodifiableSortedMap <V>(m); }
}
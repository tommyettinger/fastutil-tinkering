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
public class Byte2ReferenceSortedMaps {
	private Byte2ReferenceSortedMaps() {}
	/** Returns a comparator for entries based on a given comparator on keys.
	 *
	 * @param comparator a comparator on keys.
	 * @return the associated comparator on entries.
	 */
	public static Comparator<? super Map.Entry<Byte, ?>> entryComparator(final ByteComparator comparator) {
	 return new Comparator<Map.Entry<Byte, ?>>() {
	  public int compare(Map.Entry<Byte, ?> x, Map.Entry<Byte, ?> y) {
	   return comparator.compare(x.getKey(), y.getKey());
	  }
	 };
	}
	/** An immutable class representing an empty type-specific sorted map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */
	public static class EmptySortedMap <V> extends Byte2ReferenceMaps.EmptyMap <V> implements Byte2ReferenceSortedMap <V>, java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected EmptySortedMap() {}
	 @Override
	 public ByteComparator comparator() { return null; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public ObjectSortedSet<Byte2ReferenceMap.Entry <V> > byte2ReferenceEntrySet() { return ObjectSortedSets.EMPTY_SET; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings("unchecked")
	 public ObjectSortedSet<Map.Entry<Byte, V>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
	
	 @Override
	 public ByteSortedSet keySet() { return ByteSortedSets.EMPTY_SET; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Byte2ReferenceSortedMap <V> subMap(final byte from, final byte to) { return EMPTY_MAP; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Byte2ReferenceSortedMap <V> headMap(final byte to) { return EMPTY_MAP; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Byte2ReferenceSortedMap <V> tailMap(final byte from) { return EMPTY_MAP; }
	 @Override
	 public byte firstByteKey() { throw new NoSuchElementException(); }
	 @Override
	 public byte lastByteKey() { throw new NoSuchElementException(); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ReferenceSortedMap <V> headMap(Byte oto) { return headMap(((oto).byteValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ReferenceSortedMap <V> tailMap(Byte ofrom) { return tailMap(((ofrom).byteValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ReferenceSortedMap <V> subMap(Byte ofrom, Byte oto) { return subMap(((ofrom).byteValue()), ((oto).byteValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte firstKey() { return (Byte.valueOf(firstByteKey())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte lastKey() { return (Byte.valueOf(lastByteKey())); }
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
	public static <V> Byte2ReferenceSortedMap <V> emptyMap() {
	 return EMPTY_MAP;
	}
	/** An immutable class representing a type-specific singleton sorted map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */
	public static class Singleton <V> extends Byte2ReferenceMaps.Singleton <V> implements Byte2ReferenceSortedMap <V>, java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final ByteComparator comparator;
	 protected Singleton(final byte key, final V value, ByteComparator comparator) {
	  super(key, value);
	  this.comparator = comparator;
	 }
	 protected Singleton(final byte key, final V value) {
	  this(key, value, null);
	 }
	
	 final int compare(final byte k1, final byte k2) {
	  return comparator == null ? ( Byte.compare((k1),(k2)) ) : comparator.compare(k1, k2);
	 }
	 @Override
	 public ByteComparator comparator() { return comparator; }
	
	 @Override
	 public ObjectSortedSet<Byte2ReferenceMap.Entry <V> > byte2ReferenceEntrySet() { if (entries == null) entries = ObjectSortedSets.singleton((Byte2ReferenceMap.Entry <V>)new AbstractByte2ReferenceMap.BasicEntry <V>(key, value), (Comparator<? super Byte2ReferenceMap.Entry <V> >)entryComparator(comparator)); return (ObjectSortedSet<Byte2ReferenceMap.Entry <V> >)entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSortedSet<Map.Entry<Byte, V>> entrySet() { return (ObjectSortedSet)byte2ReferenceEntrySet(); }
	 @Override
	 public ByteSortedSet keySet() { if (keys == null) keys = ByteSortedSets.singleton(key, comparator); return (ByteSortedSet )keys; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Byte2ReferenceSortedMap <V> subMap(final byte from, final byte to) { if (compare(from, key) <= 0 && compare(key, to) < 0) return this; return EMPTY_MAP; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Byte2ReferenceSortedMap <V> headMap(final byte to) { if (compare(key, to) < 0) return this; return EMPTY_MAP; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Byte2ReferenceSortedMap <V> tailMap(final byte from) { if (compare(from, key) <= 0) return this; return EMPTY_MAP; }
	 @Override
	 public byte firstByteKey() { return key; }
	 @Override
	 public byte lastByteKey() { return key; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ReferenceSortedMap <V> headMap(Byte oto) { return headMap(((oto).byteValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ReferenceSortedMap <V> tailMap(Byte ofrom) { return tailMap(((ofrom).byteValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ReferenceSortedMap <V> subMap(Byte ofrom, Byte oto) { return subMap(((ofrom).byteValue()), ((oto).byteValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte firstKey() { return (Byte.valueOf(firstByteKey())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte lastKey() { return (Byte.valueOf(lastByteKey())); }
	}
	/** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value&gt;</code>.
	 */
	public static <V> Byte2ReferenceSortedMap <V> singleton(final Byte key, V value) {
	 return new Singleton <V>(((key).byteValue()), (value));
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
	public static <V> Byte2ReferenceSortedMap <V> singleton(final Byte key, V value, ByteComparator comparator) {
	 return new Singleton <V>(((key).byteValue()), (value), comparator);
	}
	/** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value&gt;</code>.
	 */
	public static <V> Byte2ReferenceSortedMap <V> singleton(final byte key, final V value) {
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
	public static <V> Byte2ReferenceSortedMap <V> singleton(final byte key, final V value, ByteComparator comparator) {
	 return new Singleton <V>(key, value, comparator);
	}
	 /** A synchronized wrapper class for sorted maps. */
	public static class SynchronizedSortedMap <V> extends Byte2ReferenceMaps.SynchronizedMap <V> implements Byte2ReferenceSortedMap <V>, java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final Byte2ReferenceSortedMap <V> sortedMap;
	 protected SynchronizedSortedMap(final Byte2ReferenceSortedMap <V> m, final Object sync) {
	  super(m, sync);
	  sortedMap = m;
	 }
	 protected SynchronizedSortedMap(final Byte2ReferenceSortedMap <V> m) {
	  super(m);
	  sortedMap = m;
	 }
	 @Override
	 public ByteComparator comparator() { synchronized(sync) { return sortedMap.comparator(); } }
	 @Override
	 public ObjectSortedSet<Byte2ReferenceMap.Entry <V> > byte2ReferenceEntrySet() { if (entries == null) entries = ObjectSortedSets.synchronize(sortedMap.byte2ReferenceEntrySet(), sync); return (ObjectSortedSet<Byte2ReferenceMap.Entry <V> >)entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSortedSet<Map.Entry<Byte, V>> entrySet() { return (ObjectSortedSet)byte2ReferenceEntrySet(); }
	 @Override
	 public ByteSortedSet keySet() { if (keys == null) keys = ByteSortedSets.synchronize(sortedMap.keySet(), sync); return (ByteSortedSet )keys; }
	 @Override
	 public Byte2ReferenceSortedMap <V> subMap(final byte from, final byte to) { return new SynchronizedSortedMap <V>(sortedMap.subMap(from, to), sync); }
	 @Override
	 public Byte2ReferenceSortedMap <V> headMap(final byte to) { return new SynchronizedSortedMap <V>(sortedMap.headMap(to), sync); }
	 @Override
	 public Byte2ReferenceSortedMap <V> tailMap(final byte from) { return new SynchronizedSortedMap <V>(sortedMap.tailMap(from), sync); }
	 @Override
	 public byte firstByteKey() { synchronized(sync) { return sortedMap.firstByteKey(); } }
	 @Override
	 public byte lastByteKey() { synchronized(sync) { return sortedMap.lastByteKey(); } }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte firstKey() { synchronized(sync) { return sortedMap.firstKey(); } }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte lastKey() { synchronized(sync) { return sortedMap.lastKey(); } }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ReferenceSortedMap <V> subMap(final Byte from, final Byte to) { return new SynchronizedSortedMap <V>(sortedMap.subMap(from, to), sync); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ReferenceSortedMap <V> headMap(final Byte to) { return new SynchronizedSortedMap <V>(sortedMap.headMap(to), sync); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ReferenceSortedMap <V> tailMap(final Byte from) { return new SynchronizedSortedMap <V>(sortedMap.tailMap(from), sync); }
	}
	/** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
	public static <V> Byte2ReferenceSortedMap <V> synchronize(final Byte2ReferenceSortedMap <V> m) { return new SynchronizedSortedMap <V>(m); }
	/** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map, using an assigned object to synchronize.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @param sync an object that will be used to synchronize the access to the sorted sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
	public static <V> Byte2ReferenceSortedMap <V> synchronize(final Byte2ReferenceSortedMap <V> m, final Object sync) { return new SynchronizedSortedMap <V>(m, sync); }
	/** An unmodifiable wrapper class for sorted maps. */
	public static class UnmodifiableSortedMap <V> extends Byte2ReferenceMaps.UnmodifiableMap <V> implements Byte2ReferenceSortedMap <V>, java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final Byte2ReferenceSortedMap <V> sortedMap;
	 protected UnmodifiableSortedMap(final Byte2ReferenceSortedMap <V> m) {
	  super(m);
	  sortedMap = m;
	 }
	 @Override
	 public ByteComparator comparator() { return sortedMap.comparator(); }
	 @Override
	 public ObjectSortedSet<Byte2ReferenceMap.Entry <V> > byte2ReferenceEntrySet() { if (entries == null) entries = ObjectSortedSets.unmodifiable(sortedMap.byte2ReferenceEntrySet()); return (ObjectSortedSet<Byte2ReferenceMap.Entry <V> >)entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSortedSet<Map.Entry<Byte, V>> entrySet() { return (ObjectSortedSet)byte2ReferenceEntrySet(); }
	 @Override
	 public ByteSortedSet keySet() { if (keys == null) keys = ByteSortedSets.unmodifiable(sortedMap.keySet()); return (ByteSortedSet )keys; }
	 @Override
	 public Byte2ReferenceSortedMap <V> subMap(final byte from, final byte to) { return new UnmodifiableSortedMap <V>(sortedMap.subMap(from, to)); }
	 @Override
	 public Byte2ReferenceSortedMap <V> headMap(final byte to) { return new UnmodifiableSortedMap <V>(sortedMap.headMap(to)); }
	 @Override
	 public Byte2ReferenceSortedMap <V> tailMap(final byte from) { return new UnmodifiableSortedMap <V>(sortedMap.tailMap(from)); }
	 @Override
	 public byte firstByteKey() { return sortedMap.firstByteKey(); }
	 @Override
	 public byte lastByteKey() { return sortedMap.lastByteKey(); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte firstKey() { return sortedMap.firstKey(); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte lastKey() { return sortedMap.lastKey(); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ReferenceSortedMap <V> subMap(final Byte from, final Byte to) { return new UnmodifiableSortedMap <V>(sortedMap.subMap(from, to)); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ReferenceSortedMap <V> headMap(final Byte to) { return new UnmodifiableSortedMap <V>(sortedMap.headMap(to)); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ReferenceSortedMap <V> tailMap(final Byte from) { return new UnmodifiableSortedMap <V>(sortedMap.tailMap(from)); }
	}
	/** Returns an unmodifiable type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in an unmodifiable sorted map.
	 * @return an unmodifiable view of the specified sorted map.
	 * @see java.util.Collections#unmodifiableSortedMap(SortedMap)
	 */
	public static <V> Byte2ReferenceSortedMap <V> unmodifiable(final Byte2ReferenceSortedMap <V> m) { return new UnmodifiableSortedMap <V>(m); }
}

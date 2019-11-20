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
public class Byte2ByteSortedMaps {
	private Byte2ByteSortedMaps() {}
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
	public static class EmptySortedMap extends Byte2ByteMaps.EmptyMap implements Byte2ByteSortedMap , java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected EmptySortedMap() {}
	 @Override
	 public ByteComparator comparator() { return null; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public ObjectSortedSet<Byte2ByteMap.Entry > byte2ByteEntrySet() { return ObjectSortedSets.EMPTY_SET; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings("unchecked")
	 public ObjectSortedSet<Map.Entry<Byte, Byte>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
	
	 @Override
	 public ByteSortedSet keySet() { return ByteSortedSets.EMPTY_SET; }
	
	 @Override
	 public Byte2ByteSortedMap subMap(final byte from, final byte to) { return EMPTY_MAP; }
	
	 @Override
	 public Byte2ByteSortedMap headMap(final byte to) { return EMPTY_MAP; }
	
	 @Override
	 public Byte2ByteSortedMap tailMap(final byte from) { return EMPTY_MAP; }
	 @Override
	 public byte firstByteKey() { throw new NoSuchElementException(); }
	 @Override
	 public byte lastByteKey() { throw new NoSuchElementException(); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ByteSortedMap headMap(Byte oto) { return headMap(((oto).byteValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ByteSortedMap tailMap(Byte ofrom) { return tailMap(((ofrom).byteValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ByteSortedMap subMap(Byte ofrom, Byte oto) { return subMap(((ofrom).byteValue()), ((oto).byteValue())); }
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

	public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
	/** An immutable class representing a type-specific singleton sorted map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */
	public static class Singleton extends Byte2ByteMaps.Singleton implements Byte2ByteSortedMap , java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final ByteComparator comparator;
	 protected Singleton(final byte key, final byte value, ByteComparator comparator) {
	  super(key, value);
	  this.comparator = comparator;
	 }
	 protected Singleton(final byte key, final byte value) {
	  this(key, value, null);
	 }
	
	 final int compare(final byte k1, final byte k2) {
	  return comparator == null ? ( Byte.compare((k1),(k2)) ) : comparator.compare(k1, k2);
	 }
	 @Override
	 public ByteComparator comparator() { return comparator; }
	
	 @Override
	 public ObjectSortedSet<Byte2ByteMap.Entry > byte2ByteEntrySet() { if (entries == null) entries = ObjectSortedSets.singleton((Byte2ByteMap.Entry )new AbstractByte2ByteMap.BasicEntry (key, value), (Comparator<? super Byte2ByteMap.Entry >)entryComparator(comparator)); return (ObjectSortedSet<Byte2ByteMap.Entry >)entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSortedSet<Map.Entry<Byte, Byte>> entrySet() { return (ObjectSortedSet)byte2ByteEntrySet(); }
	 @Override
	 public ByteSortedSet keySet() { if (keys == null) keys = ByteSortedSets.singleton(key, comparator); return (ByteSortedSet )keys; }
	
	 @Override
	 public Byte2ByteSortedMap subMap(final byte from, final byte to) { if (compare(from, key) <= 0 && compare(key, to) < 0) return this; return EMPTY_MAP; }
	
	 @Override
	 public Byte2ByteSortedMap headMap(final byte to) { if (compare(key, to) < 0) return this; return EMPTY_MAP; }
	
	 @Override
	 public Byte2ByteSortedMap tailMap(final byte from) { if (compare(from, key) <= 0) return this; return EMPTY_MAP; }
	 @Override
	 public byte firstByteKey() { return key; }
	 @Override
	 public byte lastByteKey() { return key; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ByteSortedMap headMap(Byte oto) { return headMap(((oto).byteValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ByteSortedMap tailMap(Byte ofrom) { return tailMap(((ofrom).byteValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ByteSortedMap subMap(Byte ofrom, Byte oto) { return subMap(((ofrom).byteValue()), ((oto).byteValue())); }
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
	public static Byte2ByteSortedMap singleton(final Byte key, Byte value) {
	 return new Singleton (((key).byteValue()), ((value).byteValue()));
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
	public static Byte2ByteSortedMap singleton(final Byte key, Byte value, ByteComparator comparator) {
	 return new Singleton (((key).byteValue()), ((value).byteValue()), comparator);
	}
	/** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value&gt;</code>.
	 */
	public static Byte2ByteSortedMap singleton(final byte key, final byte value) {
	 return new Singleton (key, value);
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
	public static Byte2ByteSortedMap singleton(final byte key, final byte value, ByteComparator comparator) {
	 return new Singleton (key, value, comparator);
	}
	 /** A synchronized wrapper class for sorted maps. */
	public static class SynchronizedSortedMap extends Byte2ByteMaps.SynchronizedMap implements Byte2ByteSortedMap , java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final Byte2ByteSortedMap sortedMap;
	 protected SynchronizedSortedMap(final Byte2ByteSortedMap m, final Object sync) {
	  super(m, sync);
	  sortedMap = m;
	 }
	 protected SynchronizedSortedMap(final Byte2ByteSortedMap m) {
	  super(m);
	  sortedMap = m;
	 }
	 @Override
	 public ByteComparator comparator() { synchronized(sync) { return sortedMap.comparator(); } }
	 @Override
	 public ObjectSortedSet<Byte2ByteMap.Entry > byte2ByteEntrySet() { if (entries == null) entries = ObjectSortedSets.synchronize(sortedMap.byte2ByteEntrySet(), sync); return (ObjectSortedSet<Byte2ByteMap.Entry >)entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSortedSet<Map.Entry<Byte, Byte>> entrySet() { return (ObjectSortedSet)byte2ByteEntrySet(); }
	 @Override
	 public ByteSortedSet keySet() { if (keys == null) keys = ByteSortedSets.synchronize(sortedMap.keySet(), sync); return (ByteSortedSet )keys; }
	 @Override
	 public Byte2ByteSortedMap subMap(final byte from, final byte to) { return new SynchronizedSortedMap (sortedMap.subMap(from, to), sync); }
	 @Override
	 public Byte2ByteSortedMap headMap(final byte to) { return new SynchronizedSortedMap (sortedMap.headMap(to), sync); }
	 @Override
	 public Byte2ByteSortedMap tailMap(final byte from) { return new SynchronizedSortedMap (sortedMap.tailMap(from), sync); }
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
	 public Byte2ByteSortedMap subMap(final Byte from, final Byte to) { return new SynchronizedSortedMap (sortedMap.subMap(from, to), sync); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ByteSortedMap headMap(final Byte to) { return new SynchronizedSortedMap (sortedMap.headMap(to), sync); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ByteSortedMap tailMap(final Byte from) { return new SynchronizedSortedMap (sortedMap.tailMap(from), sync); }
	}
	/** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
	public static Byte2ByteSortedMap synchronize(final Byte2ByteSortedMap m) { return new SynchronizedSortedMap (m); }
	/** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map, using an assigned object to synchronize.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @param sync an object that will be used to synchronize the access to the sorted sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
	public static Byte2ByteSortedMap synchronize(final Byte2ByteSortedMap m, final Object sync) { return new SynchronizedSortedMap (m, sync); }
	/** An unmodifiable wrapper class for sorted maps. */
	public static class UnmodifiableSortedMap extends Byte2ByteMaps.UnmodifiableMap implements Byte2ByteSortedMap , java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final Byte2ByteSortedMap sortedMap;
	 protected UnmodifiableSortedMap(final Byte2ByteSortedMap m) {
	  super(m);
	  sortedMap = m;
	 }
	 @Override
	 public ByteComparator comparator() { return sortedMap.comparator(); }
	 @Override
	 public ObjectSortedSet<Byte2ByteMap.Entry > byte2ByteEntrySet() { if (entries == null) entries = ObjectSortedSets.unmodifiable(sortedMap.byte2ByteEntrySet()); return (ObjectSortedSet<Byte2ByteMap.Entry >)entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSortedSet<Map.Entry<Byte, Byte>> entrySet() { return (ObjectSortedSet)byte2ByteEntrySet(); }
	 @Override
	 public ByteSortedSet keySet() { if (keys == null) keys = ByteSortedSets.unmodifiable(sortedMap.keySet()); return (ByteSortedSet )keys; }
	 @Override
	 public Byte2ByteSortedMap subMap(final byte from, final byte to) { return new UnmodifiableSortedMap (sortedMap.subMap(from, to)); }
	 @Override
	 public Byte2ByteSortedMap headMap(final byte to) { return new UnmodifiableSortedMap (sortedMap.headMap(to)); }
	 @Override
	 public Byte2ByteSortedMap tailMap(final byte from) { return new UnmodifiableSortedMap (sortedMap.tailMap(from)); }
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
	 public Byte2ByteSortedMap subMap(final Byte from, final Byte to) { return new UnmodifiableSortedMap (sortedMap.subMap(from, to)); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ByteSortedMap headMap(final Byte to) { return new UnmodifiableSortedMap (sortedMap.headMap(to)); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Byte2ByteSortedMap tailMap(final Byte from) { return new UnmodifiableSortedMap (sortedMap.tailMap(from)); }
	}
	/** Returns an unmodifiable type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in an unmodifiable sorted map.
	 * @return an unmodifiable view of the specified sorted map.
	 * @see java.util.Collections#unmodifiableSortedMap(SortedMap)
	 */
	public static Byte2ByteSortedMap unmodifiable(final Byte2ByteSortedMap m) { return new UnmodifiableSortedMap (m); }
}

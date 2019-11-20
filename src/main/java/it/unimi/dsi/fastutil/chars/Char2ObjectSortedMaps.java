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
public class Char2ObjectSortedMaps {
	private Char2ObjectSortedMaps() {}
	/** Returns a comparator for entries based on a given comparator on keys.
	 *
	 * @param comparator a comparator on keys.
	 * @return the associated comparator on entries.
	 */
	public static Comparator<? super Map.Entry<Character, ?>> entryComparator(final CharComparator comparator) {
	 return new Comparator<Map.Entry<Character, ?>>() {
	  public int compare(Map.Entry<Character, ?> x, Map.Entry<Character, ?> y) {
	   return comparator.compare(x.getKey(), y.getKey());
	  }
	 };
	}
	/** An immutable class representing an empty type-specific sorted map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */
	public static class EmptySortedMap <V> extends Char2ObjectMaps.EmptyMap <V> implements Char2ObjectSortedMap <V>, java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected EmptySortedMap() {}
	 @Override
	 public CharComparator comparator() { return null; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public ObjectSortedSet<Char2ObjectMap.Entry <V> > char2ObjectEntrySet() { return ObjectSortedSets.EMPTY_SET; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings("unchecked")
	 public ObjectSortedSet<Map.Entry<Character, V>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
	
	 @Override
	 public CharSortedSet keySet() { return CharSortedSets.EMPTY_SET; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Char2ObjectSortedMap <V> subMap(final char from, final char to) { return EMPTY_MAP; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Char2ObjectSortedMap <V> headMap(final char to) { return EMPTY_MAP; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Char2ObjectSortedMap <V> tailMap(final char from) { return EMPTY_MAP; }
	 @Override
	 public char firstCharKey() { throw new NoSuchElementException(); }
	 @Override
	 public char lastCharKey() { throw new NoSuchElementException(); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Char2ObjectSortedMap <V> headMap(Character oto) { return headMap(((oto).charValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Char2ObjectSortedMap <V> tailMap(Character ofrom) { return tailMap(((ofrom).charValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Char2ObjectSortedMap <V> subMap(Character ofrom, Character oto) { return subMap(((ofrom).charValue()), ((oto).charValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Character firstKey() { return (Character.valueOf(firstCharKey())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Character lastKey() { return (Character.valueOf(lastCharKey())); }
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
	public static <V> Char2ObjectSortedMap <V> emptyMap() {
	 return EMPTY_MAP;
	}
	/** An immutable class representing a type-specific singleton sorted map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */
	public static class Singleton <V> extends Char2ObjectMaps.Singleton <V> implements Char2ObjectSortedMap <V>, java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final CharComparator comparator;
	 protected Singleton(final char key, final V value, CharComparator comparator) {
	  super(key, value);
	  this.comparator = comparator;
	 }
	 protected Singleton(final char key, final V value) {
	  this(key, value, null);
	 }
	
	 final int compare(final char k1, final char k2) {
	  return comparator == null ? ( Character.compare((k1),(k2)) ) : comparator.compare(k1, k2);
	 }
	 @Override
	 public CharComparator comparator() { return comparator; }
	
	 @Override
	 public ObjectSortedSet<Char2ObjectMap.Entry <V> > char2ObjectEntrySet() { if (entries == null) entries = ObjectSortedSets.singleton((Char2ObjectMap.Entry <V>)new AbstractChar2ObjectMap.BasicEntry <V>(key, value), (Comparator<? super Char2ObjectMap.Entry <V> >)entryComparator(comparator)); return (ObjectSortedSet<Char2ObjectMap.Entry <V> >)entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSortedSet<Map.Entry<Character, V>> entrySet() { return (ObjectSortedSet)char2ObjectEntrySet(); }
	 @Override
	 public CharSortedSet keySet() { if (keys == null) keys = CharSortedSets.singleton(key, comparator); return (CharSortedSet )keys; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Char2ObjectSortedMap <V> subMap(final char from, final char to) { if (compare(from, key) <= 0 && compare(key, to) < 0) return this; return EMPTY_MAP; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Char2ObjectSortedMap <V> headMap(final char to) { if (compare(key, to) < 0) return this; return EMPTY_MAP; }
	 @SuppressWarnings("unchecked")
	 @Override
	 public Char2ObjectSortedMap <V> tailMap(final char from) { if (compare(from, key) <= 0) return this; return EMPTY_MAP; }
	 @Override
	 public char firstCharKey() { return key; }
	 @Override
	 public char lastCharKey() { return key; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Char2ObjectSortedMap <V> headMap(Character oto) { return headMap(((oto).charValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Char2ObjectSortedMap <V> tailMap(Character ofrom) { return tailMap(((ofrom).charValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Char2ObjectSortedMap <V> subMap(Character ofrom, Character oto) { return subMap(((ofrom).charValue()), ((oto).charValue())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Character firstKey() { return (Character.valueOf(firstCharKey())); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Character lastKey() { return (Character.valueOf(lastCharKey())); }
	}
	/** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value&gt;</code>.
	 */
	public static <V> Char2ObjectSortedMap <V> singleton(final Character key, V value) {
	 return new Singleton <V>(((key).charValue()), (value));
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
	public static <V> Char2ObjectSortedMap <V> singleton(final Character key, V value, CharComparator comparator) {
	 return new Singleton <V>(((key).charValue()), (value), comparator);
	}
	/** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value&gt;</code>.
	 */
	public static <V> Char2ObjectSortedMap <V> singleton(final char key, final V value) {
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
	public static <V> Char2ObjectSortedMap <V> singleton(final char key, final V value, CharComparator comparator) {
	 return new Singleton <V>(key, value, comparator);
	}
	 /** A synchronized wrapper class for sorted maps. */
	public static class SynchronizedSortedMap <V> extends Char2ObjectMaps.SynchronizedMap <V> implements Char2ObjectSortedMap <V>, java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final Char2ObjectSortedMap <V> sortedMap;
	 protected SynchronizedSortedMap(final Char2ObjectSortedMap <V> m, final Object sync) {
	  super(m, sync);
	  sortedMap = m;
	 }
	 protected SynchronizedSortedMap(final Char2ObjectSortedMap <V> m) {
	  super(m);
	  sortedMap = m;
	 }
	 @Override
	 public CharComparator comparator() { synchronized(sync) { return sortedMap.comparator(); } }
	 @Override
	 public ObjectSortedSet<Char2ObjectMap.Entry <V> > char2ObjectEntrySet() { if (entries == null) entries = ObjectSortedSets.synchronize(sortedMap.char2ObjectEntrySet(), sync); return (ObjectSortedSet<Char2ObjectMap.Entry <V> >)entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSortedSet<Map.Entry<Character, V>> entrySet() { return (ObjectSortedSet)char2ObjectEntrySet(); }
	 @Override
	 public CharSortedSet keySet() { if (keys == null) keys = CharSortedSets.synchronize(sortedMap.keySet(), sync); return (CharSortedSet )keys; }
	 @Override
	 public Char2ObjectSortedMap <V> subMap(final char from, final char to) { return new SynchronizedSortedMap <V>(sortedMap.subMap(from, to), sync); }
	 @Override
	 public Char2ObjectSortedMap <V> headMap(final char to) { return new SynchronizedSortedMap <V>(sortedMap.headMap(to), sync); }
	 @Override
	 public Char2ObjectSortedMap <V> tailMap(final char from) { return new SynchronizedSortedMap <V>(sortedMap.tailMap(from), sync); }
	 @Override
	 public char firstCharKey() { synchronized(sync) { return sortedMap.firstCharKey(); } }
	 @Override
	 public char lastCharKey() { synchronized(sync) { return sortedMap.lastCharKey(); } }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Character firstKey() { synchronized(sync) { return sortedMap.firstKey(); } }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Character lastKey() { synchronized(sync) { return sortedMap.lastKey(); } }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Char2ObjectSortedMap <V> subMap(final Character from, final Character to) { return new SynchronizedSortedMap <V>(sortedMap.subMap(from, to), sync); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Char2ObjectSortedMap <V> headMap(final Character to) { return new SynchronizedSortedMap <V>(sortedMap.headMap(to), sync); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Char2ObjectSortedMap <V> tailMap(final Character from) { return new SynchronizedSortedMap <V>(sortedMap.tailMap(from), sync); }
	}
	/** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
	public static <V> Char2ObjectSortedMap <V> synchronize(final Char2ObjectSortedMap <V> m) { return new SynchronizedSortedMap <V>(m); }
	/** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map, using an assigned object to synchronize.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @param sync an object that will be used to synchronize the access to the sorted sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
	public static <V> Char2ObjectSortedMap <V> synchronize(final Char2ObjectSortedMap <V> m, final Object sync) { return new SynchronizedSortedMap <V>(m, sync); }
	/** An unmodifiable wrapper class for sorted maps. */
	public static class UnmodifiableSortedMap <V> extends Char2ObjectMaps.UnmodifiableMap <V> implements Char2ObjectSortedMap <V>, java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final Char2ObjectSortedMap <V> sortedMap;
	 protected UnmodifiableSortedMap(final Char2ObjectSortedMap <V> m) {
	  super(m);
	  sortedMap = m;
	 }
	 @Override
	 public CharComparator comparator() { return sortedMap.comparator(); }
	 @Override
	 public ObjectSortedSet<Char2ObjectMap.Entry <V> > char2ObjectEntrySet() { if (entries == null) entries = ObjectSortedSets.unmodifiable(sortedMap.char2ObjectEntrySet()); return (ObjectSortedSet<Char2ObjectMap.Entry <V> >)entries; }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public ObjectSortedSet<Map.Entry<Character, V>> entrySet() { return (ObjectSortedSet)char2ObjectEntrySet(); }
	 @Override
	 public CharSortedSet keySet() { if (keys == null) keys = CharSortedSets.unmodifiable(sortedMap.keySet()); return (CharSortedSet )keys; }
	 @Override
	 public Char2ObjectSortedMap <V> subMap(final char from, final char to) { return new UnmodifiableSortedMap <V>(sortedMap.subMap(from, to)); }
	 @Override
	 public Char2ObjectSortedMap <V> headMap(final char to) { return new UnmodifiableSortedMap <V>(sortedMap.headMap(to)); }
	 @Override
	 public Char2ObjectSortedMap <V> tailMap(final char from) { return new UnmodifiableSortedMap <V>(sortedMap.tailMap(from)); }
	 @Override
	 public char firstCharKey() { return sortedMap.firstCharKey(); }
	 @Override
	 public char lastCharKey() { return sortedMap.lastCharKey(); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Character firstKey() { return sortedMap.firstKey(); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Character lastKey() { return sortedMap.lastKey(); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Char2ObjectSortedMap <V> subMap(final Character from, final Character to) { return new UnmodifiableSortedMap <V>(sortedMap.subMap(from, to)); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Char2ObjectSortedMap <V> headMap(final Character to) { return new UnmodifiableSortedMap <V>(sortedMap.headMap(to)); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Char2ObjectSortedMap <V> tailMap(final Character from) { return new UnmodifiableSortedMap <V>(sortedMap.tailMap(from)); }
	}
	/** Returns an unmodifiable type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in an unmodifiable sorted map.
	 * @return an unmodifiable view of the specified sorted map.
	 * @see java.util.Collections#unmodifiableSortedMap(SortedMap)
	 */
	public static <V> Char2ObjectSortedMap <V> unmodifiable(final Char2ObjectSortedMap <V> m) { return new UnmodifiableSortedMap <V>(m); }
}

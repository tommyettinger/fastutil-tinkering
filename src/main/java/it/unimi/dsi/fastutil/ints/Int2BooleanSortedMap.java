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
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import java.util.Map;
import java.util.SortedMap;
/** A type-specific {@link SortedMap}; provides some additional methods that use polymorphism to avoid (un)boxing.
	*
	* <P>Additionally, this interface strengthens {@link #entrySet()},
	* {@link #keySet()}, {@link #values()},
	* {@link #comparator()}, {@link SortedMap#subMap(Object,Object)}, {@link SortedMap#headMap(Object)} and {@link SortedMap#tailMap(Object)}.
	*
	* @see SortedMap
	*/
public interface Int2BooleanSortedMap extends Int2BooleanMap , SortedMap<Integer, Boolean> {
	/** Returns a view of the portion of this sorted map whose keys range from <code>fromKey</code>, inclusive, to <code>toKey</code>, exclusive.
	 *
	 * <P>Note that this specification strengthens the one given in {@link SortedMap#subMap(Object,Object)}.
	 *
	 * @see SortedMap#subMap(Object,Object)
	 */
	Int2BooleanSortedMap subMap(int fromKey, int toKey);
	/** Returns a view of the portion of this sorted map whose keys are strictly less than <code>toKey</code>.
	 *
	 * <P>Note that this specification strengthens the one given in {@link SortedMap#headMap(Object)}.
	 *
	 * @see SortedMap#headMap(Object)
	 */
	Int2BooleanSortedMap headMap(int toKey);
	/** Returns a view of the portion of this sorted map whose keys are greater than or equal to <code>fromKey</code>.
	 *
	 * <P>Note that this specification strengthens the one given in {@link SortedMap#tailMap(Object)}.
	 *
	 * @see SortedMap#tailMap(Object)
	 */
	Int2BooleanSortedMap tailMap(int fromKey);
	/** Returns the first (lowest) key currently in this map.
	 * @see SortedMap#firstKey()
	 */
	int firstIntKey();
	/** Returns the last (highest) key currently in this map.
	 * @see SortedMap#lastKey()
	 */
	int lastIntKey();
	/** {@inheritDoc}
	 * <P>Note that this specification strengthens the one given in {@link SortedMap#subMap(Object,Object)}.
	 * @deprecated Please use the corresponding type-specific method instead.
	 */
	@Deprecated
	@Override
	Int2BooleanSortedMap subMap(Integer fromKey, Integer toKey);
	/** {@inheritDoc}
	 * <P>Note that this specification strengthens the one given in {@link SortedMap#headMap(Object)}.
	 * @deprecated Please use the corresponding type-specific method instead.
	 */
	@Deprecated
	@Override
	Int2BooleanSortedMap headMap(Integer toKey);
	/** {@inheritDoc}
	 * <P>Note that this specification strengthens the one given in {@link SortedMap#tailMap(Object)}.
	 * @deprecated Please use the corresponding type-specific method instead.
	 */
	@Deprecated
	@Override
	Int2BooleanSortedMap tailMap(Integer fromKey);
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead.
	 */
	@Deprecated
	@Override
	Integer firstKey();
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead.
	 */
	@Deprecated
	@Override
	Integer lastKey();
	/** A sorted entry set providing fast iteration.
	 *
	 * <p>In some cases (e.g., hash-based classes) iteration over an entry set requires the creation
	 * of a large number of entry objects. Some <code>fastutil</code>
	 * maps might return {@linkplain #entrySet() entry set} objects of type <code>FastSortedEntrySet</code>: in this case, {@link #fastIterator() fastIterator()}
	 * will return an iterator that is guaranteed not to create a large number of objects, <em>possibly
	 * by returning always the same entry</em> (of course, mutated).
	 */
	public interface FastSortedEntrySet extends ObjectSortedSet<Int2BooleanMap.Entry >, FastEntrySet {
	 /** Returns a fast iterator over this sorted entry set; the iterator might return always the same entry object, suitably mutated.
		 *
		 * @return a fast iterator over this sorted entry set; the iterator might return always the same entry object, suitably mutated.
		 */
	 public ObjectBidirectionalIterator<Int2BooleanMap.Entry > fastIterator(Int2BooleanMap.Entry from);
	}
	/** Returns a sorted-set view of the mappings contained in this map.
	 *  <p>Note that this specification strengthens the one given in the
	 *  corresponding type-specific unsorted map.
	 *
	 * @return a sorted-set view of the mappings contained in this map.
	 * @see SortedMap#entrySet()
	 * @deprecated Please use the corresponding type-specific method instead.
	 */
	@Deprecated
	@Override
	ObjectSortedSet<Map.Entry<Integer, Boolean>> entrySet();
	/** Returns a type-specific sorted-set view of the mappings contained in this map.
	 * <p>Note that this specification strengthens the one given in the
	 * corresponding type-specific unsorted map.
	 *
	 * @return a type-specific sorted-set view of the mappings contained in this map.
	 * @see #entrySet()
	 */
	ObjectSortedSet<Int2BooleanMap.Entry > int2BooleanEntrySet();
	/** Returns a type-specific sorted-set view of the keys contained in this map.
	 *  <p>Note that this specification strengthens the one given in the
	 *  corresponding type-specific unsorted map.
	 *
	 * @return a sorted-set view of the keys contained in this map.
	 * @see SortedMap#keySet()
	 */
	@Override
	IntSortedSet keySet();
	/** Returns a type-specific set view of the values contained in this map.
	 * <P>Note that this specification strengthens the one given in {@link Map#values()},
	 * which was already strengthened in the corresponding type-specific class,
	 * but was weakened by the fact that this interface extends {@link SortedMap}.
	 *
	 * @return a set view of the values contained in this map.
	 * @see SortedMap#values()
	 */
	@Override
	BooleanCollection values();
	/** Returns the comparator associated with this sorted set, or null if it uses its keys' natural ordering.
	 *
	 *  <P>Note that this specification strengthens the one given in {@link SortedMap#comparator()}.
	 *
	 * @see SortedMap#comparator()
	 */
	@Override
	IntComparator comparator();
}
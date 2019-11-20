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
import java.util.SortedSet;
import java.util.Collection;
/** A type-specific {@link SortedSet}; provides some additional methods that use polymorphism to avoid (un)boxing.
	*
	* <P>Additionally, this interface strengthens {@link #iterator()},
	* {@link #comparator()} (for primitive types), {@link SortedSet#subSet(Object,Object)},
	* {@link SortedSet#headSet(Object)} and {@link SortedSet#tailSet(Object)}.
	*
	* @see SortedSet
	*/
public interface FloatSortedSet extends FloatSet , SortedSet<Float> {
	/** Returns a type-specific {@link it.unimi.dsi.fastutil.BidirectionalIterator} on the elements in
	 * this set, starting from a given element of the domain (optional operation).
	 *
	 * <P>This method returns a type-specific bidirectional iterator with given
	 * starting point. The starting point is any element comparable to the
	 * elements of this set (even if it does not actually belong to the
	 * set). The next element of the returned iterator is the least element of
	 * the set that is greater than the starting point (if there are no
	 * elements greater than the starting point, {@link
	 * it.unimi.dsi.fastutil.BidirectionalIterator#hasNext() hasNext()} will return
	 * <code>false</code>). The previous element of the returned iterator is
	 * the greatest element of the set that is smaller than or equal to the
	 * starting point (if there are no elements smaller than or equal to the
	 * starting point, {@link it.unimi.dsi.fastutil.BidirectionalIterator#hasPrevious()
	 * hasPrevious()} will return <code>false</code>).
	 *
	 * <P>Note that passing the last element of the set as starting point and
	 * calling {@link it.unimi.dsi.fastutil.BidirectionalIterator#previous() previous()} you can traverse the
	 * entire set in reverse order.
	 *
	 * @param fromElement an element to start from.
	 * @return a bidirectional iterator on the element in this set, starting at the given element.
	 * @throws UnsupportedOperationException if this set does not support iterators with a starting point.
	 */
	FloatBidirectionalIterator iterator(float fromElement);
	/** Returns a type-specific {@link it.unimi.dsi.fastutil.BidirectionalIterator} iterator on the collection.
	 *
	 * <p>Note that this specification strengthens the one given in the corresponding type-specific
	 * {@link Collection}.
	 *
	 * <P>The iterator returned by the {@link #iterator()} method and by this
	 * method are identical; however, using this method you can save a type casting.
	 *
	 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #iterator()}.
	 */
	@Deprecated
	FloatBidirectionalIterator floatIterator();
	/** Returns a type-specific {@link it.unimi.dsi.fastutil.BidirectionalIterator} on the elements in
	 * this set.
	 *
	 * <p>Note that this specification strengthens the one given in the corresponding type-specific
	 * {@link Collection}.
	 *
	 * <P>This method returns a parameterised bidirectional iterator. The iterator
	 * can be moreover safely cast to a type-specific iterator.
	 *
	 * @return a bidirectional iterator on the element in this set.
	 */
	@Override
	FloatBidirectionalIterator iterator();
	/** Returns a view of the portion of this sorted set whose elements range from <code>fromElement</code>, inclusive, to <code>toElement</code>, exclusive.
	 * <P>Note that this specification strengthens the one given in {@link SortedSet#subSet(Object,Object)}.
	 * @see SortedSet#subSet(Object,Object)
	 */
	FloatSortedSet subSet(float fromElement, float toElement) ;
	/** Returns a view of the portion of this sorted set whose elements are strictly less than <code>toElement</code>.
	 * <P>Note that this specification strengthens the one given in {@link SortedSet#headSet(Object)}.
	 * @see SortedSet#headSet(Object)
	 */
	FloatSortedSet headSet(float toElement);
	/** Returns a view of the portion of this sorted set whose elements are greater than or equal to <code>fromElement</code>.
	 * <P>Note that this specification strengthens the one given in {@link SortedSet#headSet(Object)}.
	 * @see SortedSet#tailSet(Object)
	 */
	FloatSortedSet tailSet(float fromElement);
	/** {@inheritDoc}
	 * <P>Note that this specification strengthens the one given in {@link SortedSet#comparator()}.
	 */
	FloatComparator comparator();
	/** Returns the first (lowest) element currently in this set.
	 * @see SortedSet#first()
	 */
	float firstFloat();
	/** Returns the last (highest) element currently in this set.
	 * @see SortedSet#last()
	 */
	float lastFloat();
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead.
	 */
	@Deprecated
	@Override
	FloatSortedSet subSet(Float fromElement, Float toElement) ;
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead.
	 */
	@Deprecated
	@Override
	FloatSortedSet headSet(Float toElement);
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead.
	 */
	@Deprecated
	@Override
	FloatSortedSet tailSet(Float fromElement);
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead.
	 */
	@Deprecated
	@Override
	Float first();
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead.
	 */
	@Deprecated
	@Override
	Float last();
}
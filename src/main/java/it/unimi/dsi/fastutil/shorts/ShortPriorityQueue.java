/*
	* Copyright (C) 2003-2017 Paolo Boldi and Sebastiano Vigna
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
import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.PriorityQueue;
/** A type-specific {@link PriorityQueue}; provides some additional methods that use polymorphism to avoid (un)boxing.
	*
	* <P>Additionally, this interface strengthens {@link #comparator()}.
	*/
public interface ShortPriorityQueue extends PriorityQueue<Short> {
	/** Enqueues a new element.
	 * @see #enqueue(Object)
	 * @param x the element to enqueue.
	 */
	void enqueue(short x);
	/** Dequeues the {@linkplain #first() first} element from the queue.
	 * @see #dequeue()
	 * @return the dequeued element.
	 * @throws NoSuchElementException if the queue is empty.
	 */
	short dequeueShort();
	/** Returns the first element of the queue.
	 * @see #first()
	 * @return the first element.
	 * @throws NoSuchElementException if the queue is empty.
	 */
	short firstShort();
	/** Returns the last element of the queue, that is, the element the would be dequeued last (optional operation).
	 * @see #last()
	 * @return the last element.
	 * @throws NoSuchElementException if the queue is empty.
	 */
	short lastShort();
	/** Returns the comparator associated with this priority queue, or null if it uses its elements' natural ordering.
	 *
	 * <P>Note that this specification strengthens the one given in {@link PriorityQueue#comparator()}.
	 * @see PriorityQueue#comparator()
	 * @return the comparator associated with this priority queue.
	 */
	@Override
	ShortComparator comparator();
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	void enqueue(Short x);
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	Short dequeue();
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	Short first();
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	Short last();
}

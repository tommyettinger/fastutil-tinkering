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
import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/**  An abstract class providing basic methods for priority queues implementing a type-specific interface.
	*
	*/
public abstract class AbstractShortPriorityQueue extends AbstractPriorityQueue<Short> implements java.io.Serializable, ShortPriorityQueue {
	private static final long serialVersionUID = 1L;
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public void enqueue(final Short x) { enqueue(x.shortValue()); }
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Short dequeue() { return (Short.valueOf(dequeueShort())); }
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Short first() { return (Short.valueOf(firstShort())); }
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Short last() { return (Short.valueOf(lastShort())); }
	/** {@inheritDoc}
	 * <p>This implementation always throws an {@link UnsupportedOperationException}. */
	@Override
	public short lastShort() { throw new UnsupportedOperationException(); }
}

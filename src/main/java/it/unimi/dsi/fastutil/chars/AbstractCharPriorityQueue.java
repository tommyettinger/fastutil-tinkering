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
import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/**  An abstract class providing basic methods for priority queues implementing a type-specific interface.
	*
	*/
public abstract class AbstractCharPriorityQueue extends AbstractPriorityQueue<Character> implements java.io.Serializable, CharPriorityQueue {
	private static final long serialVersionUID = 1L;
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public void enqueue(final Character x) { enqueue(x.charValue()); }
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Character dequeue() { return (Character.valueOf(dequeueChar())); }
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Character first() { return (Character.valueOf(firstChar())); }
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Character last() { return (Character.valueOf(lastChar())); }
	/** {@inheritDoc}
	 * <p>This implementation always throws an {@link UnsupportedOperationException}. */
	@Override
	public char lastChar() { throw new UnsupportedOperationException(); }
}

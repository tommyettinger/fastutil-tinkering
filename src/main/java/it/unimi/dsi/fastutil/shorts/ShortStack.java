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
import it.unimi.dsi.fastutil.Stack;
/** A type-specific {@link Stack}; provides some additional methods that use polymorphism to avoid (un)boxing. */
public interface ShortStack extends Stack<Short> {
	/** Pushes the given object on the stack.
	 * @see Stack#push(Object)
	 */
	void push(short k);
	/** Pushes the given object on the stack.
	 * @see Stack#pop()
	 */
	short popShort();
	/** Peeks at the top of the stack (optional operation).
	 * @see Stack#top()
	 */
	short topShort();
	/** Peeks at an element on the stack (optional operation).
	 * @see Stack#peek(int)
	 */
	short peekShort(int i);
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	void push(Short o);
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	Short pop();
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	Short top();
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	Short peek(int i);
}

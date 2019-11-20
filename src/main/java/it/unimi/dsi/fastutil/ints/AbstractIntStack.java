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
import it.unimi.dsi.fastutil.AbstractStack;
/** An abstract class providing basic methods for implementing a type-specific stack interface.
	*
	* <P>To create a type-specific stack, you need both object methods and
	* primitive-type methods. However, if you inherit from this class you need
	* just one (anyone).
	*/
public abstract class AbstractIntStack extends AbstractStack<Integer> implements IntStack {
	protected AbstractIntStack() {}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding generic method. */
	@Override
	public void push(int k) {
	 push(Integer.valueOf(k));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding generic method. */
	@Override
	public int popInt() {
	 return pop().intValue();
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding generic method. */
	@Override
	public int topInt() {
	 return top().intValue();
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding generic method. */
	@Override
	public int peekInt(int i) {
	 return peek(i).intValue();
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public void push(Integer o) {
	 push(o.intValue());
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Integer pop() {
	 return Integer.valueOf(popInt());
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Integer top() {
	 return Integer.valueOf(topInt());
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Integer peek(int i) {
	 return Integer.valueOf(peekInt(i));
	}
}

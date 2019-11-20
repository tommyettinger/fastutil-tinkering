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
import it.unimi.dsi.fastutil.AbstractStack;
/** An abstract class providing basic methods for implementing a type-specific stack interface.
	*
	* <P>To create a type-specific stack, you need both object methods and
	* primitive-type methods. However, if you inherit from this class you need
	* just one (anyone).
	*/
public abstract class AbstractByteStack extends AbstractStack<Byte> implements ByteStack {
	protected AbstractByteStack() {}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding generic method. */
	@Override
	public void push(byte k) {
	 push(Byte.valueOf(k));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding generic method. */
	@Override
	public byte popByte() {
	 return pop().byteValue();
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding generic method. */
	@Override
	public byte topByte() {
	 return top().byteValue();
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding generic method. */
	@Override
	public byte peekByte(int i) {
	 return peek(i).byteValue();
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public void push(Byte o) {
	 push(o.byteValue());
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Byte pop() {
	 return Byte.valueOf(popByte());
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Byte top() {
	 return Byte.valueOf(topByte());
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Byte peek(int i) {
	 return Byte.valueOf(peekByte(i));
	}
}

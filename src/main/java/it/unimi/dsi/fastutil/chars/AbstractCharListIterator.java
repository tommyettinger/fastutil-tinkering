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
/**  An abstract class facilitating the creation of type-specific {@linkplain java.util.ListIterator list iterators}.
	*
	* <P>This class provides trivial type-specific implementations of {@link
	* java.util.ListIterator#set(Object) set()} and {@link java.util.ListIterator#add(Object) add()} which
	* throw an {@link UnsupportedOperationException}. For primitive types, it also
	* provides a trivial implementation of {@link java.util.ListIterator#set(Object) set()} and {@link
	* java.util.ListIterator#add(Object) add()} that just invokes the type-specific one.
	*
	*
	* @see java.util.ListIterator
	*/
public abstract class AbstractCharListIterator extends AbstractCharBidirectionalIterator implements CharListIterator {
	protected AbstractCharListIterator() {}
	/** {@inheritDoc}
	 * <P>This implementation always throws an {@link UnsupportedOperationException}. */
	@Override
	public void set(char k) {
	 throw new UnsupportedOperationException();
	}
	/** {@inheritDoc}
	 * <P>This implementation always throws an {@link UnsupportedOperationException}. */
	@Override
	public void add(char k) {
	 throw new UnsupportedOperationException();
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public void set(Character ok) {
	 set(ok.charValue());
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public void add(Character ok) {
	 add(ok.charValue());
	}
}
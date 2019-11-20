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
import java.util.ListIterator;
/** A type-specific bidirectional iterator that is also a {@link ListIterator}.
	*
	* <P>This interface merges the methods provided by a {@link ListIterator} and
	* a type-specific {@link it.unimi.dsi.fastutil.BidirectionalIterator}. Moreover, it provides
	* type-specific versions of {@link ListIterator#add(Object) add()}
	* and {@link ListIterator#set(Object) set()}.
	*
	* @see java.util.ListIterator
	* @see it.unimi.dsi.fastutil.BidirectionalIterator
	*/
public interface CharListIterator extends ListIterator<Character>, CharBidirectionalIterator {
	/**
	 * Replaces the last element returned by {@link #next} or
	 * {@link #previous} with the specified element (optional operation).
	 * @param k the element used to replace the last element returned.
	 * @see ListIterator#set(Object)
	 */
	void set(char k);
	/**
	 * Inserts the specified element into the list (optional operation).
	 * The element is inserted immediately before the element that
	 * would be returned by {@link #next}, if any, and after the element
	 * that would be returned by {@link #previous}, if any.
	 * @param k the element to insert.
	 * @see ListIterator#add(Object)
	 */
	void add(char k);
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	void set(Character k);
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	void add(Character k);
}

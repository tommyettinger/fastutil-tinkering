/*
	* Copyright (C) 2010-2017 Sebastiano Vigna
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
import it.unimi.dsi.fastutil.BigListIterator;
/** A type-specific {@link BigListIterator}.
	*
	* @see BigListIterator
	*/
public interface FloatBigListIterator extends FloatBidirectionalIterator , BigListIterator<Float> {
	/**
	 * Replaces the last element returned by {@link #next()} or
	 * {@link #previous()} with the specified element (optional operation).
	 * @see java.util.ListIterator#set(Object)
	 */
	void set(float k);
	/**
	 * Inserts the specified element into the list (optional operation).
	 * @see java.util.ListIterator#add(Object)
	 */
	void add(float k);
	/**
	 * Replaces the last element returned by {@link #next()} or
	 * {@link #previous()} with the specified element (optional operation).
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	void set(Float k);
	/**
	 * Inserts the specified element into the list (optional operation).
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	void add(Float k);
}

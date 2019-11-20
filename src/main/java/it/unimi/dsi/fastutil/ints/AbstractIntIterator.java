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
/**  An abstract class facilitating the creation of type-specific iterators.
	*
	* <P>To create a type-specific iterator you need both a method returning the
	* next element as primitive type and a method returning the next element as an
	* object. However, if you inherit from this class you need just one (anyone).
	*
	* <P>This class implements also a trivial version of {@link #skip(int)} that uses
	* type-specific methods; moreover, {@link #remove()} will throw an {@link
	* UnsupportedOperationException}.
	*
	* @see java.util.Iterator
	*/
public abstract class AbstractIntIterator implements IntIterator {
	protected AbstractIntIterator() {}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding generic method. */
	@Override
	public int nextInt() {
	 return next().intValue();
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Integer next() {
	 return Integer.valueOf(nextInt());
	}
	/** {@inheritDoc}
	 * <P>This implementation always throws an {@link UnsupportedOperationException}. */
	@Override
	public void remove() {
	 throw new UnsupportedOperationException();
	}
	/** {@inheritDoc}
	 * <P>This implementaion iterates the type-specific version of {@link #next()} for at most
	 * {@code n} times, stopping if {@link #hasNext()} becomes false.
	 *
	 * @param n the number of elements to skip.
	 * @return the number of elements actually skipped.
	 */
	@Override
	public int skip(final int n) {
	 int i = n;
	 while(i-- != 0 && hasNext()) nextInt();
	 return n - i - 1;
	}
}

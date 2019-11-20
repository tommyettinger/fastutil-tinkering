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
package it.unimi.dsi.fastutil.doubles;
import java.util.ListIterator;
import it.unimi.dsi.fastutil.BigListIterator;
/**  An abstract class facilitating the creation of type-specific {@linkplain it.unimi.dsi.fastutil.BigListIterator big-list iterators}.
	*
	* <p>This implementation provides (deprecated) implementations of {@link ListIterator#previousIndex()} and {@link ListIterator#nextIndex()} that
	* just invoke the corresponding {@link BigListIterator} methods.
	*
	* @see java.util.ListIterator
	* @see it.unimi.dsi.fastutil.BigListIterator
	*/
public abstract class AbstractDoubleBigListIterator extends AbstractDoubleBidirectionalIterator implements DoubleBigListIterator {
	protected AbstractDoubleBigListIterator() {}
	/** {@inheritDoc}
	 * <P>This implementation always throws an {@link UnsupportedOperationException}. */
	@Override
	public void set(double k) {
	 throw new UnsupportedOperationException();
	}
	/** {@inheritDoc}
	 * <P>This implementation always throws an {@link UnsupportedOperationException}. */
	@Override
	public void add(double k) {
	 throw new UnsupportedOperationException();
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public void set(Double ok) {
	 set(ok.doubleValue());
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public void add(Double ok) {
	 add(ok.doubleValue());
	}
	/** {@inheritDoc}
	 * <P>This implementaion iterates the type-specific version of {@link #next()} for at most
	 * {@code n} times, stopping if {@link #hasNext()} becomes false.
	 *
	 * @param n the number of elements to skip.
	 * @return the number of elements actually skipped.
	 */
	@Override
	public long skip(final long n) {
	 long i = n;
	 while(i-- != 0 && hasNext()) nextDouble();
	 return n - i - 1;
	}
	/** {@inheritDoc}
	 *  <P>This implemntation iterates the type-specific version of {@link #previous()} for
	 * at most {@code n} times, stopping if {@link #hasPrevious()} becomes false.
	 *
	 * @param n the number of elements to skip backwards.
	 * @return the number of elements actually skipped.
	 */
	public long back(final long n) {
	 long i = n;
	 while(i-- != 0 && hasPrevious()) previousDouble();
	 return n - i - 1;
	}
}

/*
	* Copyright (C) 2003-2017 Sebastiano Vigna
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
package it.unimi.dsi.fastutil.longs;
/** An abstract class providing basic methods for sorted sets implementing a type-specific interface. */
public abstract class AbstractLongSortedSet extends AbstractLongSet implements LongSortedSet {
	protected AbstractLongSortedSet() {}
	/** {@inheritDoc}
	 *
	 * <p>This implementation delegates to the new covariantly stronger generic method {@link #iterator()}.
	 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #iterator()}.
	 */
	@Deprecated
	@Override
	public LongBidirectionalIterator longIterator() {
	 return iterator();
	}
	@Override
	public abstract LongBidirectionalIterator iterator();
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public LongSortedSet headSet(final Long to) {
	 return headSet(to.longValue());
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public LongSortedSet tailSet(final Long from) {
	 return tailSet(from.longValue());
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public LongSortedSet subSet(final Long from, final Long to) {
	 return subSet(from.longValue(), to.longValue());
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Long first() {
	 return (Long.valueOf(firstLong()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Long last() {
	 return (Long.valueOf(lastLong()));
	}
}

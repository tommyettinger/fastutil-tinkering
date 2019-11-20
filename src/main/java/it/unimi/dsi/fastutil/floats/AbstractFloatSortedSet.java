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
package it.unimi.dsi.fastutil.floats;
/** An abstract class providing basic methods for sorted sets implementing a type-specific interface. */
public abstract class AbstractFloatSortedSet extends AbstractFloatSet implements FloatSortedSet {
	protected AbstractFloatSortedSet() {}
	/** {@inheritDoc}
	 *
	 * <p>This implementation delegates to the new covariantly stronger generic method {@link #iterator()}.
	 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #iterator()}.
	 */
	@Deprecated
	@Override
	public FloatBidirectionalIterator floatIterator() {
	 return iterator();
	}
	@Override
	public abstract FloatBidirectionalIterator iterator();
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public FloatSortedSet headSet(final Float to) {
	 return headSet(to.floatValue());
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public FloatSortedSet tailSet(final Float from) {
	 return tailSet(from.floatValue());
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public FloatSortedSet subSet(final Float from, final Float to) {
	 return subSet(from.floatValue(), to.floatValue());
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Float first() {
	 return (Float.valueOf(firstFloat()));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Float last() {
	 return (Float.valueOf(lastFloat()));
	}
}

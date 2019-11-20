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
/** An abstract class providing basic methods for functions implementing a type-specific interface.
	*
	* <P>Optional operations just throw an {@link
	* UnsupportedOperationException}. Generic versions of accessors delegate to
	* the corresponding type-specific counterparts following the interface rules
	* (they take care of returning {@code null} on a missing key).
	*
	* <P>This class handles directly a default return
	* value (including {@linkplain #defaultReturnValue() methods to access
	* it}). Instances of classes inheriting from this class have just to return
	* <code>defRetValue</code> to denote lack of a key in type-specific methods. The value
	* is serialized.
	*
	* <P>Implementing subclasses have just to provide type-specific <code>get()</code>,
	* type-specific <code>containsKey()</code>, and <code>size()</code> methods.
	*
	*/
public abstract class AbstractDouble2ByteFunction implements Double2ByteFunction , java.io.Serializable {
	private static final long serialVersionUID = -4940583368468432370L;
	protected AbstractDouble2ByteFunction() {}
	/**
	 * The default return value for <code>get()</code>, <code>put()</code> and
	 * <code>remove()</code>.
	 */
	protected byte defRetValue;
	@Override
	public void defaultReturnValue(final byte rv) {
	 defRetValue = rv;
	}
	@Override
	public byte defaultReturnValue() {
	 return defRetValue;
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation always throws an {@link UnsupportedOperationException}.
	 */
	public byte put(double key, byte value) {
	 throw new UnsupportedOperationException();
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation always throws an {@link UnsupportedOperationException}.
	 */
	public byte remove(double key) {
	 throw new UnsupportedOperationException();
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation always throws an {@link UnsupportedOperationException}.
	 */
	public void clear() {
	 throw new UnsupportedOperationException();
	}
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public boolean containsKey(final Object ok) {
	 if (ok == null) return false;
	 return containsKey(((((Double)(ok)).doubleValue())));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method,
	 * taking care of returning {@code null} on a missing key.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Byte get(final Object ok) {
	 if (ok == null) return null;
	 final double k = ((((Double)(ok)).doubleValue()));
	 return containsKey(k) ? (Byte.valueOf(get(k))) : null;
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method,
	 * taking care of returning {@code null} on a missing key.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Byte put(final Double ok, final Byte ov) {
	 final double k = ((ok).doubleValue());
	 final boolean containsKey = containsKey(k);
	 final byte v = put(k, ((ov).byteValue()));
	 return containsKey ? (Byte.valueOf(v)) : null;
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method,
	 * taking care of returning <code>false</code> on a missing key.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Byte remove(final Object ok) {
	 if (ok == null) return null;
	 final double k = ((((Double)(ok)).doubleValue()));
	 final boolean containsKey = containsKey(k);
	 final byte v = remove(k);
	 return containsKey ? (Byte.valueOf(v)) : null;
	}
}

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
package it.unimi.dsi.fastutil.shorts;
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
public abstract class AbstractShort2FloatFunction implements Short2FloatFunction , java.io.Serializable {
	private static final long serialVersionUID = -4940583368468432370L;
	protected AbstractShort2FloatFunction() {}
	/**
	 * The default return value for <code>get()</code>, <code>put()</code> and
	 * <code>remove()</code>.
	 */
	protected float defRetValue;
	@Override
	public void defaultReturnValue(final float rv) {
	 defRetValue = rv;
	}
	@Override
	public float defaultReturnValue() {
	 return defRetValue;
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation always throws an {@link UnsupportedOperationException}.
	 */
	public float put(short key, float value) {
	 throw new UnsupportedOperationException();
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation always throws an {@link UnsupportedOperationException}.
	 */
	public float remove(short key) {
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
	 return containsKey(((((Short)(ok)).shortValue())));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method,
	 * taking care of returning {@code null} on a missing key.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Float get(final Object ok) {
	 if (ok == null) return null;
	 final short k = ((((Short)(ok)).shortValue()));
	 return containsKey(k) ? (Float.valueOf(get(k))) : null;
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method,
	 * taking care of returning {@code null} on a missing key.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Float put(final Short ok, final Float ov) {
	 final short k = ((ok).shortValue());
	 final boolean containsKey = containsKey(k);
	 final float v = put(k, ((ov).floatValue()));
	 return containsKey ? (Float.valueOf(v)) : null;
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the corresponding type-specific method,
	 * taking care of returning <code>false</code> on a missing key.
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	public Float remove(final Object ok) {
	 if (ok == null) return null;
	 final short k = ((((Short)(ok)).shortValue()));
	 final boolean containsKey = containsKey(k);
	 final float v = remove(k);
	 return containsKey ? (Float.valueOf(v)) : null;
	}
}

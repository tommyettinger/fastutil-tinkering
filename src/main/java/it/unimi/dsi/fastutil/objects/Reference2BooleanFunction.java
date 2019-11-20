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
package it.unimi.dsi.fastutil.objects;
import it.unimi.dsi.fastutil.Function;
/** A type-specific {@link Function}; provides some additional methods that use polymorphism to avoid (un)boxing.
	*
	* <P>Type-specific versions of <code>get()</code>, <code>put()</code> and
	* <code>remove()</code> cannot rely on {@code null} to denote absence of
	* a key.  Rather, they return a {@linkplain #defaultReturnValue() default
	* return value}, which is set to 0 cast to the return type (<code>false</code>
	* for booleans) at creation, but can be changed using the
	* <code>defaultReturnValue()</code> method.
	*
	* <P>For uniformity reasons, even maps returning objects implement the default
	* return value (of course, in this case the default return value is
	* initialized to {@code null}).
	*
	* <P><strong>Warning:</strong> to fall in line as much as possible with the
	* {@linkplain java.util.Map standard map interface}, it is strongly suggested
	* that standard versions of <code>get()</code>, <code>put()</code> and
	* <code>remove()</code> for maps with primitive-type values <em>return
	* {@code null} to denote missing keys</em> rather than wrap the default
	* return value in an object (of course, for maps with object keys and values
	* this is not possible, as there is no type-specific version).
	*
	* @see Function
	*/
public interface Reference2BooleanFunction <K> extends Function<K, Boolean> {
	/** Adds a pair to the map (optional operation).
	 *
	 * @param key the key.
	 * @param value the value.
	 * @return the old value, or the {@linkplain #defaultReturnValue() default return value} if no value was present for the given key.
	 * @see Function#put(Object,Object)
	 */
	boolean put(K key, boolean value);
	/** Returns the value to which the given key is mapped.
	 *
	 * @param key the key.
	 * @return the corresponding value, or the {@linkplain #defaultReturnValue() default return value} if no value was present for the given key.
	 * @see Function#get(Object)
	 */
	boolean getBoolean(Object key);
	/** Removes the mapping with the given key (optional operation).
	 * @param key the key.
	 * @return the old value, or the {@linkplain #defaultReturnValue() default return value} if no value was present for the given key.
	 * @see Function#remove(Object)
	 */
	boolean removeBoolean(Object key);
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	Boolean put(K key, Boolean value);
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	Boolean get(Object key);
	/** {@inheritDoc}
	 * @deprecated Please use the corresponding type-specific method instead. */
	@Deprecated
	@Override
	Boolean remove(Object key);
	/** Sets the default return value.
	 *
	 * This value must be returned by type-specific versions of
	 * <code>get()</code>, <code>put()</code> and <code>remove()</code> to
	 * denote that the map does not contain the specified key. It must be
	 * 0/<code>false</code>/{@code null} by default.
	 *
	 * @param rv the new default return value.
	 * @see #defaultReturnValue()
	 */
	void defaultReturnValue(boolean rv);
	/** Gets the default return value.
	 *
	 * @return the current default return value.
	 */
	boolean defaultReturnValue();
}

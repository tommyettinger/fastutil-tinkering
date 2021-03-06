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
/** A class providing static methods and objects that do useful things with type-specific functions.
	*
	* @see it.unimi.dsi.fastutil.Function
	* @see java.util.Collections
	*/
public class Object2DoubleFunctions {
	private Object2DoubleFunctions() {}
	/** An immutable class representing an empty type-specific function.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific function.
	 */
	public static class EmptyFunction <K> extends AbstractObject2DoubleFunction <K> implements java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected EmptyFunction() {}
	 @Override
	 public double getDouble(final Object k) { return (0); }
	 @Override
	 public boolean containsKey(final Object k) { return false; }
	 @Override
	 public double defaultReturnValue() { return (0); }
	 @Override
	 public void defaultReturnValue(final double defRetValue) { throw new UnsupportedOperationException(); }
	 @Override
	 public int size() { return 0; }
	 @Override
	 public void clear() {}
	 @Override
	 public Object clone() { return EMPTY_FUNCTION; }
	 @Override
	 public int hashCode() { return 0; }
	 @Override
	 public boolean equals(final Object o) {
	  if (! (o instanceof Function)) return false;
	  return ((Function<?,?>)o).size() == 0;
	 }
	 @Override
	 public String toString() { return "{}"; }
	 private Object readResolve() { return EMPTY_FUNCTION; }
	}
	/** An empty type-specific function (immutable). It is serializable and cloneable. */
	@SuppressWarnings("rawtypes")
	public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
	/** An immutable class representing a type-specific singleton function.	Note
	 *  that the default return value is still settable.
	 *
	 * <P>Note that albeit the function is immutable, its default return value may be changed.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific function.
	 */
	public static class Singleton <K> extends AbstractObject2DoubleFunction <K> implements java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final K key;
	 protected final double value;
	 protected Singleton(final K key, final double value) {
	  this.key = key;
	  this.value = value;
	 }
	 @Override
	 public boolean containsKey(final Object k) { return ( (key) == null ? (k) == null : (key).equals(k) ); }
	 @Override
	 public double getDouble(final Object k) { return ( (key) == null ? (k) == null : (key).equals(k) ) ? value : defRetValue; }
	 @Override
	 public int size() { return 1; }
	 @Override
	 public Object clone() { return this; }
	}
	/** Returns a type-specific immutable function containing only the specified pair.
	 * The returned function is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned function is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned function.
	 * @param value the only value of the returned function.
	 * @return a type-specific immutable function containing just the pair <code>&lt;key,value&gt;</code>.
	 */
	public static <K> Object2DoubleFunction <K> singleton(final K key, double value) {
	 return new Singleton <K>(key, value);
	}
	/** Returns a type-specific immutable function containing only the specified pair. The returned function is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned function is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned function.
	 * @param value the only value of the returned function.
	 * @return a type-specific immutable function containing just the pair <code>&lt;key,value&gt;</code>.
	 */
	public static <K> Object2DoubleFunction <K> singleton(final K key, final Double value) {
	 return new Singleton <K>((key), ((value).doubleValue()));
	}
	/** A synchronized wrapper class for functions. */
	public static class SynchronizedFunction <K> extends AbstractObject2DoubleFunction <K> implements java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final Object2DoubleFunction <K> function;
	 protected final Object sync;
	 protected SynchronizedFunction(final Object2DoubleFunction <K> f, final Object sync) {
	  if (f == null) throw new NullPointerException();
	  this.function = f;
	  this.sync = sync;
	 }
	 protected SynchronizedFunction(final Object2DoubleFunction <K> f) {
	  if (f == null) throw new NullPointerException();
	  this.function = f;
	  this.sync = this;
	 }
	 @Override
	 public int size() { synchronized(sync) { return function.size(); } }
	 @Override
	 public double defaultReturnValue() { synchronized(sync) { return function.defaultReturnValue(); } }
	 @Override
	 public void defaultReturnValue(final double defRetValue) { synchronized(sync) { function.defaultReturnValue(defRetValue); } }
	 @Override
	 public boolean containsKey(final Object k) { synchronized(sync) { return function.containsKey(k); } }
	 @Override
	 public double put(final K k, final double v) { synchronized(sync) { return function.put(k, v); } }
	 @Override
	 public double getDouble(final Object k) { synchronized(sync) { return function.getDouble(k); } }
	 @Override
	 public double removeDouble(final Object k) { synchronized(sync) { return function.removeDouble(k); } }
	 @Override
	 public void clear() { synchronized(sync) { function.clear(); } }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Double put(final K k, final Double v) { synchronized(sync) { return function.put(k, v); } }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Double get(final Object k) { synchronized(sync) { return function.get(k); } }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Double remove(final Object k) { synchronized(sync) { return function.remove(k); } }
	 @Override
	 public int hashCode() { synchronized(sync) { return function.hashCode(); } }
	 @Override
	 public boolean equals(Object o) { if (o == this) return true; synchronized(sync) { return function.equals(o); } }
	 @Override
	 public String toString() { synchronized(sync) { return function.toString(); } }
	 private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
	  synchronized(sync) { s.defaultWriteObject(); }
	 }
	}
	/** Returns a synchronized type-specific function backed by the given type-specific function.
	 *
	 * @param f the function to be wrapped in a synchronized function.
	 * @return a synchronized view of the specified function.
	 * @see java.util.Collections#synchronizedMap(java.util.Map)
	 */
	public static <K> Object2DoubleFunction <K> synchronize(final Object2DoubleFunction <K> f) { return new SynchronizedFunction <K>(f); }
	/** Returns a synchronized type-specific function backed by the given type-specific function, using an assigned object to synchronize.
	 *
	 * @param f the function to be wrapped in a synchronized function.
	 * @param sync an object that will be used to synchronize the access to the function.
	 * @return a synchronized view of the specified function.
	 * @see java.util.Collections#synchronizedMap(java.util.Map)
	 */
	public static <K> Object2DoubleFunction <K> synchronize(final Object2DoubleFunction <K> f, final Object sync) { return new SynchronizedFunction <K>(f, sync); }
	/** An unmodifiable wrapper class for functions. */
	public static class UnmodifiableFunction <K> extends AbstractObject2DoubleFunction <K> implements java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final Object2DoubleFunction <K> function;
	 protected UnmodifiableFunction(final Object2DoubleFunction <K> f) {
	  if (f == null) throw new NullPointerException();
	  this.function = f;
	 }
	 @Override
	 public int size() { return function.size(); }
	 @Override
	 public double defaultReturnValue() { return function.defaultReturnValue(); }
	 @Override
	 public void defaultReturnValue(final double defRetValue) { throw new UnsupportedOperationException(); }
	 @Override
	 public boolean containsKey(final Object k) { return function.containsKey(k); }
	 @Override
	 public double put(final K k, final double v) { throw new UnsupportedOperationException(); }
	 @Override
	 public double getDouble(final Object k) { return function.getDouble(k); }
	 @Override
	 public double removeDouble(final Object k) { throw new UnsupportedOperationException(); }
	 @Override
	 public void clear() { throw new UnsupportedOperationException(); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Double put(final K k, final Double v) { throw new UnsupportedOperationException(); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Double get(final Object k) { return function.get(k); }
	 /** {@inheritDoc}
		 * @deprecated Please use the corresponding type-specific method instead. */
	 @Deprecated
	 @Override
	 public Double remove(final Object k) { throw new UnsupportedOperationException(); }
	 @Override
	 public int hashCode() { return function.hashCode(); }
	 @Override
	 public boolean equals(Object o) { return o == this || function.equals(o); }
	 @Override
	 public String toString() { return function.toString(); }
	}
	/** Returns an unmodifiable type-specific function backed by the given type-specific function.
	 *
	 * @param f the function to be wrapped in an unmodifiable function.
	 * @return an unmodifiable view of the specified function.
	 * @see java.util.Collections#unmodifiableMap(java.util.Map)
	 */
	public static <K> Object2DoubleFunction <K> unmodifiable(final Object2DoubleFunction <K> f) { return new UnmodifiableFunction <K>(f); }
}

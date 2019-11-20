/*
	* Copyright (C) 2007-2017 Sebastiano Vigna
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
import java.util.Map;
import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.floats.FloatCollections;
import it.unimi.dsi.fastutil.floats.FloatArraySet;
import it.unimi.dsi.fastutil.floats.FloatArrays;
/** A simple, brute-force implementation of a map based on two parallel backing arrays.
	*
	* <p>The main purpose of this
	* implementation is that of wrapping cleanly the brute-force approach to the storage of a very
	* small number of pairs: just put them into two parallel arrays and scan linearly to find an item.
	*/
public class Double2FloatArrayMap extends AbstractDouble2FloatMap implements java.io.Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	/** The keys (valid up to {@link #size}, excluded). */
	private transient double[] key;
	/** The values (parallel to {@link #key}). */
	private transient float[] value;
	/** The number of valid entries in {@link #key} and {@link #value}. */
	private int size;
	/** Creates a new empty array map with given key and value backing arrays. The resulting map will have as many entries as the given arrays.
	 *
	 * <p>It is responsibility of the caller that the elements of <code>key</code> are distinct.
	 *
	 * @param key the key array.
	 * @param value the value array (it <em>must</em> have the same length as <code>key</code>).
	 */
	public Double2FloatArrayMap(final double[] key, final float[] value) {
	 this.key = key;
	 this.value = value;
	 size = key.length;
	 if(key.length != value.length) throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
	}
	/** Creates a new empty array map.
	 */
	public Double2FloatArrayMap() {
	 this.key = DoubleArrays.EMPTY_ARRAY;
	 this.value = FloatArrays.EMPTY_ARRAY;
	}
	/** Creates a new empty array map of given capacity.
	 *
	 * @param capacity the initial capacity.
	 */
	public Double2FloatArrayMap(final int capacity) {
	 this.key = new double[capacity];
	 this.value = new float[capacity];
	}
	/** Creates a new empty array map copying the entries of a given map.
	 *
	 * @param m a map.
	 */
	public Double2FloatArrayMap(final Double2FloatMap m) {
	 this(m.size());
	 putAll(m);
	}
	/** Creates a new empty array map copying the entries of a given map.
	 *
	 * @param m a map.
	 */
	public Double2FloatArrayMap(final Map<? extends Double, ? extends Float> m) {
	 this(m.size());
	 putAll(m);
	}
	/** Creates a new array map with given key and value backing arrays, using the given number of elements.
	 *
	 * <p>It is responsibility of the caller that the first <code>size</code> elements of <code>key</code> are distinct.
	 *
	 * @param key the key array.
	 * @param value the value array (it <em>must</em> have the same length as <code>key</code>).
	 * @param size the number of valid elements in <code>key</code> and <code>value</code>.
	 */
	public Double2FloatArrayMap(final double[] key, final float[] value, final int size) {
	 this.key = key;
	 this.value = value;
	 this.size = size;
	 if(key.length != value.length) throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
	 if (size > key.length) throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the backing-arrays size (" + key.length + ")");
	}
	private final class EntrySet extends AbstractObjectSet<Double2FloatMap.Entry > implements FastEntrySet {
	 @Override
	 public ObjectIterator<Double2FloatMap.Entry > iterator() {
	  return new AbstractObjectIterator<Double2FloatMap.Entry >() {
	   int curr = -1, next = 0;
	   @Override
	   public boolean hasNext() {
	    return next < size;
	   }
	   @Override
	  
	   public Entry next() {
	    if (! hasNext()) throw new NoSuchElementException();
	    return new AbstractDouble2FloatMap.BasicEntry ( key[curr = next], value[next++]);
	   }
	   @Override
	   public void remove() {
	    if (curr == -1) throw new IllegalStateException();
	    curr = -1;
	    final int tail = size-- - next--;
	    System.arraycopy(key, next + 1, key, next, tail);
	    System.arraycopy(value, next + 1, value, next, tail);
	   }
	  };
	 }
	 @Override
	 public ObjectIterator<Double2FloatMap.Entry > fastIterator() {
	  return new AbstractObjectIterator<Double2FloatMap.Entry >() {
	   int next = 0, curr = -1;
	   final BasicEntry entry = new BasicEntry ((0), (0));
	   @Override
	   public boolean hasNext() {
	    return next < size;
	   }
	   @Override
	  
	   public Entry next() {
	    if (! hasNext()) throw new NoSuchElementException();
	    entry.key = key[curr = next];
	    entry.value = value[next++];
	    return entry;
	   }
	   @Override
	   public void remove() {
	    if (curr == -1) throw new IllegalStateException();
	    curr = -1;
	    final int tail = size-- - next--;
	    System.arraycopy(key, next + 1, key, next, tail);
	    System.arraycopy(value, next + 1, value, next, tail);
	   }
	  };
	 }
	 @Override
	 public int size() {
	  return size;
	 }
	 @Override
	
	 public boolean contains(Object o) {
	  if (! (o instanceof Map.Entry)) return false;
	  final Map.Entry<?,?> e = (Map.Entry<?,?>)o;
	  if (e.getKey() == null || ! (e.getKey() instanceof Double)) return false;
	  if (e.getValue() == null || ! (e.getValue() instanceof Float)) return false;
	  final double k = ((((Double)( e.getKey())).doubleValue()));
	  return Double2FloatArrayMap.this.containsKey(k) && ( (Double2FloatArrayMap.this.get(k)) == (((((Float)(e.getValue())).floatValue()))) );
	 }
	 @Override
	
	 public boolean remove(final Object o) {
	  if (!(o instanceof Map.Entry)) return false;
	  final Map.Entry<?,?> e = (Map.Entry<?,?>)o;
	  if (e.getKey() == null || ! (e.getKey() instanceof Double)) return false;
	  if (e.getValue() == null || ! (e.getValue() instanceof Float)) return false;
	  final double k = ((((Double)( e.getKey())).doubleValue()));
	  final float v = ((((Float)( e.getValue())).floatValue()));
	  final int oldPos = Double2FloatArrayMap.this.findKey(k);
	  if (oldPos == -1 || ! ( (v) == (Double2FloatArrayMap.this.value[oldPos]) )) return false;
	  final int tail = size - oldPos - 1;
	  System.arraycopy(Double2FloatArrayMap.this.key, oldPos + 1, Double2FloatArrayMap.this.key, oldPos, tail);
	  System.arraycopy(Double2FloatArrayMap.this.value, oldPos + 1, Double2FloatArrayMap.this.value, oldPos, tail);
	  Double2FloatArrayMap.this.size--;
	  return true;
	 }
	}
	@Override
	public FastEntrySet double2FloatEntrySet() {
	 return new EntrySet();
	}
	private int findKey(final double k) {
	 final double[] key = this.key;
	 for(int i = size; i-- != 0;) if (( Double.doubleToLongBits(key[i]) == Double.doubleToLongBits(k) )) return i;
	 return -1;
	}
	@Override

	public float get(final double k) {
	 final double[] key = this.key;
	 for(int i = size; i-- != 0;) if (( Double.doubleToLongBits(key[i]) == Double.doubleToLongBits(k) )) return value[i];
	 return defRetValue;
	}
	@Override
	public int size() {
	 return size;
	}
	@Override
	public void clear() {
	 size = 0;
	}
	@Override
	public boolean containsKey(final double k) {
	 return findKey(k) != -1;
	}
	@Override
	public boolean containsValue(float v) {
	 for(int i = size; i-- != 0;) if (( (value[i]) == (v) )) return true;
	 return false;
	}
	@Override
	public boolean isEmpty() {
	 return size == 0;
	}
	@Override

	public float put(double k, float v) {
	 final int oldKey = findKey(k);
	 if (oldKey != -1) {
	  final float oldValue = value[oldKey];
	  value[oldKey] = v;
	  return oldValue;
	 }
	 if (size == key.length) {
	  final double[] newKey = new double[size == 0 ? 2 : size * 2];
	  final float[] newValue = new float[size == 0 ? 2 : size * 2];
	  for(int i = size; i-- != 0;) {
	   newKey[i] = key[i];
	   newValue[i] = value[i];
	  }
	  key = newKey;
	  value = newValue;
	 }
	 key[size] = k;
	 value[size] = v;
	 size++;
	 return defRetValue;
	}
	@Override

	public float remove(final double k) {
	 final int oldPos = findKey(k);
	 if (oldPos == -1) return defRetValue;
	 final float oldValue = value[oldPos];
	 final int tail = size - oldPos - 1;
	 System.arraycopy(key, oldPos + 1, key, oldPos, tail);
	 System.arraycopy(value, oldPos + 1, value, oldPos, tail);
	 size--;
	 return oldValue;
	}
	@Override
	public DoubleSet keySet() {
	 return new DoubleArraySet (key, size);
	}
	@Override
	public FloatCollection values() {
	 return FloatCollections.unmodifiable(new FloatArraySet (value, size));
	}
	/** Returns a deep copy of this map.
	 *
	 * <P>This method performs a deep copy of this hash map; the data stored in the
	 * map, however, is not cloned. Note that this makes a difference only for object keys.
	 *
	 *  @return a deep copy of this map.
	 */
	@Override

	public Double2FloatArrayMap clone() {
	 Double2FloatArrayMap c;
	 try {
	  c = (Double2FloatArrayMap )super.clone();
	 }
	 catch(CloneNotSupportedException cantHappen) {
	  throw new InternalError();
	 }
	 c.key = key.clone();
	 c.value = value.clone();
	 return c;
	}
	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
	 s.defaultWriteObject();
	 for(int i = 0; i < size; i++) {
	  s.writeDouble(key[i]);
	  s.writeFloat(value[i]);
	 }
	}
	private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
	 s.defaultReadObject();
	 key = new double[size];
	 value = new float[size];
	 for(int i = 0; i < size; i++) {
	  key[i] = s.readDouble();
	  value[i] = s.readFloat();
	 }
	}
}
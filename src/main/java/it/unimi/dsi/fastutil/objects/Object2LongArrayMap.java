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
package it.unimi.dsi.fastutil.objects;
import java.util.Map;
import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongCollections;
import it.unimi.dsi.fastutil.longs.LongArraySet;
import it.unimi.dsi.fastutil.longs.LongArrays;
/** A simple, brute-force implementation of a map based on two parallel backing arrays.
	*
	* <p>The main purpose of this
	* implementation is that of wrapping cleanly the brute-force approach to the storage of a very
	* small number of pairs: just put them into two parallel arrays and scan linearly to find an item.
	*/
public class Object2LongArrayMap <K> extends AbstractObject2LongMap <K> implements java.io.Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	/** The keys (valid up to {@link #size}, excluded). */
	private transient Object[] key;
	/** The values (parallel to {@link #key}). */
	private transient long[] value;
	/** The number of valid entries in {@link #key} and {@link #value}. */
	private int size;
	/** Creates a new empty array map with given key and value backing arrays. The resulting map will have as many entries as the given arrays.
	 *
	 * <p>It is responsibility of the caller that the elements of <code>key</code> are distinct.
	 *
	 * @param key the key array.
	 * @param value the value array (it <em>must</em> have the same length as <code>key</code>).
	 */
	public Object2LongArrayMap(final Object[] key, final long[] value) {
	 this.key = key;
	 this.value = value;
	 size = key.length;
	 if(key.length != value.length) throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
	}
	/** Creates a new empty array map.
	 */
	public Object2LongArrayMap() {
	 this.key = ObjectArrays.EMPTY_ARRAY;
	 this.value = LongArrays.EMPTY_ARRAY;
	}
	/** Creates a new empty array map of given capacity.
	 *
	 * @param capacity the initial capacity.
	 */
	public Object2LongArrayMap(final int capacity) {
	 this.key = new Object[capacity];
	 this.value = new long[capacity];
	}
	/** Creates a new empty array map copying the entries of a given map.
	 *
	 * @param m a map.
	 */
	public Object2LongArrayMap(final Object2LongMap <K> m) {
	 this(m.size());
	 putAll(m);
	}
	/** Creates a new empty array map copying the entries of a given map.
	 *
	 * @param m a map.
	 */
	public Object2LongArrayMap(final Map<? extends K, ? extends Long> m) {
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
	public Object2LongArrayMap(final Object[] key, final long[] value, final int size) {
	 this.key = key;
	 this.value = value;
	 this.size = size;
	 if(key.length != value.length) throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
	 if (size > key.length) throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the backing-arrays size (" + key.length + ")");
	}
	private final class EntrySet extends AbstractObjectSet<Object2LongMap.Entry <K> > implements FastEntrySet <K> {
	 @Override
	 public ObjectIterator<Object2LongMap.Entry <K> > iterator() {
	  return new AbstractObjectIterator<Object2LongMap.Entry <K> >() {
	   int curr = -1, next = 0;
	   @Override
	   public boolean hasNext() {
	    return next < size;
	   }
	   @Override
	   @SuppressWarnings("unchecked")
	   public Entry <K> next() {
	    if (! hasNext()) throw new NoSuchElementException();
	    return new AbstractObject2LongMap.BasicEntry <K>((K) key[curr = next], value[next++]);
	   }
	   @Override
	   public void remove() {
	    if (curr == -1) throw new IllegalStateException();
	    curr = -1;
	    final int tail = size-- - next--;
	    System.arraycopy(key, next + 1, key, next, tail);
	    System.arraycopy(value, next + 1, value, next, tail);
	    key[size] = null;
	   }
	  };
	 }
	 @Override
	 public ObjectIterator<Object2LongMap.Entry <K> > fastIterator() {
	  return new AbstractObjectIterator<Object2LongMap.Entry <K> >() {
	   int next = 0, curr = -1;
	   final BasicEntry <K> entry = new BasicEntry <K> ((null), (0));
	   @Override
	   public boolean hasNext() {
	    return next < size;
	   }
	   @Override
	   @SuppressWarnings("unchecked")
	   public Entry <K> next() {
	    if (! hasNext()) throw new NoSuchElementException();
	    entry.key = (K) key[curr = next];
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
	    key[size] = null;
	   }
	  };
	 }
	 @Override
	 public int size() {
	  return size;
	 }
	 @Override
	 @SuppressWarnings("unchecked")
	 public boolean contains(Object o) {
	  if (! (o instanceof Map.Entry)) return false;
	  final Map.Entry<?,?> e = (Map.Entry<?,?>)o;
	  if (e.getValue() == null || ! (e.getValue() instanceof Long)) return false;
	  final K k = ((K) e.getKey());
	  return Object2LongArrayMap.this.containsKey(k) && ( (Object2LongArrayMap.this.getLong(k)) == (((((Long)(e.getValue())).longValue()))) );
	 }
	 @Override
	 @SuppressWarnings("unchecked")
	 public boolean remove(final Object o) {
	  if (!(o instanceof Map.Entry)) return false;
	  final Map.Entry<?,?> e = (Map.Entry<?,?>)o;
	  if (e.getValue() == null || ! (e.getValue() instanceof Long)) return false;
	  final K k = ((K) e.getKey());
	  final long v = ((((Long)( e.getValue())).longValue()));
	  final int oldPos = Object2LongArrayMap.this.findKey(k);
	  if (oldPos == -1 || ! ( (v) == (Object2LongArrayMap.this.value[oldPos]) )) return false;
	  final int tail = size - oldPos - 1;
	  System.arraycopy(Object2LongArrayMap.this.key, oldPos + 1, Object2LongArrayMap.this.key, oldPos, tail);
	  System.arraycopy(Object2LongArrayMap.this.value, oldPos + 1, Object2LongArrayMap.this.value, oldPos, tail);
	  Object2LongArrayMap.this.size--;
	  Object2LongArrayMap.this.key[size] = null;
	  return true;
	 }
	}
	@Override
	public FastEntrySet <K> object2LongEntrySet() {
	 return new EntrySet();
	}
	private int findKey(final Object k) {
	 final Object[] key = this.key;
	 for(int i = size; i-- != 0;) if (( (key[i]) == null ? (k) == null : (key[i]).equals(k) )) return i;
	 return -1;
	}
	@Override

	public long getLong(final Object k) {
	 final Object[] key = this.key;
	 for(int i = size; i-- != 0;) if (( (key[i]) == null ? (k) == null : (key[i]).equals(k) )) return value[i];
	 return defRetValue;
	}
	@Override
	public int size() {
	 return size;
	}
	@Override
	public void clear() {
	 for(int i = size; i-- != 0;) {
	  key[i] = null;
	 }
	 size = 0;
	}
	@Override
	public boolean containsKey(final Object k) {
	 return findKey(k) != -1;
	}
	@Override
	public boolean containsValue(long v) {
	 for(int i = size; i-- != 0;) if (( (value[i]) == (v) )) return true;
	 return false;
	}
	@Override
	public boolean isEmpty() {
	 return size == 0;
	}
	@Override

	public long put(K k, long v) {
	 final int oldKey = findKey(k);
	 if (oldKey != -1) {
	  final long oldValue = value[oldKey];
	  value[oldKey] = v;
	  return oldValue;
	 }
	 if (size == key.length) {
	  final Object[] newKey = new Object[size == 0 ? 2 : size * 2];
	  final long[] newValue = new long[size == 0 ? 2 : size * 2];
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

	public long removeLong(final Object k) {
	 final int oldPos = findKey(k);
	 if (oldPos == -1) return defRetValue;
	 final long oldValue = value[oldPos];
	 final int tail = size - oldPos - 1;
	 System.arraycopy(key, oldPos + 1, key, oldPos, tail);
	 System.arraycopy(value, oldPos + 1, value, oldPos, tail);
	 size--;
	 key[size] = null;
	 return oldValue;
	}
	@Override
	public ObjectSet <K> keySet() {
	 return new ObjectArraySet <K>(key, size);
	}
	@Override
	public LongCollection values() {
	 return LongCollections.unmodifiable(new LongArraySet (value, size));
	}
	/** Returns a deep copy of this map.
	 *
	 * <P>This method performs a deep copy of this hash map; the data stored in the
	 * map, however, is not cloned. Note that this makes a difference only for object keys.
	 *
	 *  @return a deep copy of this map.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object2LongArrayMap <K> clone() {
	 Object2LongArrayMap <K> c;
	 try {
	  c = (Object2LongArrayMap <K>)super.clone();
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
	  s.writeObject(key[i]);
	  s.writeLong(value[i]);
	 }
	}
	private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
	 s.defaultReadObject();
	 key = new Object[size];
	 value = new long[size];
	 for(int i = 0; i < size; i++) {
	  key[i] = s.readObject();
	  value[i] = s.readLong();
	 }
	}
}

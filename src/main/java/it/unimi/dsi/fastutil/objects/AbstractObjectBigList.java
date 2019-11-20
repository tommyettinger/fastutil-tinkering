/*
	* Copyright (C) 2010-2017 Sebastiano Vigna
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
import it.unimi.dsi.fastutil.Stack;
import java.util.Iterator;
import java.util.Collection;
import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
/**  An abstract class providing basic methods for big lists implementing a type-specific big list interface. */
public abstract class AbstractObjectBigList <K> extends AbstractObjectCollection <K> implements ObjectBigList <K>, Stack <K> {
	protected AbstractObjectBigList() {}
	/** Ensures that the given index is nonnegative and not greater than this big-list size.
	 *
	 * @param index an index.
	 * @throws IndexOutOfBoundsException if the given index is negative or greater than this big-list size.
	 */
	protected void ensureIndex(final long index) {
	 if (index < 0) throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
	 if (index > size64()) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than list size (" + (size64()) + ")");
	}
	/** Ensures that the given index is nonnegative and smaller than this big-list size.
	 *
	 * @param index an index.
	 * @throws IndexOutOfBoundsException if the given index is negative or not smaller than this big-list size.
	 */
	protected void ensureRestrictedIndex(final long index) {
	 if (index < 0) throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
	 if (index >= size64()) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + (size64()) + ")");
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation always throws an {@link UnsupportedOperationException}.
	 */
	@Override
	public void add(final long index, final K k) {
	 throw new UnsupportedOperationException();
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation delegates to the type-specific version of {@link BigList#add(long, Object)}.
	 */
	@Override
	public boolean add(final K k) {
	 add(size64(), k);
	 return true;
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation always throws an {@link UnsupportedOperationException}.
	 */
	@Override
	public K remove(long i) {
	 throw new UnsupportedOperationException();
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation delegates to the type-specific version of {@link BigList#remove(long)}.
	 * @deprecated This method should have never been implemented, as it is not
	 * part of {@link BigList}. It will be removed in the future.
	 */
	@Deprecated
	public K remove(int i) {
	 return remove((long)i);
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation always throws an {@link UnsupportedOperationException}.
	 */
	@Override
	public K set(final long index, final K k) {
	 throw new UnsupportedOperationException();
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation delegates to the type-specific version of {@link BigList#set(long, Object)}.
	 * @deprecated This method should have never been implemented, as it is not
	 * part of {@link BigList}. It will be removed in the future.
	 */
	@Deprecated
	public K set(final int index, final K k) {
	 return set((long)index, k);
	}
	/** Adds all of the elements in the specified collection to this list (optional operation). */
	@Override
	public boolean addAll(long index, final Collection<? extends K> c) {
	 ensureIndex(index);
	 final Iterator<? extends K> i = c.iterator();
	 final boolean retVal = i.hasNext();
	 while(i.hasNext()) add(index++, i.next());
	 return retVal;
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation delegates to the type-specific version of {@link BigList#addAll(long, Collection)}.
	 * @deprecated This method should have never been implemented, as it is not
	 * part of {@link BigList}. It will be removed in the future.
	 */
	@Deprecated
	public boolean addAll(int index, final Collection<? extends K> c) {
	 return addAll((long)index, c);
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation delegates to the type-specific version of {@link BigList#addAll(long, Collection)}.
	 */
	public boolean addAll(final Collection<? extends K> c) {
	 return addAll(size64(), c);
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation delegates to {@link #listIterator()}.
	 */
	@Override
	public ObjectBigListIterator <K> iterator() {
	 return listIterator();
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation delegates to {@link BigList#listIterator(long) listIterator(0)}.
	 */
	@Override
	public ObjectBigListIterator <K> listIterator() {
	 return listIterator(0L);
	}
	/** {@inheritDoc}
	 * <P>This implementation is based on the random-access methods. */
	@Override
	public ObjectBigListIterator <K> listIterator(final long index) {
	 ensureIndex(index);
	 return new AbstractObjectBigListIterator <K>() {
	   long pos = index, last = -1;
	   @Override
	   public boolean hasNext() { return pos < AbstractObjectBigList.this.size64(); }
	   @Override
	   public boolean hasPrevious() { return pos > 0; }
	   @Override
	   public K next() { if (! hasNext()) throw new NoSuchElementException(); return AbstractObjectBigList.this.get(last = pos++); }
	   @Override
	   public K previous() { if (! hasPrevious()) throw new NoSuchElementException(); return AbstractObjectBigList.this.get(last = --pos); }
	   @Override
	   public long nextIndex() { return pos; }
	   @Override
	   public long previousIndex() { return pos - 1; }
	   @Override
	   public void add(K k) {
	    AbstractObjectBigList.this.add(pos++, k);
	    last = -1;
	   }
	   @Override
	   public void set(K k) {
	    if (last == -1) throw new IllegalStateException();
	    AbstractObjectBigList.this.set(last, k);
	   }
	   @Override
	   public void remove() {
	    if (last == -1) throw new IllegalStateException();
	    AbstractObjectBigList.this.remove(last);
	    /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
	    if (last < pos) pos--;
	    last = -1;
	   }
	  };
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation delegates to {@link BigList#listIterator(long)}.
	 * @deprecated This method should have never been implemented, as it is not
	 * part of {@link BigList}. It will be removed in the future.
	 */
	@Deprecated
	public ObjectBigListIterator <K> listIterator(final int index) {
	 return listIterator((long)index);
	}
	/** Returns true if this list contains the specified element.
	 * <p>This implementation delegates to {@code indexOf()}.
	 * @see BigList#contains(Object)
	 */
	@Override
	public boolean contains(final Object k) {
	 return indexOf(k) >= 0;
	}
	@Override
	public long indexOf(final Object k) {
	 final ObjectBigListIterator <K> i = listIterator();
	 K e;
	 while(i.hasNext()) {
	  e = i.next();
	  if (( (k) == null ? (e) == null : (k).equals(e) )) return i.previousIndex();
	 }
	 return -1;
	}
	@Override
	public long lastIndexOf(final Object k) {
	 ObjectBigListIterator <K> i = listIterator(size64());
	 K e;
	 while(i.hasPrevious()) {
	  e = i.previous();
	  if (( (k) == null ? (e) == null : (k).equals(e) )) return i.nextIndex();
	 }
	 return -1;
	}
	@Override
	public void size(final long size) {
	 long i = size64();
	 if (size > i) while(i++ < size) add((null));
	 else while(i-- != size) remove(i);
	}
	/**
	 * <p>This implementation delegates to {@link BigList#size(long)}.
	 * @deprecated This method should have never been implemented, as it is not
	 * part of {@link BigList}. It will be removed in the future.
	 */
	@Deprecated
	public void size(final int size) {
	 size((long)size);
	}
	@Override
	public ObjectBigList <K> subList(final long from, final long to) {
	 ensureIndex(from);
	 ensureIndex(to);
	 if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
	 return new ObjectSubList <K>(this, from, to);
	}
	/** {@inheritDoc}
	 *
	 * <P>This is a trivial iterator-based implementation. It is expected that
	 * implementations will override this method with a more optimized version.
	 */
	@Override
	public void removeElements(final long from, final long to) {
	 ensureIndex(to);
	 ObjectBigListIterator <K> i = listIterator(from);
	 long n = to - from;
	 if (n < 0) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
	 while(n-- != 0) {
	  i.next();
	  i.remove();
	 }
	}
	/** {@inheritDoc}
	 *
	 * <P>This is a trivial iterator-based implementation. It is expected that
	 * implementations will override this method with a more optimized version.
	 */
	@Override
	public void addElements(long index, final K a[][], long offset, long length) {
	 ensureIndex(index);
	 ObjectBigArrays.ensureOffsetLength(a, offset, length);
	 while(length-- != 0) add(index++, ObjectBigArrays.get(a, offset++));
	}
	/** {@inheritDoc}
	 *
	 * <P>This implementation delegates to the analogous method for big-array fragments.
	 */
	@Override
	public void addElements(final long index, final K a[][]) {
	 addElements(index, a, 0, ObjectBigArrays.length(a));
	}
	/** {@inheritDoc}
	 *
	 * <P>This is a trivial iterator-based implementation. It is expected that
	 * implementations will override this method with a more optimized version.
	 */
	@Override
	public void getElements(final long from, final Object a[][], long offset, long length) {
	 ObjectBigListIterator <K> i = listIterator(from);
	 ObjectBigArrays.ensureOffsetLength(a, offset, length);
	 if (from + length > size64()) throw new IndexOutOfBoundsException("End index (" + (from + length) + ") is greater than list size (" + size64() + ")");
	 while(length-- != 0) ObjectBigArrays.set(a, offset++, i.next());
	}
	/** {@inheritDoc}
	 * <P>This implementation delegates to {@link #removeElements(long, long)}.*/
	@Override
	public void clear() {
	 removeElements(0, size64());
	}
	/** {@inheritDoc}
	 *
	 * <P>This implementation delegates to {@link #size64()}.
	 * @deprecated Please use the {@link #size64()} instead. */
	@Override
	@Deprecated
	public int size() {
	 return (int)Math.min(Integer.MAX_VALUE, size64());
	}
	private boolean valEquals(final Object a, final Object b) {
	 return a == null ? b == null : a.equals(b);
	}
	/** Returns the hash code for this big list, which is identical to {@link java.util.List#hashCode()}.
	 *
	 * @return the hash code for this big list.
	 */
	@Override
	public int hashCode() {
	 ObjectIterator <K> i = iterator();
	 int h = 1;
	 long s = size64();
	 while (s-- != 0) {
	  K k = i.next();
	  h = 31 * h + ( (k) == null ? 0 : (k).hashCode() );
	 }
	 return h;
	}
	@Override
	public boolean equals(final Object o) {
	 if (o == this) return true;
	 if (! (o instanceof BigList)) return false;
	 final BigList<?> l = (BigList<?>)o;
	 long s = size64();
	 if (s != l.size64()) return false;
	 final BigListIterator<?> i1 = listIterator(), i2 = l.listIterator();
	 while(s-- != 0) if (! valEquals(i1.next(), i2.next())) return false;
	 return true;
	}
	/** Compares this big list to another object. If the
	 * argument is a {@link BigList}, this method performs a lexicographical comparison; otherwise,
	 * it throws a <code>ClassCastException</code>.
	 *
	 * @param l a big list.
	 * @return if the argument is a {@link BigList}, a negative integer,
	 * zero, or a positive integer as this list is lexicographically less than, equal
	 * to, or greater than the argument.
	 * @throws ClassCastException if the argument is not a big list.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(final BigList<? extends K> l) {
	 if (l == this) return 0;
	 if (l instanceof ObjectBigList) {
	  final ObjectBigListIterator <K> i1 = listIterator(), i2 = ((ObjectBigList <K>)l).listIterator();
	  int r;
	  K e1, e2;
	  while(i1.hasNext() && i2.hasNext()) {
	   e1 = i1.next();
	   e2 = i2.next();
	   if ((r = ( ((Comparable<K>)(e1)).compareTo(e2) )) != 0) return r;
	  }
	  return i2.hasNext() ? -1 : (i1.hasNext() ? 1 : 0);
	 }
	 BigListIterator<? extends K> i1 = listIterator(), i2 = l.listIterator();
	 int r;
	 while(i1.hasNext() && i2.hasNext()) {
	  if ((r = ((Comparable<? super K>)i1.next()).compareTo(i2.next())) != 0) return r;
	 }
	 return i2.hasNext() ? -1 : (i1.hasNext() ? 1 : 0);
	}
	@Override
	public void push(K o) {
	 add(o);
	}
	@Override
	public K pop() {
	 if (isEmpty()) throw new NoSuchElementException();
	 return remove(size64() - 1);
	}
	@Override
	public K top() {
	 if (isEmpty()) throw new NoSuchElementException();
	 return get(size64() - 1);
	}
	@Override
	public K peek(int i) {
	 return get(size64() - 1 - i);
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation delegates to the type-specific version of {@link BigList#get(long)}.
	 * @deprecated This method should have never been implemented, as it is not
	 * part of {@link BigList}. It will be removed in the future.
	 */
	@Deprecated
	public K get(final int index) {
	 return get((long)index);
	}
	@Override
	public String toString() {
	 final StringBuilder s = new StringBuilder();
	 final ObjectIterator <K> i = iterator();
	 long n = size64();
	 K k;
	 boolean first = true;
	 s.append("[");
	 while(n-- != 0) {
	  if (first) first = false;
	  else s.append(", ");
	  k = i.next();
	  if (this == k) s.append("(this big list)"); else
	   s.append(String.valueOf(k));
	 }
	 s.append("]");
	 return s.toString();
	}
	/** A class implementing a sublist view. */
	public static class ObjectSubList <K> extends AbstractObjectBigList <K> implements java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 /** The list this sublist restricts. */
	 protected final ObjectBigList <K> l;
	 /** Initial (inclusive) index of this sublist. */
	 protected final long from;
	 /** Final (exclusive) index of this sublist. */
	 protected long to;
	 public ObjectSubList(final ObjectBigList <K> l, final long from, final long to) {
	  this.l = l;
	  this.from = from;
	  this.to = to;
	 }
	 private boolean assertRange() {
	  assert from <= l.size64();
	  assert to <= l.size64();
	  assert to >= from;
	  return true;
	 }
	 @Override
	 public boolean add(final K k) {
	  l.add(to, k);
	  to++;
	  assert assertRange();
	  return true;
	 }
	 @Override
	 public void add(final long index, final K k) {
	  ensureIndex(index);
	  l.add(from + index, k);
	  to++;
	  assert assertRange();
	 }
	 @Override
	 public boolean addAll(final long index, final Collection<? extends K> c) {
	  ensureIndex(index);
	  to += c.size();
	  return l.addAll(from + index, c);
	 }
	 @Override
	 public K get(long index) {
	  ensureRestrictedIndex(index);
	  return l.get(from + index);
	 }
	 @Override
	 public K remove(long index) {
	  ensureRestrictedIndex(index);
	  to--;
	  return l.remove(from + index);
	 }
	 @Override
	 public K set(long index, K k) {
	  ensureRestrictedIndex(index);
	  return l.set(from + index, k);
	 }
	 @Override
	 public long size64() {
	  return to - from;
	 }
	 @Override
	 public void getElements(final long from, final Object[][] a, final long offset, final long length) {
	  ensureIndex(from);
	  if (from + length > size64()) throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size64() + ")");
	  l.getElements(this.from + from, a, offset, length);
	 }
	 @Override
	 public void removeElements(final long from, final long to) {
	  ensureIndex(from);
	  ensureIndex(to);
	  l.removeElements(this.from + from, this.from + to);
	  this.to -= (to - from);
	  assert assertRange();
	 }
	 @Override
	 public void addElements(final long index, final K a[][], long offset, long length) {
	  ensureIndex(index);
	  l.addElements(this.from + index, a, offset, length);
	  this.to += length;
	  assert assertRange();
	 }
	 @Override
	 public ObjectBigListIterator <K> listIterator(final long index) {
	  ensureIndex(index);
	  return new AbstractObjectBigListIterator <K>() {
	    long pos = index, last = -1;
	    public boolean hasNext() { return pos < size64(); }
	    public boolean hasPrevious() { return pos > 0; }
	    public K next() { if (! hasNext()) throw new NoSuchElementException(); return l.get(from + (last = pos++)); }
	    public K previous() { if (! hasPrevious()) throw new NoSuchElementException(); return l.get(from + (last = --pos)); }
	    public long nextIndex() { return pos; }
	    public long previousIndex() { return pos - 1; }
	    public void add(K k) {
	     if (last == -1) throw new IllegalStateException();
	     ObjectSubList.this.add(pos++, k);
	     last = -1;
	     assert assertRange();
	    }
	    public void set(K k) {
	     if (last == -1) throw new IllegalStateException();
	     ObjectSubList.this.set(last, k);
	    }
	    public void remove() {
	     if (last == -1) throw new IllegalStateException();
	     ObjectSubList.this.remove(last);
	     /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
	     if (last < pos) pos--;
	     last = -1;
	     assert assertRange();
	    }
	   };
	 }
	 @Override
	 public ObjectBigList <K> subList(final long from, final long to) {
	  ensureIndex(from);
	  ensureIndex(to);
	  if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
	  return new ObjectSubList <K>(this, from, to);
	 }
	}
}

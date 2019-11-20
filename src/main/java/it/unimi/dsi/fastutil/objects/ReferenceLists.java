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
import java.util.List;
import java.util.Collection;
import java.util.Random;
import java.util.RandomAccess;
/** A class providing static methods and objects that do useful things with type-specific lists.
	*
	* @see java.util.Collections
	*/
public class ReferenceLists {
	private ReferenceLists() {}
	/** Shuffles the specified list using the specified pseudorandom number generator.
	 *
	 * @param l the list to be shuffled.
	 * @param random a pseudorandom number generator (please use a <a href="http://dsiutils.dsi.unimi.it/docs/it/unimi/dsi/util/XorShiftStarRandom.html">XorShift*</a> generator).
	 * @return <code>l</code>.
	 */
	public static <K> ReferenceList <K> shuffle(final ReferenceList <K> l, final Random random) {
	 for(int i = l.size(); i-- != 0;) {
	  final int p = random.nextInt(i + 1);
	  final K t = l.get(i);
	  l.set(i, l.get(p));
	  l.set(p, t);
	 }
	 return l;
	}
	/** An immutable class representing an empty type-specific list.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific list.
	 */
	public static class EmptyList <K> extends ReferenceCollections.EmptyCollection <K> implements ReferenceList <K>, RandomAccess, java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected EmptyList() {}
	 @Override
	 public K get(int i) { throw new IndexOutOfBoundsException(); }
	 @Override
	 public boolean remove(Object k) { throw new UnsupportedOperationException(); }
	 @Override
	 public K remove(int i) { throw new UnsupportedOperationException(); }
	 @Override
	 public void add(final int index, final K k) { throw new UnsupportedOperationException(); }
	 @Override
	 public K set(final int index, final K k) { throw new UnsupportedOperationException(); }
	 @Override
	 public int indexOf(Object k) { return -1; }
	 @Override
	 public int lastIndexOf(Object k) { return -1; }
	 @Override
	 public boolean addAll(int i, Collection<? extends K> c) { throw new UnsupportedOperationException(); }
	 @SuppressWarnings("unchecked")
	 public ObjectListIterator <K> listIterator() { return ObjectIterators.EMPTY_ITERATOR; }
	 @SuppressWarnings("unchecked")
	 public ObjectListIterator <K> iterator() { return ObjectIterators.EMPTY_ITERATOR; }
	 @SuppressWarnings("unchecked")
	 public ObjectListIterator <K> listIterator(int i) { if (i == 0) return ObjectIterators.EMPTY_ITERATOR; throw new IndexOutOfBoundsException(String.valueOf(i)); }
	 /** {@inheritDoc}
		 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #listIterator()}. */
	 @Deprecated
	 @Override
	 public ObjectListIterator <K> objectListIterator() { return listIterator(); }
	 /** {@inheritDoc}
		 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #listIterator(int)}. */
	 @Deprecated
	 @Override
	 public ObjectListIterator <K> objectListIterator(int i) { return listIterator(i); }
	 public ReferenceList <K> subList(int from, int to) { if (from == 0 && to == 0) return this; throw new IndexOutOfBoundsException(); }
	 /* {@inheritDoc}
		 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #subList(int,int)}.
		 */
	 @Deprecated
	 @Override
	 public ReferenceList <K> referenceSubList(int from, int to) { return subList(from, to); }
	 @Override
	 public void getElements(int from, Object[] a, int offset, int length) { if (from == 0 && length == 0 && offset >= 0 && offset <= a.length) return; throw new IndexOutOfBoundsException(); }
	 @Override
	 public void removeElements(int from, int to) { throw new UnsupportedOperationException(); }
	 @Override
	 public void addElements(int index, final K a[], int offset, int length) { throw new UnsupportedOperationException(); }
	 @Override
	 public void addElements(int index, final K a[]) { throw new UnsupportedOperationException(); }
	 @Override
	 public void size(int s) { throw new UnsupportedOperationException(); }
	 @Override
	 public Object clone() { return EMPTY_LIST; }
	 @Override
	 public int hashCode() { return 1; }
	 @Override
	 @SuppressWarnings("rawtypes")
	 public boolean equals(Object o) { return o instanceof List && ((List)o).isEmpty(); }
	 @Override
	 public String toString() { return "[]"; }
	 private Object readResolve() { return EMPTY_LIST; }
	}
	/** An empty list (immutable). It is serializable and cloneable.
	 */
	@SuppressWarnings("rawtypes")
	public static final EmptyList EMPTY_LIST = new EmptyList();
	/** Returns an empty list (immutable). It is serializable and cloneable.
	 *
	 * <P>This method provides a typesafe access to {@link #EMPTY_LIST}.
	 * @return an empty list (immutable).
	 */
	@SuppressWarnings("unchecked")
	public static <K> ReferenceList <K> emptyList() {
	 return EMPTY_LIST;
	}
	/** An immutable class representing a type-specific singleton list.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific list.
	 */
	public static class Singleton <K> extends AbstractReferenceList <K> implements RandomAccess, java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 private final K element;
	 private Singleton(final K element) {
	  this.element = element;
	 }
	 @Override
	 public K get(final int i) { if (i == 0) return element; throw new IndexOutOfBoundsException(); }
	 @Override
	 public boolean remove(Object k) { throw new UnsupportedOperationException(); }
	 @Override
	 public K remove(final int i) { throw new UnsupportedOperationException(); }
	 @Override
	 public boolean contains(final Object k) { return ( (k) == (element) ); }
	 /* Slightly optimized w.r.t. the one in ABSTRACT_SET. */
	 @Override
	 public Object[] toArray() {
	  Object a[] = new Object[1];
	  a[0] = element;
	  return a;
	 }
	 @Override
	 public ObjectListIterator <K> listIterator() { return ObjectIterators.singleton(element); }
	 @Override
	 public ObjectListIterator <K> iterator() { return listIterator(); }
	 @Override
	 public ObjectListIterator <K> listIterator(int i) {
	  if (i > 1 || i < 0) throw new IndexOutOfBoundsException();
	  ObjectListIterator <K> l = listIterator();
	  if (i == 1) l.next();
	  return l;
	 }
	 @Override
	 @SuppressWarnings("unchecked")
	 public ReferenceList <K> subList(final int from, final int to) {
	  ensureIndex(from);
	  ensureIndex(to);
	  if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
	  if (from != 0 || to != 1) return EMPTY_LIST;
	  return this;
	 }
	 @Override
	 public boolean addAll(int i, Collection<? extends K> c) { throw new UnsupportedOperationException(); }
	 @Override
	 public boolean addAll(final Collection<? extends K> c) { throw new UnsupportedOperationException(); }
	 @Override
	 public boolean removeAll(final Collection<?> c) { throw new UnsupportedOperationException(); }
	 @Override
	 public boolean retainAll(final Collection<?> c) { throw new UnsupportedOperationException(); }
	 @Override
	 public int size() { return 1; }
	 @Override
	 public void size(final int size) { throw new UnsupportedOperationException(); }
	 @Override
	 public void clear() { throw new UnsupportedOperationException(); }
	 @Override
	 public Object clone() { return this; }
	}
	/** Returns a type-specific immutable list containing only the specified element. The returned list is serializable and cloneable.
	 *
	 * @param element the only element of the returned list.
	 * @return a type-specific immutable list containing just <code>element</code>.
	 */
	public static <K> ReferenceList <K> singleton(final K element) { return new Singleton <K>(element); }
	/** A synchronized wrapper class for lists. */
	public static class SynchronizedList <K> extends ReferenceCollections.SynchronizedCollection <K> implements ReferenceList <K>, java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final ReferenceList <K> list; // Due to the large number of methods that are not in COLLECTION, this is worth caching.
	 protected SynchronizedList(final ReferenceList <K> l, final Object sync) {
	  super(l, sync);
	  this.list = l;
	 }
	 protected SynchronizedList(final ReferenceList <K> l) {
	  super(l);
	  this.list = l;
	 }
	 @Override
	 public K get(final int i) { synchronized(sync) { return list.get(i); } }
	 @Override
	 public K set(final int i, final K k) { synchronized(sync) { return list.set(i, k); } }
	 @Override
	 public void add(final int i, final K k) { synchronized(sync) { list.add(i, k); } }
	 @Override
	 public K remove(final int i) { synchronized(sync) { return list.remove(i); } }
	 @Override
	 public int indexOf(final Object k) { synchronized(sync) { return list.indexOf(k); } }
	 @Override
	 public int lastIndexOf(final Object k) { synchronized(sync) { return list.lastIndexOf(k); } }
	 @Override
	 public boolean addAll(final int index, final Collection<? extends K> c) { synchronized(sync) { return list.addAll(index, c); } }
	 @Override
	 public void getElements(final int from, final Object a[], final int offset, final int length) { synchronized(sync) { list.getElements(from, a, offset, length); } }
	 @Override
	 public void removeElements(final int from, final int to) { synchronized(sync) { list.removeElements(from, to); } }
	 @Override
	 public void addElements(int index, final K a[], int offset, int length) { synchronized(sync) { list.addElements(index, a, offset, length); } }
	 @Override
	 public void addElements(int index, final K a[]) { synchronized(sync) { list.addElements(index, a); } }
	 @Override
	 public void size(final int size) { synchronized(sync) { list.size(size); } }
	 @Override
	 public ObjectListIterator <K> listIterator() { return list.listIterator(); }
	 @Override
	 public ObjectListIterator <K> iterator() { return listIterator(); }
	 @Override
	 public ObjectListIterator <K> listIterator(final int i) { return list.listIterator(i); }
	 /** Returns a type-specific list iterator on the list.
		 *
		 * @see #listIterator()
		 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #listIterator()}.
		 */
	 @Deprecated
	 @Override
	 public ObjectListIterator <K> objectListIterator() { return listIterator(); }
	 /** Returns a type-specific list iterator on the list.
		 *
		 * @see #listIterator()
		 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #listIterator(int)}.
		 */
	 @Deprecated
	 @Override
	 public ObjectListIterator <K> objectListIterator(final int i) { return listIterator(i); }
	 @Override
	 public ReferenceList <K> subList(final int from, final int to) { synchronized(sync) { return new SynchronizedList <K>(list.subList(from, to), sync); } }
	 /* {@inheritDoc}
		 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #subList(int,int)}.
		 */
	 @Deprecated
	 @Override
	 public ReferenceList <K> referenceSubList(final int from, final int to) { return subList(from, to); }
	 @Override
	 public boolean equals(final Object o) { if (o == this) return true; synchronized(sync) { return collection.equals(o); } }
	 @Override
	 public int hashCode() { synchronized(sync) { return collection.hashCode(); } }
	 private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
	  synchronized(sync) { s.defaultWriteObject(); }
	 }
	}
	/** A synchronized wrapper class for random-access lists. */
	public static class SynchronizedRandomAccessList <K> extends SynchronizedList <K> implements RandomAccess, java.io.Serializable {
	 private static final long serialVersionUID = 0L;
	 protected SynchronizedRandomAccessList(final ReferenceList <K> l, final Object sync) {
	  super(l, sync);
	 }
	 protected SynchronizedRandomAccessList(final ReferenceList <K> l) {
	  super(l);
	 }
	 @Override
	 public ReferenceList <K> subList(final int from, final int to) { synchronized(sync) { return new SynchronizedRandomAccessList <K>(list.subList(from, to), sync); } }
	}
	/** Returns a synchronized type-specific list backed by the given type-specific list.
	 *
	 * @param l the list to be wrapped in a synchronized list.
	 * @return a synchronized view of the specified list.
	 * @see java.util.Collections#synchronizedList(List)
	 */
	public static <K> ReferenceList <K> synchronize(final ReferenceList <K> l) {
	 return l instanceof RandomAccess ? new SynchronizedRandomAccessList <K>(l) : new SynchronizedList <K>(l);
	}
	/** Returns a synchronized type-specific list backed by the given type-specific list, using an assigned object to synchronize.
	 *
	 * @param l the list to be wrapped in a synchronized list.
	 * @param sync an object that will be used to synchronize the access to the list.
	 * @return a synchronized view of the specified list.
	 * @see java.util.Collections#synchronizedList(List)
	 */
	public static <K> ReferenceList <K> synchronize(final ReferenceList <K> l, final Object sync) {
	 return l instanceof RandomAccess ? new SynchronizedRandomAccessList <K>(l, sync) : new SynchronizedList <K>(l, sync);
	}
	/** An unmodifiable wrapper class for lists. */
	public static class UnmodifiableList <K> extends ReferenceCollections.UnmodifiableCollection <K> implements ReferenceList <K>, java.io.Serializable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected final ReferenceList <K> list; // Due to the large number of methods that are not in COLLECTION, this is worth caching.
	 protected UnmodifiableList(final ReferenceList <K> l) {
	  super(l);
	  this.list = l;
	 }
	 @Override
	 public K get(final int i) { return list.get(i); }
	 @Override
	 public K set(final int i, final K k) { throw new UnsupportedOperationException(); }
	 @Override
	 public void add(final int i, final K k) { throw new UnsupportedOperationException(); }
	 @Override
	 public K remove(final int i) { throw new UnsupportedOperationException(); }
	 @Override
	 public int indexOf(final Object k) { return list.indexOf(k); }
	 @Override
	 public int lastIndexOf(final Object k) { return list.lastIndexOf(k); }
	 @Override
	 public boolean addAll(final int index, final Collection<? extends K> c) { throw new UnsupportedOperationException(); }
	 @Override
	 public void getElements(final int from, final Object a[], final int offset, final int length) { list.getElements(from, a, offset, length); }
	 @Override
	 public void removeElements(final int from, final int to) { throw new UnsupportedOperationException(); }
	 @Override
	 public void addElements(int index, final K a[], int offset, int length) { throw new UnsupportedOperationException(); }
	 @Override
	 public void addElements(int index, final K a[]) { throw new UnsupportedOperationException(); }
	 @Override
	 public void size(final int size) { list.size(size); }
	 @Override
	 public ObjectListIterator <K> listIterator() { return ObjectIterators.unmodifiable(list.listIterator()); }
	 @Override
	 public ObjectListIterator <K> iterator() { return listIterator(); }
	 @Override
	 public ObjectListIterator <K> listIterator(final int i) { return ObjectIterators.unmodifiable(list.listIterator(i)); }
	 /** Returns a type-specific list iterator on the list.
		 *
		 * @see #listIterator()
		 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #listIterator()}.
		 */
	 @Deprecated
	 @Override
	 public ObjectListIterator <K> objectListIterator() { return listIterator(); }
	 /** Returns a type-specific list iterator on the list.
		 *
		 * @see #listIterator()
		 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #listIterator(int)}.
		 */
	 @Deprecated
	 @Override
	 public ObjectListIterator <K> objectListIterator(final int i) { return listIterator(i); }
	 public ReferenceList <K> subList(final int from, final int to) { return new UnmodifiableList <K>(list.subList(from, to)); }
	 /* {@inheritDoc}
		 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #subList(int,int)}.
		 */
	 @Deprecated
	 @Override
	 public ReferenceList <K> referenceSubList(final int from, final int to) { return subList(from, to); }
	 @Override
	 public boolean equals(final Object o) { if (o == this) return true; return collection.equals(o); }
	 @Override
	 public int hashCode() { return collection.hashCode(); }
	}
	/** An unmodifiable wrapper class for random-access lists. */
	public static class UnmodifiableRandomAccessList <K> extends UnmodifiableList <K> implements RandomAccess, java.io.Serializable {
	 private static final long serialVersionUID = 0L;
	 protected UnmodifiableRandomAccessList(final ReferenceList <K> l) {
	  super(l);
	 }
	 @Override
	 public ReferenceList <K> subList(final int from, final int to) { return new UnmodifiableRandomAccessList <K>(list.subList(from, to)); }
	}
	/** Returns an unmodifiable type-specific list backed by the given type-specific list.
	 *
	 * @param l the list to be wrapped in an unmodifiable list.
	 * @return an unmodifiable view of the specified list.
	 * @see java.util.Collections#unmodifiableList(List)
	 */
	public static <K> ReferenceList <K> unmodifiable(final ReferenceList <K> l) {
	 return l instanceof RandomAccess ? new UnmodifiableRandomAccessList <K>(l) : new UnmodifiableList <K>(l);
	}
}

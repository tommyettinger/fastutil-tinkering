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
package it.unimi.dsi.fastutil.longs;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/** A class providing static methods and objects that do useful things with type-specific iterators.
	*
	* @see Iterator
	*/
public class LongIterators {
	private LongIterators() {}
	/** A class returning no elements and a type-specific iterator interface.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific iterator.
	 */
	public static class EmptyIterator extends AbstractLongListIterator implements java.io.Serializable, Cloneable {
	 private static final long serialVersionUID = -7046029254386353129L;
	 protected EmptyIterator() {}
	 @Override
	 public boolean hasNext() { return false; }
	 @Override
	 public boolean hasPrevious() { return false; }
	 @Override
	 public long nextLong() { throw new NoSuchElementException(); }
	 @Override
	 public long previousLong() { throw new NoSuchElementException(); }
	 @Override
	 public int nextIndex() { return 0; }
	 @Override
	 public int previousIndex() { return -1; }
	 @Override
	 public int skip(int n) { return 0; };
	 @Override
	 public int back(int n) { return 0; };
	 @Override
	 public Object clone() { return EMPTY_ITERATOR; }
	 private Object readResolve() { return EMPTY_ITERATOR; }
	}
	/** An empty iterator. It is serializable and cloneable.
	 *
	 * <P>The class of this objects represent an abstract empty iterator
	 * that can iterate as a type-specific (list) iterator.
	 */

	public final static EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
	/** An iterator returning a single element. */
	private static class SingletonIterator extends AbstractLongListIterator {
	 private final long element;
	 private int curr;
	 public SingletonIterator(final long element) {
	  this.element = element;
	 }
	 @Override
	 public boolean hasNext() { return curr == 0; }
	 @Override
	 public boolean hasPrevious() { return curr == 1; }
	 @Override
	 public long nextLong() {
	  if (! hasNext()) throw new NoSuchElementException();
	  curr = 1;
	  return element;
	 }
	 @Override
	 public long previousLong() {
	  if (! hasPrevious()) throw new NoSuchElementException();
	  curr = 0;
	  return element;
	 }
	 @Override
	 public int nextIndex() {
	  return curr;
	 }
	 @Override
	 public int previousIndex() {
	  return curr - 1;
	 }
	}
	/** Returns an immutable iterator that iterates just over the given element.
	 *
	 * @param element the only element to be returned by a type-specific list iterator.
	 * @return an immutable iterator that iterates just over <code>element</code>.
	 */
	public static LongListIterator singleton(final long element) {
	 return new SingletonIterator (element);
	}
	/** A class to wrap arrays in iterators. */
	private static class ArrayIterator extends AbstractLongListIterator {
	 private final long[] array;
	 private final int offset, length;
	 private int curr;
	 public ArrayIterator(final long[] array, final int offset, final int length) {
	  this.array = array;
	  this.offset = offset;
	  this.length = length;
	 }
	 @Override
	 public boolean hasNext() { return curr < length; }
	 @Override
	 public boolean hasPrevious() { return curr > 0; }
	 @Override
	 public long nextLong() {
	  if (! hasNext()) throw new NoSuchElementException();
	  return array[offset + curr++];
	 }
	 @Override
	 public long previousLong() {
	  if (! hasPrevious()) throw new NoSuchElementException();
	  return array[offset + --curr];
	 }
	 @Override
	 public int skip(int n) {
	  if (n <= length - curr) {
	   curr += n;
	   return n;
	  }
	  n = length - curr;
	  curr = length;
	  return n;
	 }
	 @Override
	 public int back(int n) {
	  if (n <= curr) {
	   curr -= n;
	   return n;
	  }
	  n = curr;
	  curr = 0;
	  return n;
	 }
	 @Override
	 public int nextIndex() {
	  return curr;
	 }
	 @Override
	 public int previousIndex() {
	  return curr - 1;
	 }
	}
	/** Wraps the given part of an array into a type-specific list iterator.
	 *
	 * <P>The type-specific list iterator returned by this method will iterate
	 * <code>length</code> times, returning consecutive elements of the given
	 * array starting from the one with index <code>offset</code>.
	 *
	 * @param array an array to wrap into a type-specific list iterator.
	 * @param offset the first element of the array to be returned.
	 * @param length the number of elements to return.
	 * @return an iterator that will return <code>length</code> elements of <code>array</code> starting at position <code>offset</code>.
	 */
	public static LongListIterator wrap(final long[] array, final int offset, final int length) {
	 LongArrays.ensureOffsetLength(array, offset, length);
	 return new ArrayIterator (array, offset, length);
	}
	/** Wraps the given array into a type-specific list iterator.
	 *
	 * <P>The type-specific list iterator returned by this method will return
	 * all elements of the given array.
	 *
	 * @param array an array to wrap into a type-specific list iterator.
	 * @return an iterator that will the elements of <code>array</code>.
	 */
	public static LongListIterator wrap(final long[] array) {
	 return new ArrayIterator (array, 0, array.length);
	}
	/** Unwraps an iterator into an array starting at a given offset for a given number of elements.
	 *
	 * <P>This method iterates over the given type-specific iterator and stores the elements
	 * returned, up to a maximum of <code>length</code>, in the given array starting at <code>offset</code>.
	 * The number of actually unwrapped elements is returned (it may be less than <code>max</code> if
	 * the iterator emits less than <code>max</code> elements).
	 *
	 * @param i a type-specific iterator.
	 * @param array an array to contain the output of the iterator.
	 * @param offset the first element of the array to be returned.
	 * @param max the maximum number of elements to unwrap.
	 * @return the number of elements unwrapped.
	 */
	public static int unwrap(final LongIterator i, final long array[], int offset, final int max) {
	 if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
	 if (offset < 0 || offset + max > array.length) throw new IllegalArgumentException();
	 int j = max;
	 while(j-- != 0 && i.hasNext()) array[offset++] = i.nextLong();
	 return max - j - 1;
	}
	/** Unwraps an iterator into an array.
	 *
	 * <P>This method iterates over the given type-specific iterator and stores the
	 * elements returned in the given array. The iteration will stop when the
	 * iterator has no more elements or when the end of the array has been reached.
	 *
	 * @param i a type-specific iterator.
	 * @param array an array to contain the output of the iterator.
	 * @return the number of elements unwrapped.
	 */
	public static int unwrap(final LongIterator i, final long array[]) {
	 return unwrap(i, array, 0, array.length);
	}
	/** Unwraps an iterator, returning an array, with a limit on the number of elements.
	 *
	 * <P>This method iterates over the given type-specific iterator and returns an array
	 * containing the elements returned by the iterator. At most <code>max</code> elements
	 * will be returned.
	 *
	 * @param i a type-specific iterator.
	 * @param max the maximum number of elements to be unwrapped.
	 * @return an array containing the elements returned by the iterator (at most <code>max</code>).
	 */

	public static long[] unwrap(final LongIterator i, int max) {
	 if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
	 long array[] = new long[16];
	 int j = 0;
	 while(max-- != 0 && i.hasNext()) {
	  if (j == array.length) array = LongArrays.grow(array, j + 1);
	  array[j++] = i.nextLong();
	 }
	 return LongArrays.trim(array, j);
	}
	/** Unwraps an iterator, returning an array.
	 *
	 * <P>This method iterates over the given type-specific iterator and returns an array
	 * containing the elements returned by the iterator.
	 *
	 * @param i a type-specific iterator.
	 * @return an array containing the elements returned by the iterator.
	 */
	public static long[] unwrap(final LongIterator i) {
	 return unwrap(i, Integer.MAX_VALUE);
	}
	/** Unwraps an iterator into a type-specific collection,  with a limit on the number of elements.
	 *
	 * <P>This method iterates over the given type-specific iterator and stores the elements
	 * returned, up to a maximum of <code>max</code>, in the given type-specific collection.
	 * The number of actually unwrapped elements is returned (it may be less than <code>max</code> if
	 * the iterator emits less than <code>max</code> elements).
	 *
	 * @param i a type-specific iterator.
	 * @param c a type-specific collection array to contain the output of the iterator.
	 * @param max the maximum number of elements to unwrap.
	 * @return the number of elements unwrapped. Note that
	 * this is the number of elements returned by the iterator, which is not necessarily the number
	 * of elements that have been added to the collection (because of duplicates).
	 */
	public static int unwrap(final LongIterator i, final LongCollection c, final int max) {
	 if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
	 int j = max;
	 while(j-- != 0 && i.hasNext()) c.add(i.nextLong());
	 return max - j - 1;
	}
	/** Unwraps an iterator into a type-specific collection.
	 *
	 * <P>This method iterates over the given type-specific iterator and stores the
	 * elements returned in the given type-specific collection. The returned count on the number
	 * unwrapped elements is a long, so that it will work also with very large collections.
	 *
	 * @param i a type-specific iterator.
	 * @param c a type-specific collection to contain the output of the iterator.
	 * @return the number of elements unwrapped. Note that
	 * this is the number of elements returned by the iterator, which is not necessarily the number
	 * of elements that have been added to the collection (because of duplicates).
	 */
	public static long unwrap(final LongIterator i, final LongCollection c) {
	 long n = 0;
	 while(i.hasNext()) {
	  c.add(i.nextLong());
	  n++;
	 }
	 return n;
	}
	/** Pours an iterator into a type-specific collection, with a limit on the number of elements.
	 *
	 * <P>This method iterates over the given type-specific iterator and adds
	 * the returned elements to the given collection (up to <code>max</code>).
	 *
	 * @param i a type-specific iterator.
	 * @param s a type-specific collection.
	 * @param max the maximum number of elements to be poured.
	 * @return the number of elements poured. Note that
	 * this is the number of elements returned by the iterator, which is not necessarily the number
	 * of elements that have been added to the collection (because of duplicates).
	 */
	public static int pour(final LongIterator i, final LongCollection s, final int max) {
	 if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
	 int j = max;
	 while(j-- != 0 && i.hasNext()) s.add(i.nextLong());
	 return max - j - 1;
	}
	/** Pours an iterator into a type-specific collection.
	 *
	 * <P>This method iterates over the given type-specific iterator and adds
	 * the returned elements to the given collection.
	 *
	 * @param i a type-specific iterator.
	 * @param s a type-specific collection.
	 * @return the number of elements poured. Note that
	 * this is the number of elements returned by the iterator, which is not necessarily the number
	 * of elements that have been added to the collection (because of duplicates).
	 */
	public static int pour(final LongIterator i, final LongCollection s) {
	 return pour(i, s, Integer.MAX_VALUE);
	}
	/** Pours an iterator, returning a type-specific list, with a limit on the number of elements.
	 *
	 * <P>This method iterates over the given type-specific iterator and returns
	 * a type-specific list containing the returned elements (up to <code>max</code>). Iteration
	 * on the returned list is guaranteed to produce the elements in the same order
	 * in which they appeared in the iterator.
	 *
	 *
	 * @param i a type-specific iterator.
	 * @param max the maximum number of elements to be poured.
	 * @return a type-specific list containing the returned elements, up to <code>max</code>.
	 */
	public static LongList pour(final LongIterator i, int max) {
	 final LongArrayList l = new LongArrayList ();
	 pour(i, l, max);
	 l.trim();
	 return l;
	}
	/** Pours an iterator, returning a type-specific list.
	 *
	 * <P>This method iterates over the given type-specific iterator and returns
	 * a list containing the returned elements. Iteration
	 * on the returned list is guaranteed to produce the elements in the same order
	 * in which they appeared in the iterator.
	 *
	 * @param i a type-specific iterator.
	 * @return a type-specific list containing the returned elements.
	 */
	public static LongList pour(final LongIterator i) {
	 return pour(i, Integer.MAX_VALUE);
	}
	private static class IteratorWrapper extends AbstractLongIterator {
	 final Iterator<Long> i;
	 public IteratorWrapper(final Iterator<Long> i) {
	  this.i = i;
	 }
	 public boolean hasNext() { return i.hasNext(); }
	 public void remove() { i.remove(); }
	 public long nextLong() { return ((i.next()).longValue()); }
	}
	/** Wraps a standard iterator into a type-specific iterator.
	 *
	 * <P>This method wraps a standard iterator into a type-specific one which will handle the
	 * type conversions for you. Of course, any attempt to wrap an iterator returning the
	 * instances of the wrong class will generate a {@link ClassCastException}. The
	 * returned iterator is backed by <code>i</code>: changes to one of the iterators
	 * will affect the other, too.
	 *
	 * <P>If <code>i</code> is already type-specific, it will returned and no new object
	 * will be generated.
	 *
	 * @param i an iterator.
	 * @return a type-specific iterator  backed by <code>i</code>.
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	 public static LongIterator asLongIterator(final Iterator i) {
	 if (i instanceof LongIterator) return (LongIterator )i;
	 return new IteratorWrapper (i);
	}
	private static class ListIteratorWrapper extends AbstractLongListIterator {
	 final ListIterator<Long> i;
	 public ListIteratorWrapper(final ListIterator<Long> i) {
	  this.i = i;
	 }
	 @Override
	 public boolean hasNext() { return i.hasNext(); }
	 @Override
	 public boolean hasPrevious() { return i.hasPrevious(); }
	 @Override
	 public int nextIndex() { return i.nextIndex(); }
	 @Override
	 public int previousIndex() { return i.previousIndex(); }
	 @Override
	 public void set(long k) { i.set((Long.valueOf(k))); }
	 @Override
	 public void add(long k) { i.add((Long.valueOf(k))); }
	 @Override
	 public void remove() { i.remove(); }
	 @Override
	 public long nextLong() { return ((i.next()).longValue()); }
	 @Override
	 public long previousLong() { return ((i.previous()).longValue()); }
	}
	/** Wraps a standard list iterator into a type-specific list iterator.
	 *
	 * <P>This method wraps a standard list iterator into a type-specific one
	 * which will handle the type conversions for you. Of course, any attempt
	 * to wrap an iterator returning the instances of the wrong class will
	 * generate a {@link ClassCastException}. The
	 * returned iterator is backed by <code>i</code>: changes to one of the iterators
	 * will affect the other, too.
	 *
	 * <P>If <code>i</code> is already type-specific, it will returned and no new object
	 * will be generated.
	 *
	 * @param i a list iterator.
	 * @return a type-specific list iterator backed by <code>i</code>.
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	 public static LongListIterator asLongIterator(final ListIterator i) {
	 if (i instanceof LongListIterator) return (LongListIterator )i;
	 return new ListIteratorWrapper (i);
	}
	private static class IntervalIterator extends AbstractLongBidirectionalIterator {
	 private final long from, to;
	 long curr;
	 public IntervalIterator(final long from, final long to) {
	  this.from = this.curr = from;
	  this.to = to;
	 }
	 @Override
	 public boolean hasNext() { return curr < to; }
	 @Override
	 public boolean hasPrevious() { return curr > from; }
	 @Override
	 public long nextLong() {
	  if (! hasNext()) throw new NoSuchElementException();
	  return curr++;
	 }
	 @Override
	 public long previousLong() {
	  if (! hasPrevious()) throw new NoSuchElementException();
	  return --curr;
	 }
	 @Override
	 public int skip(int n) {
	  if (curr + n <= to) {
	   curr += n;
	   return n;
	  }
	  n = (int)(to - curr);
	  curr = to;
	  return n;
	 }
	 @Override
	 public int back(int n) {
	  if (curr - n >= from) {
	   curr -= n;
	   return n;
	  }
	  n = (int)(curr - from);
	  curr = from;
	  return n;
	 }
	}
	/** Creates a type-specific bidirectional iterator over an interval.
	 *
	 * <P>The type-specific bidirectional iterator returned by this method will return the
	 * elements <code>from</code>, <code>from+1</code>,&hellip;, <code>to-1</code>.
	 *
	 * <P>Note that all other type-specific interval iterator are <em>list</em>
	 * iterators. Of course, this is not possible with longs as the index
	 * returned by {@link java.util.ListIterator#nextIndex() nextIndex()}/{@link
	 * java.util.ListIterator#previousIndex() previousIndex()} would exceed an integer.
	 *
	 * @param from the starting element (inclusive).
	 * @param to the ending element (exclusive).
	 * @return a type-specific bidirectional iterator enumerating the elements from <code>from</code> to <code>to</code>.
	 */
	public static LongBidirectionalIterator fromTo(final long from, final long to) {
	 return new IntervalIterator(from, to);
	}
	private static class IteratorConcatenator extends AbstractLongIterator {
	 final LongIterator a[];
	 int offset, length, lastOffset = -1;
	 public IteratorConcatenator(final LongIterator a[], int offset, int length) {
	  this.a = a;
	  this.offset = offset;
	  this.length = length;
	  advance();
	 }
	 private void advance() {
	  while(length != 0) {
	   if (a[offset].hasNext()) break;
	   length--;
	   offset++;
	  }
	  return;
	 }
	 @Override
	 public boolean hasNext() {
	  return length > 0;
	 }
	 @Override
	 public long nextLong() {
	  if (! hasNext()) throw new NoSuchElementException();
	  long next = a[lastOffset = offset].nextLong();
	  advance();
	  return next;
	 }
	 @Override
	 public void remove() {
	  if (lastOffset == -1) throw new IllegalStateException();
	  a[lastOffset].remove();
	 }
	 @Override
	 public int skip(int n) {
	  lastOffset = -1;
	  int skipped = 0;
	  while(skipped < n && length != 0) {
	   skipped += a[offset].skip(n - skipped);
	   if (a[offset].hasNext()) break;
	   length--;
	   offset++;
	  }
	  return skipped;
	 }
	}
	/** Concatenates all iterators contained in an array.
	 *
	 * <P>This method returns an iterator that will enumerate in order the elements returned
	 * by all iterators contained in the given array.
	 *
	 * @param a an array of iterators.
	 * @return an iterator obtained by concatenation.
	 */
	public static LongIterator concat(final LongIterator a[]) {
	 return concat(a, 0, a.length);
	}
	/** Concatenates a sequence of iterators contained in an array.
	 *
	 * <P>This method returns an iterator that will enumerate in order the elements returned
	 * by <code>a[offset]</code>, then those returned
	 * by <code>a[offset + 1]</code>, and so on up to
	 * <code>a[offset + length - 1]</code>.
	 *
	 * @param a an array of iterators.
	 * @param offset the index of the first iterator to concatenate.
	 * @param length the number of iterators to concatenate.
	 * @return an iterator obtained by concatenation of <code>length</code> elements of <code>a</code> starting at <code>offset</code>.
	 */
	public static LongIterator concat(final LongIterator a[], final int offset, final int length) {
	 return new IteratorConcatenator (a, offset, length);
	}
	  /** An unmodifiable wrapper class for iterators. */
	public static class UnmodifiableIterator extends AbstractLongIterator {
	 final protected LongIterator i;
	 public UnmodifiableIterator(final LongIterator i) {
	  this.i = i;
	 }
	 @Override
	 public boolean hasNext() { return i.hasNext(); }
	 @Override
	 public long nextLong() { return i.nextLong(); }
	}
	/** Returns an unmodifiable iterator backed by the specified iterator.
	 *
	 * @param i the iterator to be wrapped in an unmodifiable iterator.
	 * @return an unmodifiable view of the specified iterator.
	 */
	public static LongIterator unmodifiable(final LongIterator i) { return new UnmodifiableIterator (i); }
	  /** An unmodifiable wrapper class for bidirectional iterators. */
	public static class UnmodifiableBidirectionalIterator extends AbstractLongBidirectionalIterator {
	 final protected LongBidirectionalIterator i;
	 public UnmodifiableBidirectionalIterator(final LongBidirectionalIterator i) {
	  this.i = i;
	 }
	 @Override
	 public boolean hasNext() { return i.hasNext(); }
	 @Override
	 public boolean hasPrevious() { return i.hasPrevious(); }
	 @Override
	 public long nextLong() { return i.nextLong(); }
	 @Override
	 public long previousLong() { return i.previousLong(); }
	}
	/** Returns an unmodifiable bidirectional iterator backed by the specified bidirectional iterator.
	 *
	 * @param i the bidirectional iterator to be wrapped in an unmodifiable bidirectional iterator.
	 * @return an unmodifiable view of the specified bidirectional iterator.
	 */
	public static LongBidirectionalIterator unmodifiable(final LongBidirectionalIterator i) { return new UnmodifiableBidirectionalIterator (i); }
	  /** An unmodifiable wrapper class for list iterators. */
	public static class UnmodifiableListIterator extends AbstractLongListIterator {
	 final protected LongListIterator i;
	 public UnmodifiableListIterator(final LongListIterator i) {
	  this.i = i;
	 }
	 @Override
	 public boolean hasNext() { return i.hasNext(); }
	 @Override
	 public boolean hasPrevious() { return i.hasPrevious(); }
	 @Override
	 public long nextLong() { return i.nextLong(); }
	 @Override
	 public long previousLong() { return i.previousLong(); }
	 @Override
	 public int nextIndex() { return i.nextIndex(); }
	 @Override
	 public int previousIndex() { return i.previousIndex(); }
	}
	/** Returns an unmodifiable list iterator backed by the specified list iterator.
	 *
	 * @param i the list iterator to be wrapped in an unmodifiable list iterator.
	 * @return an unmodifiable view of the specified list iterator.
	 */
	public static LongListIterator unmodifiable(final LongListIterator i) { return new UnmodifiableListIterator (i); }
	  /** A wrapper promoting the results of a ByteIterator. */
	protected static class ByteIteratorWrapper implements LongIterator {
	 final it.unimi.dsi.fastutil.bytes.ByteIterator iterator;
	 public ByteIteratorWrapper(final it.unimi.dsi.fastutil.bytes.ByteIterator iterator) {
	  this.iterator = iterator;
	 }
	 @Override
	 public boolean hasNext() { return iterator.hasNext(); }
	 @Deprecated
	 @Override
	 public Long next() { return Long.valueOf(iterator.nextByte()); }
	 @Override
	 public long nextLong() { return iterator.nextByte(); }
	 @Override
	 public void remove() { iterator.remove(); }
	 @Override
	 public int skip(final int n) { return iterator.skip(n); }
	}
	/** Returns an iterator backed by the specified byte iterator.
	 * @return an iterator backed by the specified byte iterator.
	 */
	public static LongIterator wrap(final it.unimi.dsi.fastutil.bytes.ByteIterator iterator) {
	 return new ByteIteratorWrapper(iterator);
	}
	  /** A wrapper promoting the results of a ShortIterator. */
	protected static class ShortIteratorWrapper implements LongIterator {
	 final it.unimi.dsi.fastutil.shorts.ShortIterator iterator;
	 public ShortIteratorWrapper(final it.unimi.dsi.fastutil.shorts.ShortIterator iterator) {
	  this.iterator = iterator;
	 }
	 @Override
	 public boolean hasNext() { return iterator.hasNext(); }
	 @Deprecated
	 @Override
	 public Long next() { return Long.valueOf(iterator.nextShort()); }
	 @Override
	 public long nextLong() { return iterator.nextShort(); }
	 @Override
	 public void remove() { iterator.remove(); }
	 @Override
	 public int skip(final int n) { return iterator.skip(n); }
	}
	/** Returns an iterator backed by the specified short iterator.
	 * @return an iterator backed by the specified short iterator.
	 */
	public static LongIterator wrap(final it.unimi.dsi.fastutil.shorts.ShortIterator iterator) {
	 return new ShortIteratorWrapper(iterator);
	}
	  /** A wrapper promoting the results of an IntIterator. */
	protected static class IntIteratorWrapper implements LongIterator {
	 final it.unimi.dsi.fastutil.ints.IntIterator iterator;
	 public IntIteratorWrapper(final it.unimi.dsi.fastutil.ints.IntIterator iterator) {
	  this.iterator = iterator;
	 }
	 @Override
	 public boolean hasNext() { return iterator.hasNext(); }
	 @Deprecated
	 @Override
	 public Long next() { return Long.valueOf(iterator.nextInt()); }
	 @Override
	 public long nextLong() { return iterator.nextInt(); }
	 @Override
	 public void remove() { iterator.remove(); }
	 @Override
	 public int skip(final int n) { return iterator.skip(n); }
	}
	/** Returns an iterator backed by the specified integer iterator.
	 * @return an iterator backed by the specified integer iterator.
	 */
	public static LongIterator wrap(final it.unimi.dsi.fastutil.ints.IntIterator iterator) {
	 return new IntIteratorWrapper(iterator);
	}
}

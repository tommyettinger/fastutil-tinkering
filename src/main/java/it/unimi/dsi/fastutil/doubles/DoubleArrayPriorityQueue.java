/*
	* Copyright (C) 2003-2017 Paolo Boldi and Sebastiano Vigna
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
import java.util.NoSuchElementException;
/** A type-specific array-based priority queue.
	*
	* <P>Instances of this class represent a priority queue using a backing
	* array&mdash;all operations are performed directly on the array. The array is
	* enlarged as needed, but it is never shrunk. Use the {@link #trim()} method
	* to reduce its size, if necessary.
	*
	* <P>This implementation is extremely inefficient, but it is difficult to beat
	* when the size of the queue is very small.
	*/
public class DoubleArrayPriorityQueue extends AbstractDoublePriorityQueue implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/** The backing array. */

	protected transient double array[] = DoubleArrays.EMPTY_ARRAY;
	/** The number of elements in this queue. */
	protected int size;
	/** The type-specific comparator used in this queue. */
	protected DoubleComparator c;
	/** The first index, cached, if {@link #firstIndexValid} is true. */
	transient protected int firstIndex;
	/** Whether {@link #firstIndex} contains a valid value. */
	transient protected boolean firstIndexValid;
	/** Creates a new empty queue with a given capacity and comparator.
	 *
	 * @param capacity the initial capacity of this queue.
	 * @param c the comparator used in this queue, or {@code null} for the natural order.
	 */

	public DoubleArrayPriorityQueue(int capacity, DoubleComparator c) {
	 if (capacity > 0) this.array = new double[capacity];
	 this.c = c;
	}
	/** Creates a new empty queue with a given capacity and using the natural order.
	 *
	 * @param capacity the initial capacity of this queue.
	 */
	public DoubleArrayPriorityQueue(int capacity) {
	 this(capacity, null);
	}
	/** Creates a new empty queue with a given comparator.
	 *
	 * @param c the comparator used in this queue, or {@code null} for the natural order.
	 */
	public DoubleArrayPriorityQueue(DoubleComparator c) {
	 this(0, c);
	}
	/** Creates a new empty queue using the natural order.
	 */
	public DoubleArrayPriorityQueue() {
	 this(0, null);
	}
	/** Wraps a given array in a queue using a given comparator.
	 *
	 * <P>The queue returned by this method will be backed by the given array.
	 *
	 * @param a an array.
	 * @param size the number of elements to be included in the queue.
	 * @param c the comparator used in this queue, or {@code null} for the natural order.
	 */
	public DoubleArrayPriorityQueue(final double[] a, int size, final DoubleComparator c) {
	 this(c);
	 this.array = a;
	 this.size = size;
	}
	/** Wraps a given array in a queue using a given comparator.
	 *
	 * <P>The queue returned by this method will be backed by the given array.
	 *
	 * @param a an array.
	 * @param c the comparator used in this queue, or {@code null} for the natural order.
	 */
	public DoubleArrayPriorityQueue(final double[] a, final DoubleComparator c) {
	 this(a, a.length, c);
	}
	/** Wraps a given array in a queue using the natural order.
	 *
	 * <P>The queue returned by this method will be backed by the given array.
	 *
	 * @param a an array.
	 * @param size the number of elements to be included in the queue.
	 */
	public DoubleArrayPriorityQueue(final double[] a, int size) {
	 this(a, size, null);
	}
	/** Wraps a given array in a queue using the natural order.
	 *
	 * <P>The queue returned by this method will be backed by the given array.
	 *
	 * @param a an array.
	 */
	public DoubleArrayPriorityQueue(final double[] a) {
	 this(a, a.length);
	}
	/** Returns the index of the smallest element. */

	private int findFirst() {
	 if (firstIndexValid) return this.firstIndex;
	 firstIndexValid = true;
	 int i = size;
	 int firstIndex = --i;
	 double first = array[firstIndex];
	 if (c == null) { while(i-- != 0) if (( Double.compare((array[i]),(first)) < 0 )) first = array[firstIndex = i]; }
	 else while(i-- != 0) { if (c.compare(array[i], first) < 0) first = array[firstIndex = i]; }
	 return this.firstIndex = firstIndex;
	}
	private void ensureNonEmpty() {
	 if (size == 0) throw new NoSuchElementException();
	}
	@Override

	public void enqueue(double x) {
	 if (size == array.length) array = DoubleArrays.grow(array, size + 1);
	 if (firstIndexValid) {
	  if (c == null) { if (( Double.compare((x),(array[firstIndex])) < 0 )) firstIndex = size; }
	  else if (c.compare(x, array[firstIndex]) < 0) firstIndex = size;
	 }
	 else firstIndexValid = false;
	 array[size++] = x;
	}
	@Override
	public double dequeueDouble() {
	 ensureNonEmpty();
	 final int first = findFirst();
	 final double result = array[first];
	 System.arraycopy(array, first + 1, array, first, --size - first);
	 firstIndexValid = false;
	 return result;
	}
	@Override
	public double firstDouble() {
	 ensureNonEmpty();
	 return array[findFirst()];
	}
	@Override
	public void changed() {
	 ensureNonEmpty();
	 firstIndexValid = false;
	}
	@Override
	public int size() { return size; }
	@Override
	public void clear() {
	 size = 0;
	 firstIndexValid = false;
	}
	/** Trims the underlying array so that it has exactly {@link #size()} elements. */
	public void trim() {
	 array = DoubleArrays.trim(array, size);
	}
	@Override
	public DoubleComparator comparator() { return c; }
	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
	 s.defaultWriteObject();
	 s.writeInt(array.length);
	 for(int i = 0; i < size; i++) s.writeDouble(array[i]);
	}

	private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
	 s.defaultReadObject();
	 array = new double[s.readInt()];
	 for(int i = 0; i < size; i++) array[i] = s.readDouble();
	}
}

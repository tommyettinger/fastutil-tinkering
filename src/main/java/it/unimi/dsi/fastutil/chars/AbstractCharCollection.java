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
package it.unimi.dsi.fastutil.chars;
import java.util.AbstractCollection;
/** An abstract class providing basic methods for collections implementing a type-specific interface.
	*
	* <P>In particular, this class provide {@link #iterator()}, <code>add()</code>, {@link #remove(Object)} and
	* {@link #contains(Object)} methods that just call the type-specific counterpart.
	*
	* <p><strong>Warning</strong>: Because of a name clash between the list and collection interfaces
	* the type-specific deletion method of a type-specific abstract
	* collection is <code>rem()</code>, rather then <code>remove()</code>. A
	* subclass must thus override <code>rem()</code>, rather than
	* <code>remove()</code>, to make all inherited methods work properly.
	*/
public abstract class AbstractCharCollection extends AbstractCollection<Character> implements CharCollection {
	protected AbstractCharCollection() {}
	/** {@inheritDoc}
	 *
	 * <p>This implementation delegates to the new covariantly stronger generic method {@link #iterator()}.
	 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #iterator()}.
	 */
	@Deprecated
	@Override
	public CharIterator charIterator() {
	 return iterator();
	}
	@Override
	public abstract CharIterator iterator();
	/** {@inheritDoc}
	 *
	 * <p>This implementation always throws an {@link UnsupportedOperationException}.
	 */
	@Override
	public boolean add(final char k) {
	 throw new UnsupportedOperationException();
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation iterates over the elements in the collection,
	 * looking for the specified element.
	 */
	public boolean contains(final char k) {
	 final CharIterator iterator = iterator();
	 while (iterator.hasNext()) if (k == iterator.nextChar()) return true;
	 return false;
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation iterates over the elements in the collection,
	 * looking for the specified element and tries to remove it.
	 */
	@Override
	public boolean rem(final char k) {
	 final CharIterator iterator = iterator();
	 while (iterator.hasNext())
	  if (k == iterator.nextChar()) {
	   iterator.remove();
	   return true;
	  }
	 return false;
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead.
	 */
	@Deprecated
	@Override
	public boolean add(final Character o) {
	 return add(o.charValue());
	}
	/** {@inheritDoc}
	 *
	 * <p>This implementation delegates to the corresponding type-specific method.
	 * @deprecated Please use the corresponding type-specific method instead.
	 */
	@Deprecated
	@Override
	public boolean contains(final Object o) {
	 if (o == null) return false;
	 return contains(((((Character)(o)).charValue())));
	}
	/** {@inheritDoc}
	 * <p>This implementation delegates to the {@code rem()} method.
	 * @deprecated Please use (and implement) the {@code rem()} method instead.
	 */
	@Deprecated
	@Override
	public boolean remove(final Object o) {
	 if (o == null) return false;
	 return rem(((((Character)(o)).charValue())));
	}
	@Override
	public char[] toArray(char a[]) {
	 if (a == null || a.length < size()) a = new char[size()];
	 CharIterators.unwrap(iterator(), a);
	 return a;
	}
	@Override
	public char[] toCharArray() {
	 return toCharArray(null);
	}
	/* {@inheritDoc}
	 * @deprecated Please use {@code toArray()} instead&mdash;this method is redundant.
	 */
	@Deprecated
	@Override
	public char[] toCharArray(final char a[]) {
	 return toArray(a);
	}
	@Override
	public boolean addAll(final CharCollection c) {
	 boolean retVal = false;
	 for(final CharIterator i = c.iterator(); i.hasNext();)
	  if (add(i.nextChar())) retVal = true;
	 return retVal;
	}
	@Override
	public boolean containsAll(final CharCollection c) {
	 for(final CharIterator i = c.iterator(); i.hasNext();)
	  if (! contains(i.nextChar())) return false;
	 return true;
	}
	@Override
	public boolean removeAll(final CharCollection c) {
	 boolean retVal = false;
	 for(final CharIterator i = c.iterator(); i.hasNext();)
	  if (rem(i.nextChar())) retVal = true;
	 return retVal;
	}
	@Override
	public boolean retainAll(final CharCollection c) {
	 boolean retVal = false;
	 for(final CharIterator i = iterator(); i.hasNext();)
	  if (! c.contains(i.nextChar())) {
	   i.remove();
	   retVal = true;
	  }
	 return retVal;
	}
	@Override
	public String toString() {
	 final StringBuilder s = new StringBuilder();
	 final CharIterator i = iterator();
	 int n = size();
	 char k;
	 boolean first = true;
	 s.append("{");
	 while(n-- != 0) {
	  if (first) first = false;
	  else s.append(", ");
	  k = i.nextChar();
	   s.append(String.valueOf(k));
	 }
	 s.append("}");
	 return s.toString();
	}
}

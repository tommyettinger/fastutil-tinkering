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
package it.unimi.dsi.fastutil.chars;
/** A class providing static methods and objects that do useful things with comparators.
	*/
public class CharComparators {
	private CharComparators() {}
	/** A type-specific comparator mimicking the natural order. */
	protected static class NaturalImplicitComparator extends AbstractCharComparator implements java.io.Serializable {
	 private static final long serialVersionUID = 1L;
	 @Override
	 public final int compare(final char a, final char b) {
	  return ( Character.compare((a),(b)) );
	 }
	 private Object readResolve() { return NATURAL_COMPARATOR; }
	};

	public static final CharComparator NATURAL_COMPARATOR = new NaturalImplicitComparator();
	/** A type-specific comparator mimicking the opposite of the natural order. */
	protected static class OppositeImplicitComparator extends AbstractCharComparator implements java.io.Serializable {
	 private static final long serialVersionUID = 1L;
	 @Override
	 public final int compare(final char a, final char b) {
	  return - ( Character.compare((a),(b)) );
	 }
	 private Object readResolve() { return OPPOSITE_COMPARATOR; }
	};

	public static final CharComparator OPPOSITE_COMPARATOR = new OppositeImplicitComparator();
	protected static class OppositeComparator extends AbstractCharComparator implements java.io.Serializable {
	 private static final long serialVersionUID = 1L;
	 private final CharComparator comparator;
	 protected OppositeComparator(final CharComparator c) {
	  comparator = c;
	 }
	 @Override
	 public final int compare(final char a, final char b) {
	  return comparator.compare(b, a);
	 }
	};
	/** Returns a comparator representing the opposite order of the given comparator.
	 *
	 * @param c a comparator.
	 * @return a comparator representing the opposite order of <code>c</code>.
	 */
	public static CharComparator oppositeComparator(final CharComparator c) {
	 return new OppositeComparator (c);
	}
}

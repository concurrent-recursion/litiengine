/*
 * The Alphanum Algorithm is an improved sorting algorithm for strings
 * containing numbers.  Instead of sorting numbers in ASCII order like
 * a standard sort, this algorithm sorts numbers in numeric order.
 *
 * The Alphanum Algorithm is discussed at http://www.DaveKoelle.com
 *
 * Released under the MIT License - https://opensource.org/licenses/MIT
 *
 * Copyright 2007-2017 David Koelle
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.gurkenlabs.litiengine.util;

import java.util.Comparator;

/**
 * This is an updated version with enhancements made by Daniel Migowski, Andre Bogus, and David Koelle. Updated by David Koelle in 2017.
 *
 * <p>
 * To use this class: Use the static "sort" method from the java.util.Collections class: Collections.sort(your list, new AlphanumComparator());
 */
public class AlphanumComparator implements Comparator<String> {
  private AlphanumComparator() {
    // Default constructor
  }

  private static boolean isDigit(char ch) {
    return ((ch >= 48) && (ch <= 57));
  }

  /**
   * Length of string is passed in for improved efficiency (only need to calculate it once) *
   */
  private static String getChunk(String s, int slength, int marker) {
    StringBuilder chunk = new StringBuilder();
    char c = s.charAt(marker);
    chunk.append(c);
    marker++;
    if (isDigit(c)) {
      while (marker < slength) {
        c = s.charAt(marker);
        if (!isDigit(c)) {
          break;
        }
        chunk.append(c);
        marker++;
      }
    } else {
      while (marker < slength) {
        c = s.charAt(marker);
        if (isDigit(c)) {
          break;
        }
        chunk.append(c);
        marker++;
      }
    }
    return chunk.toString();
  }

  /**
   * Compares two strings using the Alphanum algorithm. This method compares two strings by breaking them into chunks of numeric and non-numeric
   * characters and comparing these chunks numerically and lexicographically.
   *
   * @param s1 the first string to compare
   * @param s2 the second string to compare
   * @return a negative integer, zero, or a positive integer as the first string is less than, equal to, or greater than the second string
   */
  @Override
  public int compare(String s1, String s2) {
    return compareTo(s1, s2);
  }

  /**
   * Compares two strings using the Alphanum algorithm. This method compares two strings by breaking them into chunks of numeric and non-numeric
   * characters and comparing these chunks numerically and lexicographically.
   *
   * @param s1 the first string to compare
   * @param s2 the second string to compare
   * @return a negative integer, zero, or a positive integer as the first string is less than, equal to, or greater than the second string
   */
  public static int compareTo(String s1, String s2) {
    if ((s1 == null) || (s2 == null)) {
      return 0;
    }
    int thisMarker = 0;
    int thatMarker = 0;
    int s1Length = s1.length();
    int s2Length = s2.length();

    while (thisMarker < s1Length && thatMarker < s2Length) {
      String thisChunk = getChunk(s1, s1Length, thisMarker);
      thisMarker += thisChunk.length();

      String thatChunk = getChunk(s2, s2Length, thatMarker);
      thatMarker += thatChunk.length();

      // If both chunks contain numeric characters, sort them numerically
      int result;
      if (isDigit(thisChunk.charAt(0)) && isDigit(thatChunk.charAt(0))) {
        // Simple chunk comparison by length.
        int thisChunkLength = thisChunk.length();
        result = thisChunkLength - thatChunk.length();
        // If equal, the first different number counts
        if (result == 0) {
          for (int i = 0; i < thisChunkLength; i++) {
            result = thisChunk.charAt(i) - thatChunk.charAt(i);
            if (result != 0) {
              return result;
            }
          }
        }
      } else {
        result = thisChunk.compareTo(thatChunk);
      }

      if (result != 0) {
        return result;
      }
    }

    return s1Length - s2Length;
  }
}

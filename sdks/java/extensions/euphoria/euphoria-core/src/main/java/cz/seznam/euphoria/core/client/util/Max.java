/*
 * Copyright 2016-2018 Seznam.cz, a.s.
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
package cz.seznam.euphoria.core.client.util;

import cz.seznam.euphoria.core.annotation.audience.Audience;
import cz.seznam.euphoria.core.client.functional.CombinableReduceFunction;
import cz.seznam.euphoria.core.client.functional.UnaryFunction;
import java.util.Comparator;

/** Calculate maximum. */
@Audience(Audience.Type.CLIENT)
public class Max {

  private Max() {}

  /**
   * Return {@code CombinableReduceFunction} to calculate maximum of input.
   *
   * @param <InputT> the type of elements handled
   * @param <X> the type of key by which to compare the elements
   * @param extract the key extraction function
   * @return a combiner function which delivers the "maximum" element seen; never {@code null}
   */
  public static <InputT, X extends Comparable<X>> CombinableReduceFunction<InputT> of(
      UnaryFunction<InputT, X> extract) {

    return values ->
        values
            .max(Comparator.comparing(extract::apply))
            .orElseThrow(() -> new IllegalStateException("Got empty stream on input!"));
  }
}

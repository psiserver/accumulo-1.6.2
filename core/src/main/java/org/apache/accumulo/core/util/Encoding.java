/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.accumulo.core.util;

import static com.google.common.base.Charsets.UTF_8;

import org.apache.hadoop.io.Text;

public class Encoding {

  public static String encodeAsBase64FileName(Text data) {
    String encodedRow = Base64.encodeBase64URLSafeString(TextUtil.getBytes(data));

    int index = encodedRow.length() - 1;
    while (index >= 0 && encodedRow.charAt(index) == '=')
      index--;

    encodedRow = encodedRow.substring(0, index + 1);
    return encodedRow;
  }

  public static byte[] decodeBase64FileName(String node) {
    while (node.length() % 4 != 0)
      node += "=";
    /* decode transparently handles URLSafe encodings */
    return Base64.decodeBase64(node.getBytes(UTF_8));
  }

}

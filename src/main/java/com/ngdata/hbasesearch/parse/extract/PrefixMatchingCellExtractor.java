/*
 * Copyright 2013 NGDATA nv
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
package com.ngdata.hbasesearch.parse.extract;

/**
 * Extracts byte arrays from cells based on a matching prefix in the column qualifier.
 */
public class PrefixMatchingCellExtractor extends AbstractPrefixMatchingExtractor {

    public PrefixMatchingCellExtractor(byte[] columnFamily, byte[] qualifierPrefix) {
        super(columnFamily, qualifierPrefix);
    }

    @Override
    protected byte[] extract(byte[] qualifier, byte[] value) {
        return value;
    }

}

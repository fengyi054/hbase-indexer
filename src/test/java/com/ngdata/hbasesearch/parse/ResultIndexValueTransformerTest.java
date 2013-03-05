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
package com.ngdata.hbasesearch.parse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import junit.framework.TestCase;
import org.apache.hadoop.hbase.client.Result;
import org.junit.Before;
import org.junit.Test;

public class ResultIndexValueTransformerTest extends TestCase {

    private ByteArrayExtractor valueExtractor;
    private ByteArrayValueMapper valueMapper;
    private ResultIndexValueTransformer transformer;

    @Override
    @Before
    public void setUp() {
        valueExtractor = mock(ByteArrayExtractor.class);
        valueMapper = mock(ByteArrayValueMapper.class);
        transformer = new ResultIndexValueTransformer("fieldName", valueExtractor, valueMapper);
    }

    @Test
    public void testExtractAndTransform() {
        byte[] bytesA = new byte[] { 1, 2 };
        byte[] bytesB = new byte[] { 3, 4 };

        Result result = mock(Result.class);

        when(valueExtractor.extract(result)).thenReturn(Lists.newArrayList(bytesA, bytesB));
        when(valueMapper.map(bytesA)).thenReturn(Lists.<Object> newArrayList("A"));
        when(valueMapper.map(bytesB)).thenReturn(Lists.<Object> newArrayList("B"));

        Multimap<String, Object> expectedOutput = ArrayListMultimap.create();
        expectedOutput.putAll("fieldName", Lists.newArrayList("A", "B"));

        assertEquals(expectedOutput, transformer.extractAndTransform(result));

    }

}

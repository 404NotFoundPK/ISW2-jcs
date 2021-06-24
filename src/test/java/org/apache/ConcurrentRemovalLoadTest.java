package org.apache;

import org.apache.jcs.JCS;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test which exercises the hierarchical removal when the cache is active.
 */

public class ConcurrentRemovalLoadTest {
    private RemovalTestUtil removalTestUtil;
    private static JCS jcs;
        
    @BeforeClass
    public static void setUpClass() throws Exception {

        JCS.setConfigFilename( "/TestRemoval.ccf" );
        jcs = JCS.getInstance( "testCache1" );
    }

    private void configure() throws Exception {
        removalTestUtil = new RemovalTestUtil();
    }

    @Before
    public void setUp() throws Exception {
        configure();
    }


    @Test
    public void testRemoveCache1() throws Exception {
        removalTestUtil.runTestPutThenRemoveCategorical(jcs, 0, 200 );
    }

    @Test
    public void testRemoveCache2() throws Exception {
        removalTestUtil.runTestPutThenRemoveCategorical(jcs, 601, 700 );
    }

    @Test
    public void testRemoveCache3() throws Exception {
        removalTestUtil.runTestPutThenRemoveCategorical(jcs, 701, 800 );
    }

    @Test
    public void testPutCache1() throws Exception {
        removalTestUtil.runPutInRange(jcs, 300, 400 );
    }

    @Test
    public void testPutCache2() throws Exception {
        removalTestUtil.runPutInRange(jcs, 401, 600 );
    }

    @Test
    public void testPutCache3() throws Exception {
        removalTestUtil.runPutInRange(jcs, 401, 600 );
    }

    @Test
    public void testPutCache4() throws Exception {
        removalTestUtil.runGetInRange(jcs, 0, 1000, false );
    }
}

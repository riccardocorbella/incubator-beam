/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.beam.sdk.io.kinesis;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Queues.newArrayDeque;

import java.util.Deque;
import java.util.Iterator;

/***
 * Very simple implementation of round robin algorithm.
 */
class RoundRobin<T> implements Iterable<T> {
    private final Deque<T> deque;

    public RoundRobin(Iterable<T> collection) {
        this.deque = newArrayDeque(collection);
        checkArgument(!deque.isEmpty(), "Tried to initialize RoundRobin with empty collection");
    }

    public T getCurrent() {
        return deque.getFirst();
    }

    public void moveForward() {
        deque.addLast(deque.removeFirst());
    }

    public int size() {
        return deque.size();
    }

    @Override
    public Iterator<T> iterator() {
        return deque.iterator();
    }
}

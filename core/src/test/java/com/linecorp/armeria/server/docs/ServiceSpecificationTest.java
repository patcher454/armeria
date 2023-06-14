/*
 * Copyright 2023 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linecorp.armeria.server.docs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableList;

class ServiceSpecificationTest {

    @Test
    void preferAliasedStructInfo() {
        final StructInfo noAlias = new StructInfo("foo", ImmutableList.of());
        final StructInfo aliased = noAlias.withAlias("bar");
        ServiceSpecification specification =
                new ServiceSpecification(ImmutableList.of(), ImmutableList.of(),
                                         ImmutableList.of(noAlias, aliased), ImmutableList.of());
        assertThat(specification.structs()).containsExactly(aliased);

        specification = new ServiceSpecification(ImmutableList.of(), ImmutableList.of(),
                                                 ImmutableList.of(aliased, noAlias), ImmutableList.of());
        assertThat(specification.structs()).containsExactly(aliased);
    }
}
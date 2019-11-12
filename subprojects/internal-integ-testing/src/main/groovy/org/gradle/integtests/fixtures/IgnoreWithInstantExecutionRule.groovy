/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.integtests.fixtures

import org.gradle.integtests.fixtures.executer.GradleContextualExecuter
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

import static org.junit.Assume.assumeFalse

class IgnoreWithInstantExecutionRule implements TestRule {

    @Override
    Statement apply(Statement base, Description description) {
        def annotation = description.getAnnotation(IgnoreWithInstantExecution.class)
        if (true || !GradleContextualExecuter.isInstant() || annotation == null) {
            return base
        }
        return new IgnoreWithInstantExecutionRuleStatement(base)
    }

    private static class IgnoreWithInstantExecutionRuleStatement extends Statement {

        private final Statement next

        private IgnoreWithInstantExecutionRuleStatement(Statement next) {
            this.next = next
        }

        @Override
        void evaluate() throws Throwable {
            assumeFalse(GradleContextualExecuter.isInstant())
            next.evaluate()
        }
    }
}

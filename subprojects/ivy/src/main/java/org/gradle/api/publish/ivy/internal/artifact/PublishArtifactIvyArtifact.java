/*
 * Copyright 2013 the original author or authors.
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

package org.gradle.api.publish.ivy.internal.artifact;

import org.gradle.api.Buildable;
import org.gradle.api.artifacts.PublishArtifact;
import org.gradle.api.tasks.TaskDependency;
import org.gradle.util.GUtil;

public class PublishArtifactIvyArtifact extends DefaultIvyArtifact implements Buildable {
    private final TaskDependency buildDependencies;

    public PublishArtifactIvyArtifact(PublishArtifact publishArtifact) {
        super(publishArtifact.getFile(), publishArtifact.getName(),
                emptyToNull(publishArtifact.getExtension()), emptyToNull(publishArtifact.getType()), emptyToNull(publishArtifact.getClassifier()));
        this.buildDependencies = publishArtifact.getBuildDependencies();
    }

    private static String emptyToNull(String value) {
        return GUtil.elvis(value, null);
    }

    public TaskDependency getBuildDependencies() {
        return buildDependencies;
    }
}

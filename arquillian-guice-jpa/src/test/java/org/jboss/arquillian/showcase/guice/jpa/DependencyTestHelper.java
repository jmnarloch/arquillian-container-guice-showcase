/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.showcase.guice.jpa;

import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

import java.io.File;

/**
 * <p>Resolves dependency within test.</p>
 *
 * @author <a href="mailto:jmnarloch@gmail.com">Jakub Narloch</a>
 */
public class DependencyTestHelper {

    /**
     * <p>Represents the Guice Persist extension artifact name.</p>
     */
    public static final String GUICE_PERSIST_ARTIFACT = "com.google.inject.extensions:guice-persist:3.0";

    /**
     * <p>Creates new instance of {@link DependencyTestHelper} class.</p>
     *
     * <p>Private constructor prevents instantiation outside this class.</p>
     */
    private DependencyTestHelper() {
        // empty constructor
    }

    /**
     * <p>Resolves Guice dependencies.</p>
     *
     * @return Guice dependencies files
     */
    public static File[] guiceDependencies() {
        
        return resolveArtifact(GUICE_PERSIST_ARTIFACT);
    }

    /**
     * Resolves the given artifact by it's name with help of maven build system.
     *
     * @param artifact the fully qualified artifact name
     *
     * @return the resolved files
     */
    private static File[] resolveArtifact(String artifact) {
        MavenDependencyResolver mvnResolver = DependencyResolvers.use(MavenDependencyResolver.class);

        mvnResolver.loadMetadataFromPom("pom.xml");

        return mvnResolver.artifacts(artifact).resolveAsFiles();
    }
}

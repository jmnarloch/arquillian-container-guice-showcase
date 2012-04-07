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
package org.jboss.arquillian.showcase.guice.testng;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import org.jboss.arquillian.showcase.guice.testng.repository.AppointmentRepository;
import org.jboss.arquillian.showcase.guice.testng.repository.impl.AppointmentRepositoryImpl;
import org.jboss.arquillian.showcase.guice.testng.service.AppointmentService;
import org.jboss.arquillian.showcase.guice.testng.service.impl.AppointmentServiceImpl;

/**
 * <p>Appointments module.</p>
 *
 * @author <a href="mailto:jmnarloch@gmail.com">Jakub Narloch</a>
 */
public class AppointmentModule implements Module {

    /**
     * Configures binder.
     *
     * @param binder the binder
     */
    public void configure(Binder binder) {

        binder.bind(AppointmentRepository.class)
                .annotatedWith(Names.named("appointmentRepository"))
                .to(AppointmentRepositoryImpl.class);
        binder.bind(AppointmentService.class)
                .annotatedWith(Names.named("appointmentService"))
                .to(AppointmentServiceImpl.class);
    }
}

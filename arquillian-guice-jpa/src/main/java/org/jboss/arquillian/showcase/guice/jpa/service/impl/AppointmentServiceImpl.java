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
package org.jboss.arquillian.showcase.guice.jpa.service.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;
import org.jboss.arquillian.showcase.guice.jpa.Appointment;
import org.jboss.arquillian.showcase.guice.jpa.repository.AppointmentRepository;
import org.jboss.arquillian.showcase.guice.jpa.service.AppointmentService;

import java.util.List;

/**
 * <p>Default implementation of {@link AppointmentService}.</p>
 *
 * @author <a href="mailto:jmnarloch@gmail.com">Jakub Narloch</a>
 */
public class AppointmentServiceImpl implements AppointmentService {

    /**
     * Instance of {@link AppointmentRepository} to which execution is delegated.
     */
    @Inject
    @Named("appointmentRepository")
    private AppointmentRepository appointmentRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(Appointment appointment) {

        appointmentRepository.add(appointment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Appointment> getAll() {

        return appointmentRepository.getAll();
    }
}

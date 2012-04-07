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
package org.jboss.arquillian.showcase.guice.inject.repository.impl;

import org.jboss.arquillian.showcase.guice.inject.Appointment;
import org.jboss.arquillian.showcase.guice.inject.repository.AppointmentRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Default implementation of {@link AppointmentRepository}.</p>
 *
 * @author <a href="mailto:jmnarloch@gmail.com">Jakub Narloch</a>
 */
public class AppointmentRepositoryImpl implements AppointmentRepository {

    /**
     * <p>Stores the appointments.</p>
     */
    private final List<Appointment> appointments = new ArrayList<Appointment>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Appointment appointment) {

        appointments.add(appointment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Appointment> getAll() {

        return new ArrayList<Appointment>(appointments);
    }
}

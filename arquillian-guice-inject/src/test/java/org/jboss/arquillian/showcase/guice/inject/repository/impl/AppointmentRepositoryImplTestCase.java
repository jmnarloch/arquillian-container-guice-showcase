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

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.guice.annotations.GuiceConfiguration;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.showcase.guice.inject.Appointment;
import org.jboss.arquillian.showcase.guice.inject.AppointmentModule;
import org.jboss.arquillian.showcase.guice.inject.repository.AppointmentRepository;
import org.jboss.arquillian.showcase.guice.inject.service.AppointmentService;
import org.jboss.arquillian.showcase.guice.inject.service.impl.AppointmentServiceImpl;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * <p>Tests the {@link AppointmentRepository} class.</p>
 *
 * @author <a href="mailto:jmnarloch@gmail.com">Jakub Narloch</a>
 */
@RunWith(Arquillian.class)
@GuiceConfiguration(AppointmentModule.class)
public class AppointmentRepositoryImplTestCase {

    /**
     * <p>Creates test deployment.</p>
     *
     * @return test deployment
     */
    @Deployment
    public static JavaArchive createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class, "guice-test.jar")
                .addClasses(Appointment.class,
                        AppointmentRepository.class, AppointmentRepositoryImpl.class,
                        AppointmentService.class, AppointmentServiceImpl.class,
                        AppointmentModule.class);
    }

    /**
     * <p>Injected {@link AppointmentService}.</p>
     */
    @Inject
    @Named("appointmentRepository")
    private AppointmentRepository appointmentRepository;

    /**
     * <p>Tests {@link AppointmentService#getAll()} method.</p>
     */
    @Test
    public void testGetAll() {

        Appointment appointment1 = createAppointment("Important", "Work", new Date());
        Appointment appointment2 = createAppointment("Do not forget", "Work", new Date());

        appointmentRepository.add(appointment1);
        appointmentRepository.add(appointment2);

        List<Appointment> result = appointmentRepository.getAll();
        assertNotNull("Method returned null.", result);
        assertEquals("Invalid element count, 2 appointments were expected.", 2, result.size());
    }

    /**
     * <p>Creates new appointment.</p>
     *
     * @param name     the appointment name
     * @param location the appointment location
     * @param date     the appointment date
     *
     * @return new appointment instance
     */
    private Appointment createAppointment(String name, String location, Date date) {

        Appointment appointment = new Appointment();
        appointment.setName(name);
        appointment.setLocation(location);
        appointment.setDate(date);
        return appointment;
    }
}

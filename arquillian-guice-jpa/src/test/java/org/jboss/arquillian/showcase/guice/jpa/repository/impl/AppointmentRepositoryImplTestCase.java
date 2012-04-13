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
package org.jboss.arquillian.showcase.guice.jpa.repository.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.guice.annotations.GuiceConfiguration;
import org.jboss.arquillian.guice.annotations.GuiceJpaPersistConfiguration;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.showcase.guice.jpa.Appointment;
import org.jboss.arquillian.showcase.guice.jpa.AppointmentModule;
import org.jboss.arquillian.showcase.guice.jpa.DependencyTestHelper;
import org.jboss.arquillian.showcase.guice.jpa.GuiceTestDataSourceEJB;
import org.jboss.arquillian.showcase.guice.jpa.repository.AppointmentRepository;
import org.jboss.arquillian.showcase.guice.jpa.service.AppointmentService;
import org.jboss.arquillian.showcase.guice.jpa.service.impl.AppointmentServiceImpl;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
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
@GuiceConfiguration({AppointmentModule.class})
@GuiceJpaPersistConfiguration(value = "GuiceJpaUnit", init = true)
public class AppointmentRepositoryImplTestCase {

    /**
     * <p>Creates test deployment.</p>
     *
     * @return test deployment
     */
    @Deployment
    public static WebArchive createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "guice-test.war")
                .addClasses(Appointment.class,
                        AppointmentRepository.class, AppointmentRepositoryImpl.class,
                        AppointmentService.class, AppointmentServiceImpl.class,
                        AppointmentModule.class,
                        GuiceTestDataSourceEJB.class)
                .addAsResource(AppointmentRepositoryImplTestCase.class.getClassLoader().getResource("persistence.xml"),
                        "META-INF/persistence.xml")
                .addAsLibraries(DependencyTestHelper.guiceDependencies());
    }

    /**
     * <p>Injected {@link AppointmentService}.</p>
     */
    @Inject
    @Named("appointmentRepository")
    private AppointmentRepository appointmentRepository;

    /**
     * <p>The injected entity manager.</p>
     */
    @Inject
    private EntityManager entityManager;

    /**
     * <p>Tests {@link AppointmentRepositoryImpl#add(Appointment)} method.</p>
     */
    public void testAdd() {
        List<Appointment> result;
        Appointment appointment1 = createAppointment("Important", "Work", new Date());
        Appointment appointment2 = createAppointment("Important", "Day of", new Date());

        try {
            entityManager.getTransaction().begin();

            appointmentRepository.add(appointment1);

            result = entityManager.createQuery("select app from Appointment app").getResultList();
            assertEquals("The Appointment entity hasn't been persisted.", 1, result.size());

            appointmentRepository.add(appointment2);

            result = entityManager.createQuery("select app from Appointment app").getResultList();
            assertEquals("The Appointment entity hasn't been persisted.", 1, result.size());
        } finally {
            // rollbacks the transaction
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * <p>Tests {@link AppointmentRepositoryImpl#getAll()} method.</p>
     */
    @Test
    public void testGetAll() {

        Appointment appointment1 = createAppointment("Important", "Work", new Date());
        Appointment appointment2 = createAppointment("Do not forget", "Work", new Date());

        entityManager.getTransaction().begin();

        try {
            appointmentRepository.add(appointment1);
            appointmentRepository.add(appointment2);

            List<Appointment> result = appointmentRepository.getAll();
            assertNotNull("Method returned null.", result);
            assertEquals("Invalid element count, 2 appointments were expected.", 2, result.size());
        } finally {
            // rollbacks the transaction
            entityManager.getTransaction().rollback();
        }
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

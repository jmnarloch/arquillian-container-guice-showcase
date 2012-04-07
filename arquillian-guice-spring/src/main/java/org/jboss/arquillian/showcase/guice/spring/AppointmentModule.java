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
package org.jboss.arquillian.showcase.guice.spring;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.spring.SpringIntegration;
import org.jboss.arquillian.showcase.guice.spring.repository.AppointmentRepository;
import org.jboss.arquillian.showcase.guice.spring.repository.impl.AppointmentRepositoryImpl;
import org.jboss.arquillian.showcase.guice.spring.service.AppointmentService;
import org.jboss.arquillian.showcase.guice.spring.service.impl.AppointmentServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.google.inject.spring.SpringIntegration.bindAll;
import static com.google.inject.spring.SpringIntegration.fromSpring;

/**
 * <p>Appointments module.</p>
 *
 * @author <a href="mailto:jmnarloch@gmail.com">Jakub Narloch</a>
 */
public class AppointmentModule implements Module {

    /**
     * <p>Configures binder.</p>
     *
     * @param binder the binder
     */
    public void configure(Binder binder) {

        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("WEB-INF/applicationContext.xml");

        binder.bind(BeanFactory.class).toInstance(applicationContext);
        binder.bind(AppointmentRepository.class)
                .toProvider(fromSpring(AppointmentRepositoryImpl.class, "appointmentRepository"));
        binder.bind(AppointmentService.class)
                .toProvider(fromSpring(AppointmentServiceImpl.class, "appointmentService"));
    }
}

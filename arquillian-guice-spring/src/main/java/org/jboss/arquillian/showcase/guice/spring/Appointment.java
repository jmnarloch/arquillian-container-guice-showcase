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

import java.util.Date;

/**
 * Appointment.
 *
 * @author <a href="mailto:jmnarloch@gmail.com">Jakub Narloch</a>
 */
public class Appointment {

    /**
     * Represents the appointment name.
     */
    private String name;

    /**
     * Represents the appointment location.
     */
    private String location;

    /**
     * Represents the appointment date.
     */
    private Date date;

    /**
     * <p>Creates new {@link Appointment} instance.</p>
     */
    public Appointment() {
        // empty constructor
    }

    /**
     * <p>Retrieves the appointment date.</p>
     *
     * @return the appointment date
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Sets the appointment date</p>
     *
     * @param name the appointment date
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>Retrieves the appointment location.</p>
     *
     * @return the appointment location
     */
    public String getLocation() {
        return location;
    }

    /**
     * <p>Sets the appointment location.</p>
     *
     * @param location the appointment location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * <p>Retrieves the appointment date.</p>
     *
     * @return the appointment date
     */
    public Date getDate() {
        return date;
    }

    /**
     * <p>Sets the appointment date.</p>
     *
     * @param date the appointment date
     */
    public void setDate(Date date) {
        this.date = date;
    }
}

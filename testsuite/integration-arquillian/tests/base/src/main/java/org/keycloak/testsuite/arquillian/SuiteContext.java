/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.keycloak.testsuite.arquillian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jboss.arquillian.container.spi.Container;
import static org.keycloak.testsuite.util.MailServerConfiguration.*;

/**
 *
 * @author tkyjovsk
 */
public final class SuiteContext {

    private final List<Container> arquillianContainers;

    private ContainerInfo authServerInfo;
    private final List<ContainerInfo> authServerBackendsInfo = new ArrayList<>();

    private ContainerInfo migratedAuthServerInfo;

    private boolean adminPasswordUpdated;
    private final Map<String, String> smtpServer = new HashMap<>();

    public SuiteContext(List<Container> arquillianContainers) {
        this.arquillianContainers = arquillianContainers;
        this.adminPasswordUpdated = false;
        smtpServer.put("from", FROM);
        smtpServer.put("host", HOST);
        smtpServer.put("port", PORT);
    }

    public boolean isAdminPasswordUpdated() {
        return adminPasswordUpdated;
    }

    public void setAdminPasswordUpdated(boolean adminPasswordUpdated) {
        this.adminPasswordUpdated = adminPasswordUpdated;
    }

    public Map<String, String> getSmtpServer() {
        return smtpServer;
    }

    public ContainerInfo getAuthServerInfo() {
        return authServerInfo;
    }

    public void setAuthServerInfo(ContainerInfo authServerInfo) {
        this.authServerInfo = authServerInfo;
    }

    public List<ContainerInfo> getAuthServerBackendsInfo() {
        return authServerBackendsInfo;
    }

    public ContainerInfo getMigratedAuthServerInfo() {
        return migratedAuthServerInfo;
    }

    public void setMigratedAuthServerInfo(ContainerInfo migratedAuthServerInfo) {
        this.migratedAuthServerInfo = migratedAuthServerInfo;
    }

    public boolean isAuthServerCluster() {
        return !authServerBackendsInfo.isEmpty();
    }

    public boolean isAuthServerMigrationEnabled() {
        return migratedAuthServerInfo != null;
    }

    public List<Container> getArquillianContainers() {
        return arquillianContainers;
    }

    @Override
    public String toString() {
        return "SUITE CONTEXT:\n"
                + "Auth server: " + authServerInfo.getQualifier() + "\n"
                +(isAuthServerCluster() ? "Auth server cluster: " + getAuthServerBackendsInfo().size() + " nodes+\n" : "");
    }

}

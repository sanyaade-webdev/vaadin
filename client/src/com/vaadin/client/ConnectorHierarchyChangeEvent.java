/*
 * Copyright 2000-2013 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.client;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.vaadin.client.ConnectorHierarchyChangeEvent.ConnectorHierarchyChangeHandler;
import com.vaadin.client.communication.AbstractServerConnectorEvent;

/**
 * Event for containing data related to a change in the {@link ServerConnector}
 * hierarchy. A {@link ConnectorHierarchyChangedEvent} is fired when an update
 * from the server has been fully processed and all hierarchy updates have been
 * completed.
 * 
 * @author Vaadin Ltd
 * @since 7.0.0
 * 
 */
public class ConnectorHierarchyChangeEvent extends
        AbstractServerConnectorEvent<ConnectorHierarchyChangeHandler> {
    /**
     * Type of this event, used by the event bus.
     */
    public static final Type<ConnectorHierarchyChangeHandler> TYPE = new Type<ConnectorHierarchyChangeHandler>();

    List<ComponentConnector> oldChildren;
    private HasComponentsConnector parent;

    public ConnectorHierarchyChangeEvent() {
    }

    /**
     * Returns a collection of the old children for the connector. This was the
     * state before the update was received from the server.
     * 
     * @return A collection of old child connectors. Never returns null.
     */
    public List<ComponentConnector> getOldChildren() {
        return oldChildren;
    }

    /**
     * Sets the collection of the old children for the connector.
     * 
     * @param oldChildren
     *            The old child connectors. Must not be null.
     */
    public void setOldChildren(List<ComponentConnector> oldChildren) {
        this.oldChildren = oldChildren;
    }

    /**
     * Returns the {@link HasComponentsConnector} for which this event
     * occurred.
     * 
     * @return The {@link HasComponentsConnector} whose child collection
     *         has changed. Never returns null.
     */
    public HasComponentsConnector getParent() {
        return parent;
    }

    /**
     * Sets the {@link HasComponentsConnector} for which this event
     * occurred.
     * 
     * @param The
     *            {@link HasComponentsConnector} whose child collection has
     *            changed.
     */
    public void setParent(HasComponentsConnector parent) {
        this.parent = parent;
    }

    public interface ConnectorHierarchyChangeHandler extends Serializable,
            EventHandler {
        public void onConnectorHierarchyChange(
                ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent);
    }

    @Override
    public void dispatch(ConnectorHierarchyChangeHandler handler) {
        handler.onConnectorHierarchyChange(this);
    }

    @Override
    public GwtEvent.Type<ConnectorHierarchyChangeHandler> getAssociatedType() {
        return TYPE;
    }

}
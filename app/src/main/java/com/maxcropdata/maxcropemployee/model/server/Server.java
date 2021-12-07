package com.maxcropdata.maxcropemployee.model.server;

import java.util.Objects;

public class Server {

    private String protocol;
    private String address;
    private String webserviceAddress;
    private String database;

    public Server() {

    }

    public Server(String protocol, String address, String webserviceAddress, String database) {
        this.protocol = protocol;
        this.address = address;
        this.webserviceAddress = webserviceAddress;
        this.database = database;
    }

    public Server(Builder builder) {
        this.protocol = builder.protocol;
        this.address = builder.address;
        this.webserviceAddress = builder.webserviceAddress;
        this.database = builder.database;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebserviceAddress() {
        return webserviceAddress;
    }

    public void setWebserviceAddress(String webserviceAddress) {
        this.webserviceAddress = webserviceAddress;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    @Override
    public String toString() {
        return this.protocol + "://" + this.address + "/" + this.webserviceAddress + "/";
    }

    public String stringForUpdate() {
        return this.protocol + "://" + this.address + "/";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Server server = (Server) o;
        return protocol.equals(server.protocol) &&
                address.equals(server.address) &&
                webserviceAddress.equals(server.webserviceAddress) &&
                database.equals(server.database);
    }

    @Override
    public int hashCode() {
        return Objects.hash(protocol, address, webserviceAddress, database);
    }

    public static class Builder {
        private String protocol;
        private String address;
        private String webserviceAddress;
        private String database;

        public Builder() {

        }

        public Builder protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder webserviceAddress(String webserviceAddress) {
            this.webserviceAddress = webserviceAddress;
            return this;
        }

        public Builder database(String database) {
            this.database = database;
            return this;
        }

        public Server build() {
            return new Server(this);
        }
    }
}


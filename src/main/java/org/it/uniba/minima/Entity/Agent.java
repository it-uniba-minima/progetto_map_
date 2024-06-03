package org.it.uniba.minima.Entity;

import org.it.uniba.minima.Database.DatabaseConnection;

import java.util.List;
import java.util.Objects;

public class Agent {
    private String name;
    private List<String> alias;

    public Agent() {
    }

    public String getName() {
        return name;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agent)) return false;
        Agent agent = (Agent) o;
        return Objects.equals(name, agent.name) &&
                Objects.equals(alias, agent.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, alias);
    }

    public void getDescription(Room room) {

    }
}


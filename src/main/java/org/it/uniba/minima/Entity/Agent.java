package org.it.uniba.minima.Entity;

import java.util.List;
import java.util.Objects;

public class Agent {
    private String name;
    private String description;
    private List<String> alias;

    public Agent() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agent)) return false;
        Agent agent = (Agent) o;
        return Objects.equals(name, agent.name) &&
                Objects.equals(description, agent.description) &&
                Objects.equals(alias, agent.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, alias);
    }
}


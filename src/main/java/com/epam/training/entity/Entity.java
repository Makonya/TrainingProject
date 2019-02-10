package com.epam.training.entity;

public abstract class Entity {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity that = (Entity) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}

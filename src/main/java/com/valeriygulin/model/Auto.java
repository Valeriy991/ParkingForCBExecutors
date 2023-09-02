package com.valeriygulin.model;

import java.util.Objects;

public class Auto {
    private long id;
    private int size;

    public Auto(long id, int size) {
        this.id = id;
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return id == auto.id && size == auto.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size);
    }

    @Override
    public String toString() {
        return "Auto{" +
                "id=" + id +
                ", size=" + size +
                '}';
    }
}

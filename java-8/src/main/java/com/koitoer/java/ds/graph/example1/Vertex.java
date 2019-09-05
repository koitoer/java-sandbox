package com.koitoer.java.ds.graph.example1;

import java.util.Objects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Vertex {

    private final String vertexName;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(vertexName, vertex.vertexName);
    }

    @Override public int hashCode() {
        return Objects.hash(vertexName);
    }
}

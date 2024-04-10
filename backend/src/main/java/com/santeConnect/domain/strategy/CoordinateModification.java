package com.santeConnect.domain.strategy;

import com.santeConnect.domain.entities.Coordinate;

public class CoordinateModification {
    private CoordinateModificationStrategy strategy;

    public void setStrategy(CoordinateModificationStrategy strategy) {
        this.strategy = strategy;
    }

    public void execute(Coordinate coordinate) {
        this.strategy.modify(coordinate);
    }
}

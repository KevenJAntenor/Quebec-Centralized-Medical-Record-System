package com.santeConnect.domain.strategy;

import com.santeConnect.domain.exception.*;
import com.santeConnect.domain.entities.Coordinate;

public interface CoordinateModificationStrategy {
    void modify(Coordinate coordinate) throws InvalidCoordinateException;
}

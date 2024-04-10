package com.santeConnect.domain.strategy;

import com.santeConnect.domain.entities.Coordinate;
import com.santeConnect.domain.exception.InvalidCoordinateException;

public class AdressModificationStrategy implements CoordinateModificationStrategy {
    private String newAdress;

    public AdressModificationStrategy(String newAdress) {
        this.newAdress = newAdress;
    }

    @Override
    public void modify(Coordinate coordinate) throws InvalidCoordinateException {
        if (newAdress.length() < 10)
            throw new InvalidCoordinateException();
        coordinate.setAddress(newAdress);
    }
}

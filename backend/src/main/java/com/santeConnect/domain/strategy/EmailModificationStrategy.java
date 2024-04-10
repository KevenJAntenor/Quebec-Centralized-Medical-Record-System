package com.santeConnect.domain.strategy;

import com.santeConnect.domain.entities.Coordinate;
import com.santeConnect.domain.exception.InvalidCoordinateException;

public class EmailModificationStrategy implements CoordinateModificationStrategy {
    final String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private String newEmail;

    public EmailModificationStrategy(String newEmail) {
        this.newEmail = newEmail;
    }

    @Override
    public void modify(Coordinate coordinate) throws InvalidCoordinateException {
        if (!newEmail.matches(regex))
            throw new InvalidCoordinateException();
        coordinate.setEmail(newEmail);
    }
}

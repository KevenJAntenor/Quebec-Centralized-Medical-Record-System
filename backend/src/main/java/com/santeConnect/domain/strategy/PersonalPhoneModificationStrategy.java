package com.santeConnect.domain.strategy;

import com.santeConnect.domain.entities.Coordinate;
import com.santeConnect.domain.exception.InvalidCoordinateException;

public class PersonalPhoneModificationStrategy implements CoordinateModificationStrategy {
    final String regex = "^[0-9]{3} [0-9]{3}-[0-9]{4}$";
    private String newPhone;

    public PersonalPhoneModificationStrategy(String newPhone) {
        this.newPhone = newPhone;
    }

    @Override
    public void modify(Coordinate coordinate) throws InvalidCoordinateException {
        if (!newPhone.matches(regex))
            throw new InvalidCoordinateException();
        coordinate.setPersonalPhoneNumber(newPhone);
    }
}

package org.smartfactory.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StationType {
    OPERATING("Operating", "performing a transforming action on a part"),
    MANUEL("Manuel", "manual operation performed by a human operator"),
    CONTROL("Control", "performing a measurement on a part");

    private String name;

    private String operationToThePart;

}

package org.smartfactory.model;

import java.util.List;

import org.smartfactory.util.StationType;

import lombok.Builder;

@Builder
public class Machine implements ExecutableUnit {
    List<Station> stationList;

    public void execute() {
        stationList.stream()
                .map(Station::getStationType)
                .map(StationType::getOperationToThePart)
                .forEach(System.out::println);
    }
}

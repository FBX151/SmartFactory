package org.smartfactory;

import java.util.List;

import org.smartfactory.model.Factory;
import org.smartfactory.model.Line;
import org.smartfactory.model.Machine;
import org.smartfactory.model.Station;
import org.smartfactory.util.StationType;

public class Main {

    /**
     * Main method serves as a tool to create sample Stations, Machines, Lines and Factory
     * This can be moved to the separate configuration which could then be consumed and used
     * to create all these units
     * @param args
     */
    public static void main(String[] args) {

        Station operatingStation = Station.builder()
                                           .stationType(StationType.OPERATING)
                                           .build();
        Station manuelStation = Station.builder()
                                           .stationType(StationType.MANUEL)
                                           .build();
        Station controlStation = Station.builder()
                                        .stationType(StationType.CONTROL)
                                        .build();
        Machine operatingAndManuelMachine = Machine.builder()
                                                    .stationList(List.of(operatingStation, manuelStation))
                                                    .build();

        Machine operatingAndControlMachine = Machine.builder()
                                                    .stationList(List.of(operatingStation, controlStation))
                                                    .build();

        Machine controlMachine = Machine.builder()
                                                     .stationList(List.of(controlStation))
                                                     .build();

        Machine manuelAndControlMachine = Machine.builder()
                                         .stationList(List.of(manuelStation, controlStation))
                                         .build();

        Line primaryProductionLine = Line.builder()
                                             .machineList(List.of(operatingAndManuelMachine, operatingAndControlMachine))
                                             .build();
        Line secondaryProductionLine = Line.builder()
                                             .machineList(List.of(manuelAndControlMachine, controlMachine))
                                             .build();
        Factory smartFactory = Factory.builder()
                                       .productionLines(List.of(primaryProductionLine, secondaryProductionLine))
                                       .build();
        smartFactory.execute();
    }

}

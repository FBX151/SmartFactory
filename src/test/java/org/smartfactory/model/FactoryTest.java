package org.smartfactory.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.smartfactory.util.StationType;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FactoryTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void execute() {
        Station operatingStation = Station
                .builder()
                .stationType(StationType.OPERATING)
                .build();
        Station manuelStation = Station
                .builder()
                .stationType(StationType.MANUEL)
                .build();
        Station controlStation = Station
                .builder()
                .stationType(StationType.CONTROL)
                .build();

        Machine operatingAndManuelMachine = Machine
                .builder()
                .stationList(List.of(operatingStation, manuelStation))
                .build();
        Machine manuelAndControlMachine = Machine
                .builder()
                .stationList(List.of(manuelStation, controlStation))
                .build();
        Machine controlMachine = Machine
                .builder()
                .stationList(List.of(controlStation))
                .build();
        Machine emptyMachine = Machine
                .builder()
                .stationList(null)
                .build();

        Line primaryLine = Line
                .builder()
                .machineList(List.of(operatingAndManuelMachine, manuelAndControlMachine))
                .build();
        Line secondaryLine = Line
                .builder()
                .machineList(List.of(manuelAndControlMachine,controlMachine))
                .build();
        Line thirdLine = Line
                .builder()
                .machineList(List.of(emptyMachine))
                .build();

        Factory factory = Factory
                .builder()
                .productionLines(List.of(primaryLine, secondaryLine))
                .build();
        Factory factory1 = Factory
                .builder()
                .productionLines(List.of(secondaryLine, thirdLine))
                .build();

        factory.execute();
        assertEquals("performing a transforming action on a part\n" +
                "manual operation performed by a human operator\n" +
                "manual operation performed by a human operator\n" +
                "performing a measurement on a part\n" +
                "manual operation performed by a human operator\n" +
                "performing a measurement on a part\n" +
                "performing a measurement on a part\n", outContent.toString());
        outContent.reset();

        factory1.execute();
        assertEquals("manual operation performed by a human operator\n" +
                "performing a measurement on a part\n" +
                "performing a measurement on a part\n", outContent.toString());


    }
}
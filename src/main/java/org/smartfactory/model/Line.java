package org.smartfactory.model;

import java.util.List;

import lombok.Builder;

@Builder
public class Line implements ExecutableUnit {
    private List<Machine> machineList;

    public void execute() {
        if(machineList != null){
            machineList.forEach(Machine::execute);
        }
    }
}

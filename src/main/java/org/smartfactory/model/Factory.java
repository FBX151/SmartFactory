package org.smartfactory.model;

import java.util.List;

import lombok.Builder;

@Builder
public class Factory implements ExecutableUnit {
    private List<Line> productionLines;

    public void execute() {
        productionLines.forEach(Line::execute);
    }
}

package org.smartfactory.model;

import org.smartfactory.util.StationType;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Station {
    StationType stationType;

}

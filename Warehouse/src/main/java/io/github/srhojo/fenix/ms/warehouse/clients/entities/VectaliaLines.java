package io.github.srhojo.fenix.ms.warehouse.clients.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties
public class VectaliaLines {

    private static final long serialVersionUID = 8929369726946355548L;

    private List<VectaliaLine> lines;

    public List<VectaliaLine> getLines() {
        return lines;
    }

    public void setLines(List<VectaliaLine> lines) {
        this.lines = lines;
    }
}

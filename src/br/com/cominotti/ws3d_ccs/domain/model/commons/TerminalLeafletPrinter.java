package br.com.cominotti.ws3d_ccs.domain.model.commons;

import ws3dproxy.model.Leaflet;

import java.util.logging.Logger;

public class TerminalLeafletPrinter implements LeafletPrinter {

    private static final Logger LOGGER = Logger.getLogger(TerminalLeafletPrinter.class.getName());


    @Override
    public void printLeaflet(Leaflet leaflet) {
        LOGGER.info(leaflet.toString());
    }
}

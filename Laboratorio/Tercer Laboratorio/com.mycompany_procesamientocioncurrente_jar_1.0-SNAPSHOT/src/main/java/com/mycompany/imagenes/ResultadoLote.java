package com.mycompany.imagenes;

import java.util.List;

/**
 *
 * @author Julio Blacio, Overnight Developers Squad, DCCO-ESPE
 */
public class ResultadoLote {
    public List<String> lineasDetalle;
    public long sumaTiempos;

    public ResultadoLote(List<String> lineasDetalle, long sumaTiempos) {
        this.lineasDetalle = lineasDetalle;
        this.sumaTiempos = sumaTiempos;
    }
}

package com.itsqmet.proyecto_f.Controller;

import com.itsqmet.proyecto_f.Service.ReporteService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }


    @GetMapping("/proyectos/{clienteId}")
    public ResponseEntity<byte[]> generarReporte(@PathVariable Long clienteId) {
        byte[] pdfBytes = reporteService.generarReporteProyectosPorCliente(clienteId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_proyectos.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}

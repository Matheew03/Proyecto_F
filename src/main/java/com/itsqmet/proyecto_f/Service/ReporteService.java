package com.itsqmet.proyecto_f.Service;

import com.itsqmet.proyecto_f.model.Cliente;
import com.itsqmet.proyecto_f.model.Proyecto;
import com.itsqmet.proyecto_f.repository.ClienteRepository;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ReporteService {

    private final ClienteRepository clienteRepository;

    public ReporteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public byte[] generarReporteProyectosPorCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            document.add(new Paragraph("Reporte de Proyectos del Cliente"));
            document.add(new Paragraph("Nombre: " + cliente.getNombre()));
            document.add(new Paragraph("Email: " + cliente.getEmail()));
            document.add(new Paragraph(" "));

            List<Proyecto> proyectos = cliente.getProyectos();
            for (Proyecto proyecto : proyectos) {
                document.add(new Paragraph("Proyecto: " + proyecto.getNombre()));
                document.add(new Paragraph("Descripción: " + proyecto.getDescripcion()));
                document.add(new Paragraph("-----------------------------"));
            }

            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }

        return baos.toByteArray();
    }
}

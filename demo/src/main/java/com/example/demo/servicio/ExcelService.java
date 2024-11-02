package com.example.demo.servicio;

import com.example.demo.model.Droga;
import com.example.demo.repositorio.DrogaRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Service
public class ExcelService {

    @Autowired
    private DrogaRepository drogaRepository;

    public List<Droga> cargarDrogasDesdeExcel() {
        try {
            List<Droga> drogas = new ArrayList<Droga>();
            InputStream inputStream = getClass().getResourceAsStream("/MEDICAMENTOS_VETERINARIA.xlsx");
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            // Saltar la primera fila (encabezados)
            if (rows.hasNext()) {
                rows.next();
            }

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                Droga droga = new Droga();

                droga.setNombre(getCellValueAsString(currentRow.getCell(0)));
                droga.setPrecioVenta(getCellValueAsFloat(currentRow.getCell(1)));
                droga.setPrecioCompra(getCellValueAsFloat(currentRow.getCell(2)));
                droga.setUnidadesDisponibles(getCellValueAsInteger(currentRow.getCell(3)));
                droga.setUnidadesVendidas(getCellValueAsInteger(currentRow.getCell(4)));

                drogas.add(droga);
            }

            workbook.close();
            return drogaRepository.saveAll(drogas);
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar el archivo Excel: " + e.getMessage());
        }
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return "";
        }
    }

    private Float getCellValueAsFloat(Cell cell) {
        if (cell == null) return 0.0f;
        return (float) cell.getNumericCellValue();
    }

    private Integer getCellValueAsInteger(Cell cell) {
        if (cell == null) return 0;
        return (int) cell.getNumericCellValue();
    }

    
}
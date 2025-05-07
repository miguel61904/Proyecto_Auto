package com.backend.sgd.controller;

import com.backend.sgd.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/excel")
public class CsvController {
    @Autowired
    private CsvService csvService;

    @PostMapping("importar")
    public ResponseEntity<String> importarCsv(@RequestParam("file") MultipartFile file) {
        try {
            csvService.importarCsv(file);
            return ResponseEntity.ok("Archivo importado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error de importación: " + e.getMessage());
        }
    }

}

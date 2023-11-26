package pe.edu.cibertec.DSWII_CL3_BeltranLopez.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.cibertec.DSWII_CL3_BeltranLopez.model.response.ResponseFile;
import pe.edu.cibertec.DSWII_CL3_BeltranLopez.service.FileService;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://www.cibertec.edu.pe")
@RequestMapping("api/v1/file")
public class FileController {
    private static final String IMAGES_DIRECTORY = "images";
    private static final String DOCUMENTS_DIRECTORY = "documentos";
    private static final long MAX_FILE_SIZE = 1_500_000; // 1.5MB

    @PostMapping("/filesimages")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        validateFileExtension(file, "png");
        return saveFile(file, IMAGES_DIRECTORY);
    }

    @PostMapping("/filesexcel")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
        validateFileExtension(file, "xlsx");
        validateFileSize(file, MAX_FILE_SIZE);
        return saveFile(file, DOCUMENTS_DIRECTORY);
    }

    private ResponseEntity<String> saveFile(MultipartFile file, String directory) throws IOException {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(file.getOriginalFilename())
                .toUriString();

        return ResponseEntity.ok("Archivo subido correctamente");
    }

    private void validateFileExtension(MultipartFile file, String allowedExtension) {
        if (!file.getOriginalFilename().toLowerCase().endsWith(allowedExtension)) {
            throw new IllegalArgumentException("Extensión de archivo no permitida");
        }
    }

    private void validateFileSize(MultipartFile file, long maxSize) {
        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException("Tamaño de archivo excede el límite permitido");
        }
    }
}

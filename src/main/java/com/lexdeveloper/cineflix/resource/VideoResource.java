package com.lexdeveloper.cineflix.resource;

import com.lexdeveloper.cineflix.config.VideoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class VideoResource {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String CONTENT_RANGE = "Content-Range";

    @Autowired
    private VideoConfig videoConfig;

    @GetMapping("/stream")
    public ResponseEntity<StreamingResponseBody> streamVideo(@RequestParam String filename,
                                                             @RequestHeader HttpHeaders headers) {
        Path path = Paths.get(videoConfig.getVideoDirectory()).resolve(filename).normalize();
        if (!Files.exists(path) || !path.startsWith(videoConfig.getVideoDirectory())) {
            return ResponseEntity.notFound().build();
        }

        long contentLength;
        try {
            contentLength = Files.size(path);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.valueOf("video/mp4"));

        if (headers.getRange().isEmpty()) {
            // Se não houver intervalo, envia o arquivo inteiro.
            responseHeaders.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(contentLength));
            return ResponseEntity.ok().headers(responseHeaders).body(outputStream -> {
                try (InputStream inputStream = Files.newInputStream(path)) {
                    byte[] buffer = new byte[16384]; // Aumentando o buffer para 16KB
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                } catch (IOException e) {
                    System.out.println("Erro ao transmitir o vídeo: " + e);
                }
            });
        } else {
            // Resposta com intervalo parcial.
            HttpRange range = headers.getRange().get(0);
            long start = range.getRangeStart(contentLength);
            long end = range.getRangeEnd(contentLength);

            if (start < 0 || end >= contentLength || start > end) {
                return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).build();
            }

            long contentRangeLength = end - start + 1;
            responseHeaders.add(HttpHeaders.CONTENT_RANGE, "bytes " + start + "-" + end + "/" + contentLength);
            responseHeaders.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(contentRangeLength));
            responseHeaders.add(HttpHeaders.ACCEPT_RANGES, "bytes");

            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).headers(responseHeaders).body(outputStream -> {
                try (InputStream inputStream = Files.newInputStream(path)) {
                    long skipped = 0;
                    while (skipped < start) {
                        long bytes = inputStream.skip(start - skipped);
                        if (bytes == 0) break; // Se não conseguir pular, o arquivo é muito pequeno ou a posição de início é errada.
                        skipped += bytes;
                    }
                    byte[] buffer = new byte[16384]; // Aumentando o buffer para 16KB
                    long bytesSent = 0;
                    while (bytesSent < contentRangeLength) {
                        int bytesRead = inputStream.read(buffer, 0, (int) Math.min(buffer.length, contentRangeLength - bytesSent));
                        if (bytesRead == -1) break;
                        outputStream.write(buffer, 0, bytesRead);
                        bytesSent += bytesRead;
                    }
                } catch (IOException e) {
                    System.out.println("Erro ao transmitir o vídeo: " + e);
                }
            });
        }
    }
}

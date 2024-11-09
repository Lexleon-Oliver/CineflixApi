package com.lexdeveloper.cineflix.resource;

import com.lexdeveloper.cineflix.config.VideoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
                                                             @RequestHeader HttpHeaders headers) throws IOException {
        Path path = Paths.get(videoConfig.getVideoDirectory()).resolve(filename).normalize();
        if (!Files.exists(path) || !path.startsWith(videoConfig.getVideoDirectory())) {
            return ResponseEntity.notFound().build();
        }
        long contentLength = Files.size(path);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "video/mp4");

        if (headers.getRange().isEmpty()) {
            responseHeaders.add(CONTENT_TYPE, "video/mp4");
            responseHeaders.add(CONTENT_LENGTH, String.valueOf(contentLength));
            return ResponseEntity.ok().headers(responseHeaders).body(outputStream -> {
                try (InputStream inputStream = Files.newInputStream(path)) {
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            });
        } else {
            HttpRange range = headers.getRange().get(0);
            long start = range.getRangeStart(contentLength);
            long end = range.getRangeEnd(contentLength);

            if (start < 0 || end >= contentLength || start > end) {
                return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).build();
            }

            responseHeaders.add(CONTENT_TYPE, "video/mp4");
            responseHeaders.add(CONTENT_RANGE, "bytes " + start + "-" + end + "/" + contentLength);
            responseHeaders.add(CONTENT_LENGTH, String.valueOf(end - start + 1));

            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).headers(responseHeaders).body(outputStream -> {
                try (InputStream inputStream = Files.newInputStream(path)) {
                    inputStream.skip(start);
                    byte[] buffer = new byte[8192];
                    long bytesToSend = end - start + 1;
                    long bytesSent = 0;
                    while (bytesSent < bytesToSend) {
                        int bytesRead = inputStream.read(buffer, 0, (int) Math.min(buffer.length, bytesToSend - bytesSent));
                        if (bytesRead == -1) break;
                        outputStream.write(buffer, 0, bytesRead);
                        bytesSent += bytesRead;
                    }
                }
            });
        }
    }
}

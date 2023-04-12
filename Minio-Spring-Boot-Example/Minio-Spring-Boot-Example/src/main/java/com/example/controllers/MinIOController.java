package com.example.controllers;

import com.example.beans.FileBean;
import com.example.services.MinioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.servlet.HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE;

@RestController
@RequestMapping("/api/minio")
public class MinIOController {
    @Autowired
    private MinioService minioService;

    /**
     * GetFiles
     *
     * @return ResponseEntity<?>
     */
    @GetMapping
    public ResponseEntity<?> getFiles() {
        try {
            return minioService.getListObjects();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Upload
     *
     * @param fileBean
     * @return ResponseEntity<?>
     */
    @PostMapping
    public ResponseEntity<?> upload(@ModelAttribute FileBean fileBean) {
        try {
            return minioService.uploadFile(fileBean);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GetFile
     *
     * @param request
     * @return ResponseEntity<?>
     */
    @GetMapping("/**")
    public ResponseEntity<?> getFile(HttpServletRequest request) {
        try {
            String pattern = (String) request.getAttribute(BEST_MATCHING_PATTERN_ATTRIBUTE);
            String filename = new AntPathMatcher().extractPathWithinPattern(pattern, request.getServletPath());
            return minioService.getObject(filename);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * DeleteFile
     *
     * @param request
     * @return ResponseEntity<Object>
     */
    @DeleteMapping("/**")
    public ResponseEntity<Object> deleteFile(HttpServletRequest request) {
        Map<String, String> reuslt = new HashMap<>();
        try {
            String pattern = (String) request.getAttribute(BEST_MATCHING_PATTERN_ATTRIBUTE);
            String filename = new AntPathMatcher().extractPathWithinPattern(pattern, request.getServletPath());
            boolean del = minioService.deleteObject(filename);
            if (del) {
                reuslt.put("msg", "Delete successful");
                return ResponseEntity.ok().body(reuslt);
            } else {
                reuslt.put("msg", "Delete failed");
                return ResponseEntity.badRequest().body(reuslt);
            }
        } catch (Exception e) {
            reuslt.put("msg", "Delete failed");
            return ResponseEntity.badRequest().body(reuslt);
        }
    }
}

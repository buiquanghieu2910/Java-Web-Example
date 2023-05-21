/**
 * BUI_QUANG_HIEU, 2023
 * TestRestController.java
 */
package com.example.testspringnginx.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BUI_QUANG_HIEU
 */
@RestController
@RequestMapping("/api/test")
public class TestRestController {
    @GetMapping
    public ResponseEntity<?> test() {
        Map<String, String> map = new HashMap<>();
        map.put("fullname", "Bùi Quang Hiếu");
        return ResponseEntity.ok(map);
    }
}

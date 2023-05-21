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
    @GetMapping("client")
    public ResponseEntity<?> client() {
        Map<String, String> map = new HashMap<>();
        map.put("fullname", "Bùi Quang Hiếu - Client");
        return ResponseEntity.ok(map);
    }

    @GetMapping("admin")
    public ResponseEntity<?> admin() {
        Map<String, String> map = new HashMap<>();
        map.put("fullname", "Bùi Quang Hiếu - Admin");
        return ResponseEntity.ok(map);
    }
}

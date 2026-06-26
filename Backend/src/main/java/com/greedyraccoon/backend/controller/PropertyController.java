package com.greedyraccoon.backend.controller;

import com.greedyraccoon.backend.dto.PropertyRequest;
import com.greedyraccoon.backend.dto.PropertyResponse;
import com.greedyraccoon.backend.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PropertyResponse> createProperty(
            @RequestBody PropertyRequest request,
            Principal principal
    ) {
        // principal.getName() holds the email extracted from the JWT token
        return ResponseEntity.ok(propertyService.createProperty(request, principal.getName()));
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponse>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }
}
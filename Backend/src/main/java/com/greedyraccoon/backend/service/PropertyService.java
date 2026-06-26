package com.greedyraccoon.backend.service;

import com.greedyraccoon.backend.dto.PropertyRequest;
import com.greedyraccoon.backend.dto.PropertyResponse;
import com.greedyraccoon.backend.model.Property;
import com.greedyraccoon.backend.model.User;
import com.greedyraccoon.backend.repository.PropertyRepository; // Ensure this import is present!
import com.greedyraccoon.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    public PropertyResponse createProperty(PropertyRequest request, String agentEmail) {
        User agent = userRepository.findByEmail(agentEmail)
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        Property property = new Property();
        property.setTitle(request.title());
        property.setDescription(request.description());
        property.setType(Property.PropertyType.valueOf(request.type().toUpperCase()));
        property.setStatus(Property.PropertyStatus.valueOf(request.status().toUpperCase()));
        property.setPrice(request.price());
        property.setLocation(request.location());
        property.setBedrooms(request.bedrooms());
        property.setArea(request.area());
        property.setImageUrl(request.imageUrl());

        property.setAgent(agent);

        Property savedProperty = propertyRepository.save(property);

        return mapToResponse(savedProperty);
    }

    public List<PropertyResponse> getAllProperties() {
        return propertyRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private PropertyResponse mapToResponse(Property property) {
        return new PropertyResponse(
                property.getId(),
                property.getTitle(),
                property.getType().name(),
                property.getStatus().name(),
                property.getPrice(),
                property.getLocation(),
                property.getAgent().getName()
        );
    }
}
package com.rentatools.infrastructure.common.security.dtos;

import java.util.List;
import java.util.UUID;

public record LoginResponse(
        UUID userId,
        String email,
        List<String> roles,
        String accessToken,
        String refreshToken,
        String tokenType
) {
}

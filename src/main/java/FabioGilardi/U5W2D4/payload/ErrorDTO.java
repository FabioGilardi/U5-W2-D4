package FabioGilardi.U5W2D4.payload;

import java.time.LocalDateTime;

public record ErrorDTO(
        String message,
        LocalDateTime timestamp
) {
}

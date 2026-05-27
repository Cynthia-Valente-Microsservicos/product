package store.product;

import lombok.Builder;

@Builder
public record ProductOut(
    String id,
    String name,
    Float price,
    String unit
) {
    
}

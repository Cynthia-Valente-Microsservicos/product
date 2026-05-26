package store.product;

import lombok.Builder;

@Builder
public record ProductOut(
    Integer id,
    String name,
    Float price,
    String unit
) {
    
}

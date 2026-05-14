package store.product;

import lombok.Builder;

@Builder
public record ProductIn (
    String name,
    Float price,
    String unit
    
){

}

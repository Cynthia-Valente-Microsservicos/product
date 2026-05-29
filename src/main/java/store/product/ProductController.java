package store.product;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "product", url = "http://product:8080")
public interface ProductController {

    @PostMapping("/products")
    public ResponseEntity<Void> create(
        @RequestBody ProductIn in,
        @RequestHeader("role") String role
    );

    @GetMapping("/products")
    public ResponseEntity<List<ProductOut>> findAll();
    
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductOut> findById(
        @PathVariable String id
    );

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable String id,
        @RequestHeader("role") String role
    );
    
    @GetMapping("/products/health-check")
    public ResponseEntity<Void> healthCheck();
}


package com.example.catalog;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
    record Product(long id, String name, double price){}
    private final Map<Long, Product> store = new HashMap<>();
    private long seq = 0;
    record CreateReq(String name, double price){}
    @PostMapping("/products")
    public Product create(@RequestBody CreateReq req){ long id = ++seq; Product p = new Product(id, req.name(), req.price()); store.put(id, p); return p; }
    @GetMapping("/products")
    public List<Product> list(){ return new ArrayList<>(store.values()); }
}

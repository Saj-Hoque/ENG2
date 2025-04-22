package uk.ac.york.eng2.products.resources;


import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Arrays;
import java.util.List;

@Controller("/products")
public class ProductsController {

    @Get("/names")
    public List<String> getNames() {
        return Arrays.asList("name 1", "name 2");
    }


}

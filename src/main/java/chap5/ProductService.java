package chap5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
    Map<String, Product> products = new HashMap<>();

    public ProductService() {
        Product p =new Product("101","아이폰 12",120000,"애플","24.01.01");
        products.put("101",p);
        p =new Product("102","갤럭시",150000,"삼성","24.03.01");
        products.put("102",p);
        p =new Product("103","엘지폰",100000,"엘지","24.02.01");
        products.put("103",p);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product find(String id) {
        return products.get(id);
    }
}

package by.mysecurityspringgit.controllers;

import by.mysecurityspringgit.entities.Product;
import by.mysecurityspringgit.security.UsersDetails;
import by.mysecurityspringgit.services.ProductsService;
import by.mysecurityspringgit.repositories.specifications.ProductSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

//ссылка на html deleteproduct.html потому что @RequestMapping("/products")
// <a href="http://localhost:8188/ls14/products/deleteproduct">deleteproduct open page</a>
@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProductsList(Model model) {
        Product product = new Product();
        model.addAttribute("products", productsService.getAllIProducts()); //getAllProducts());
        model.addAttribute("product", product);
        return "products";
    }

    @GetMapping("/deleteproduct")
    public String showProductsListDelete(Model model) {
        Product product = new Product();
        model.addAttribute("products", productsService.getAllIProducts()); //getAllProducts());
        model.addAttribute("product", product);
        return "deleteproduct";
    }

    @GetMapping("/add")
    public String AddProduct(Model model) { //edit
        Product product = new Product();
        model.addAttribute("products", productsService.getAllIProducts()); //getAllProducts());
        model.addAttribute("product", product);
        return "redirect:/products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product")Product product) {
        productsService.adddb(product); //add(product);
        return "redirect:/products";

    }

    @GetMapping("/addeditproduct")
    public String showEditAddProductForm(Model model) { //edit
        Product product =  new Product();
        model.addAttribute(product);
        return "product-edit";
    }
    @GetMapping("/edit/{id}")
    public String showEditProductForm(Model model,@PathVariable(value = "id" ) Long id ) { //edit
        Product product =  productsService.getById(id);
        model.addAttribute(product);
        return "product-edit";
    }

    /*<p>
    <td>
        <a href=@{"/products/filtr"}>Filtr product element</a>
    </td>*/
    //filtr word ,required = false -может быть или не быть не обязателен
    @GetMapping("/filtr")
    public String showListProductFiltrForm(Model model,
                                           @RequestParam(value = "word"     ,required = false) String word,
                                           @RequestParam(value = "minPrice",required = false) BigDecimal minPrice, // с формы может прийти  необязательный параметр  int minPrice
                                           @RequestParam(value = "maxPrice",required = false) BigDecimal maxPrice
                                         ) {
         Specification<Product> specification =  Specification.where(null);

        //работа  фильтров спецификаций
        if (word != null){
            specification = specification.and(ProductSpecs.titleContains(word));

        }
        if ( minPrice != null ){
            specification = specification.and(ProductSpecs.priceGreatThenOrEq(minPrice));
         }
        if ( maxPrice != null ){
            specification = specification.and(ProductSpecs.priceLessThenOrEq(maxPrice));
        }

        Product product = new Product();
        model.addAttribute("products" ,productsService.getProductWithPagingFiltr(specification, PageRequest.of(0,100)).getContent()); //нулевая страница на 100 товаров  //productsService.getdbAllProductsFiltr(word));  //getAllProductsFiltr(word));
        //model.addAttribute("product"  ,productsService.getdbAllProductsFiltr(word));
        model.addAttribute("product"  ,product);
        model.addAttribute("word"     ,word  );// прокидываем  на фронтэнд
        model.addAttribute("minPrice"  ,minPrice);
        model.addAttribute("maxPrice"  ,maxPrice  );// прокидываем  на фронтэнд
        return "products-filtr"; //"products";
    }

    @PostMapping("/edit")
    public String addEditProduct(@ModelAttribute(value = "product") Product product) {
        productsService.addEdit(product); // добавление нового если по id
        return "redirect:/products";
    }

   /* @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product")Product product) {
        productsService.add(product);
        return "redirect:/products";
    }*/

    @GetMapping ("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        productsService.deleteProductdb(id);//deleteProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Optional<Product> product = productsService.getIById(id); //getById(id);
        model.addAttribute("product", product) ;
        return "product-page";
    }

}

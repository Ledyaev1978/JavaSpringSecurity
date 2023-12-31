package by.mysecurityspringgit.repositories;

import by.mysecurityspringgit.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

;

@Repository
public interface IProductREpository extends JpaSpecificationExecutor <Product> ,
                                            PagingAndSortingRepository <Product,Long>,
                                            JpaRepository <Product,Long> {
    List<Product> findByTitle(String title);
}

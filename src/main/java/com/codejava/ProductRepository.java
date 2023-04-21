package com.codejava;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
	public Page<Product> findAll(String keyword, Pageable pageable);
// 
//	@Query(value = "SELECT * FROM Product  WHERE Product.name =:keyword and Product.price BETWEEN :min and :max", nativeQuery = true)
// 	public Page<Product> getAllProduct(String keyword, @Param(value = "min") Integer o, @Param(value = "max") Integer p,
//			Pageable pageable);

	public Page<Product> findByNameAndPriceGreaterThanEqualAndPriceLessThanEqual(String keyword, Integer min,
			Integer max, Pageable pageable);
 

	public Page<Product> findAllByNameContainingAndPriceBetween(String keyword, Integer min, Integer max, Pageable pageable);
}

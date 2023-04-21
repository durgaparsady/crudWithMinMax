package com.codejava;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductRepository repo;

	public Page<Product> listAll(int pageNo, String sortField, String sortDir, String keyword, Integer min,
			Integer max) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, 5, sort);

		System.out.println(min);
		System.out.println(max);
		System.out.println("keyword is " + keyword);
		if (!keyword.isBlank()) {
			System.out.println("this iskey wird");
			return repo.findByNameAndPriceGreaterThanEqualAndPriceLessThanEqual(keyword, min, max, pageable);

		}
		if(min!=null && max!=null) {
			System.out.println("this is min max");
			return repo.findAllByNameContainingAndPriceBetween(keyword, min, max, pageable);
		 
		}
		return repo.findAll(pageable);
	}

	public void save(Product product) {
		repo.save(product);
	}

	public Product get(long id) {
		return repo.findById(id).get();
	}

	public Product update(UpdateRequest rrequest, HttpSession httpSession) throws IOException {
		Product request = get(rrequest.getId());
		if (request != null) {
			request.setName(rrequest.getName());
			request.setBrand(rrequest.getBrand());
			request.setPrice(rrequest.getPrice());
			if (!rrequest.getImage().isEmpty()) {
				String img = ProductImageData.ImageUploade(rrequest.getImage(), httpSession);
				request.setFileName(img);
				request.setImage(rrequest.getImage().getBytes());
			}
			return repo.save(request);
		}
		return null;
	}

	public void delete(long id) {
		repo.deleteById(id);
	}

	public void addImage(AddRequest addRequest, String filename, byte[] data) {
		Product request = new Product();

		request.setName(addRequest.getName());
		request.setBrand(addRequest.getBrand());
		request.setPrice(addRequest.getPrice());
		request.setFileName(filename);
		request.setImage(data);

		repo.save(request);
	}

}

package com.codejava;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EntityService {

//    @Autowired
//    private ProductRepository entityRepository;
//
//    public  List<Product>  findEntitiesByFields(String field1, String field2, int field3) {
//        return entityRepository.findAll((Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            if (field1 != null && !field1.isEmpty()) {
//                predicates.add(cb.like(root.get("field1"), "%" + field1 + "%"));
//            }
//
//            if (field2 != null && !field2.isEmpty()) {
//                predicates.add(cb.equal(root.get("field2"), field2));
//            }
//
//            if (field3 > 0) {
//                predicates.add(cb.greaterThan(root.get("field3"), field3));
//            }
//
//            return cb.and(predicates.toArray(new Predicate[0]));
//        });
//    }
}

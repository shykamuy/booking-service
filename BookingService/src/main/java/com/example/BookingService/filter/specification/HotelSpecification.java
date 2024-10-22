package com.example.BookingService.filter.specification;

import com.example.BookingService.model.entity.Hotel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HotelSpecification {

    public static Specification<Hotel> filterByCriteria(Long id, String name, String article, String city,
                                                        String address, Float distanceFromCenter, Float rating, Integer reviewQuantity) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), id));
            }

            if (name != null && !name.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (article != null && !article.trim().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("article"), article));
            }

            if (city != null && !city.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("city")), "%" + city.toLowerCase() + "%"));
            }

            if (address != null && !address.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + address.toLowerCase() + "%"));
            }

            if (distanceFromCenter != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("distanceFromCenter"), distanceFromCenter));
            }

            if (rating != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), rating));
            }

            if (reviewQuantity != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("reviewQuantity"), reviewQuantity));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}

package com.example.BookingService.filter.specification;

import com.example.BookingService.model.entity.Room;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class RoomSpecification {

    public static Specification<Room> filterByCriteria(Long id, String name, Float minPrice, Float maxPrice,
                                                       Integer personQuantity, Date arrivalDate, Date departureDate,
                                                       Long hotelId) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (id != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), id));
            }

            if (name != null && !name.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }

            if (minPrice != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.ge(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.le(root.get("price"), maxPrice));
            }

            if (personQuantity != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("personQuantity"), personQuantity));
            }

            if (hotelId != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("hotel").get("id"), hotelId));
            }

            if (arrivalDate != null || departureDate != null) {
                Subquery<Date> subquery = query.subquery(Date.class);
                Root<Room> subRoot = subquery.from(Room.class);
                subquery.select(subRoot.join("notAvailableDates"))
                        .where(criteriaBuilder.or(
                                (arrivalDate != null) ? criteriaBuilder.equal(subRoot.join("notAvailableDates"), arrivalDate) : criteriaBuilder.conjunction(),
                                (departureDate != null) ? criteriaBuilder.equal(subRoot.join("notAvailableDates"), departureDate) : criteriaBuilder.conjunction()
                        ));

                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.not(criteriaBuilder.exists(subquery)));
            }

            return predicate;
        };
    }
}
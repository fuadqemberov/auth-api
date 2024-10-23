package az.company.auth_api.search;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class GenericSpecifications {

    private static <T> Specification<T> createSpecification(Object value, Specification<T> specification) {
        return (value == null) ? Specification.where(null) : specification;
    }

    public static <T> Specification<T> stringEquals(String fieldName, String value) {
        return createSpecification(value, (root, query, cb) -> cb.equal(root.get(fieldName), value));
    }

    public static <T> Specification<T> stringContains(String fieldName, String value) {
        return createSpecification(value, (root, query, cb) ->
                cb.like(cb.lower(root.get(fieldName)), "%" + value.toLowerCase() + "%"));
    }

    public static <T> Specification<T> booleanEquals(String fieldName, Boolean value) {
        return createSpecification(value, (root, query, cb) -> cb.equal(root.get(fieldName), value));
    }

    public static <T> Specification<T> numberEquals(String fieldName, Double value) {
        return createSpecification(value, (root, query, cb) -> cb.equal(root.get(fieldName), value));
    }

    public static <T> Specification<T> numberGreaterThan(String fieldName, Double value) {
        return createSpecification(value, (root, query, cb) -> cb.gt(root.get(fieldName), value));
    }

    public static <T> Specification<T> numberLessThan(String fieldName, Double value) {
        return createSpecification(value, (root, query, cb) -> cb.lt(root.get(fieldName), value));
    }

    public static <T> Specification<T> dateEquals(String fieldName, LocalDate value) {
        return createSpecification(value, (root, query, cb) -> cb.equal(root.get(fieldName), value));
    }

    public static <T> Specification<T> dateAfter(String fieldName, LocalDate value) {
        return createSpecification(value, (root, query, cb) -> cb.greaterThan(root.get(fieldName), value));
    }

    public static <T> Specification<T> dateBefore(String fieldName, LocalDate value) {
        return createSpecification(value, (root, query, cb) -> cb.lessThan(root.get(fieldName), value));
    }

    public static <T> Specification<T> joinAttributeContains(String joinAttribute, String fieldName, String value) {
        return createSpecification(value, (root, query, cb) -> {
            Join<T, ?> join = root.join(joinAttribute);
            return cb.like(cb.lower(join.get(fieldName)), "%" + value.toLowerCase() + "%");
        });
    }
}

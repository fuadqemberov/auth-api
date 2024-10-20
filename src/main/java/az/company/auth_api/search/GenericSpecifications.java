package az.company.auth_api.search;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class GenericSpecifications {

    public static <T> Specification<T> stringEquals(String fieldName, String value) {
        if(value != null){
            return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                    value == null ? cb.isNull(root.get(fieldName)) : cb.equal(root.get(fieldName), value);
        }
        else {
            return null;
        }
    }

    public static <T> Specification<T> stringContains(String fieldName, String value) {
        if(value != null) {
            return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                    value == null ? null : cb.like(cb.lower(root.get(fieldName)), "%" + value.toLowerCase() + "%");
        }
        else {
            return null;
        }
    }

    public static <T> Specification<T> booleanEquals(String fieldName, Boolean value) {
        return (Root<T> root,CriteriaQuery<?> query, CriteriaBuilder cb) ->
                value == null ? cb.isNull(root.get(fieldName)) : cb.equal(root.get(fieldName), value);
    }

    public static <T> Specification<T> numberEquals(String fieldName, Number value) {
        if(value != null) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                value == null ? cb.isNull(root.get(fieldName)) : cb.equal(root.get(fieldName), value);

    } else {
            return null;
        }
    }

    public static <T> Specification<T> numberGreaterThan(String fieldName, Number value) {
        return (Root<T> root,CriteriaQuery<?> query, CriteriaBuilder cb) ->
                value == null ? null : cb.gt(root.get(fieldName), value);
    }

    public static <T> Specification<T> numberLessThan(String fieldName, Number value) {
        return (Root<T> root,CriteriaQuery<?> query, CriteriaBuilder cb) ->
                value == null ? null : cb.lt(root.get(fieldName), value);
    }

    public static <T> Specification<T> dateEquals(String fieldName, LocalDate value) {
        return (Root<T> root,CriteriaQuery<?> query, CriteriaBuilder cb) ->
                value == null ? cb.isNull(root.get(fieldName)) : cb.equal(root.get(fieldName), value);
    }

    public static <T> Specification<T> dateAfter(String fieldName, LocalDate value) {
        return (Root<T> root,CriteriaQuery<?> query, CriteriaBuilder cb) ->
                value == null ? null : cb.greaterThan(root.get(fieldName), value);
    }

    public static <T> Specification<T> dateBefore(String fieldName, LocalDate value) {
        return (Root<T> root,CriteriaQuery<?> query, CriteriaBuilder cb) ->
                value == null ? null : cb.lessThan(root.get(fieldName), value);
    }

    public static <T> Specification<T> joinAttributeContains(String joinAttribute, String fieldName, String value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }
            Join<T, ?> join = root.join(joinAttribute);
            return cb.like(cb.lower(join.get(fieldName)), "%" + value.toLowerCase() + "%");
        };
    }

}
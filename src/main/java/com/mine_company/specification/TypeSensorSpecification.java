package com.mine_company.specification;

import com.mine_company.entity.TypeSensor;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TypeSensorSpecification {

    public static Specification<TypeSensor> filter(TypeSensor filter){

        return(root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(filter.getName() != null && !filter.getName().isBlank()){
                predicates.add(
                        cb.like(
                                cb.lower((root.get("name"))),
                                "%" + filter.getName().toLowerCase() + "%"
                        )
                );
            }


            if(filter.getDescription() != null && !filter.getDescription().isBlank()){
                predicates.add(
                        cb.like(
                                cb.lower((root.get("description"))),
                                "%" + filter.getDescription().toLowerCase() + "%"
                        )
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

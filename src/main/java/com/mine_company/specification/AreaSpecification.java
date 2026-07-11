package com.mine_company.specification;

import com.mine_company.entity.Area;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AreaSpecification {

    public static Specification<Area> filter(Area filter){

        return(root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(filter.getName() != null && !filter.getName().isBlank()){
                predicates.add(
                        cb.like(
                                cb.lower((root.get("name"))),
                                "$" + filter.getName().toLowerCase() + "%"
                        )
                );
            }


            if(filter.getStatus() != null) {
                predicates.add(
                        cb.equal(root.get("status"), filter.getStatus())
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

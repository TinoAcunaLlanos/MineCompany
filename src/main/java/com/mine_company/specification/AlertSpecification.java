package com.mine_company.specification;

import com.mine_company.dto.AlertDTO;
import com.mine_company.entity.Alert;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AlertSpecification {

    @Qualifier("alertMapper")
    private static ModelMapper mapper;

    public static Specification<Alert> filter(AlertDTO dto) {


        Alert filter = mapper.map(dto, Alert.class);

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filter.getId() != null) {
                predicates.add(
                        cb.like(
                                cb.lower((root.get("id"))),
                                "%" + filter.getId() + "%"
                        )
                );
            }

            if (dto.getIdMeasurement() != null) {
                predicates.add(
                        cb.like(
                                cb.lower((root.get("Measurement"))),
                                "%" + filter.getMeasurement().getId() + "%"
                        )
                );
            }

            if (dto.getDateTimeCreatedBeginAt() != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("createdAt"),
                                dto.getDateTimeCreatedBeginAt()
                        )
                );
            }

            if (dto.getDateTimeCreatedEndAt() != null) {
                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get("createdAt"),
                                dto.getDateTimeCreatedEndAt()
                        )
                );
            }

            if (dto.getDateTimeResolvedBeginAt() != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("resolvedAt"),
                                dto.getDateTimeResolvedBeginAt()
                        )
                );
            }

            if (dto.getDateTimeResolvedBeginAt() != null) {
                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get("resolvedAt"),
                                dto.getDateTimeResolvedEndAt()
                        )
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

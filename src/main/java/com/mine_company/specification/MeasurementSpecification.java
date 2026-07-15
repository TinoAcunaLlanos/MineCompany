package com.mine_company.specification;

import com.mine_company.dto.MeasurementDTO;
import com.mine_company.entity.Measurement;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MeasurementSpecification {

    @Qualifier("measurementMapper")
    private static ModelMapper mapper;

    public static Specification<Measurement> filter(MeasurementDTO dto) {


        Measurement filter = mapper.map(dto, Measurement.class);

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

            if (dto.getIdSensor() != null) {
                predicates.add(
                        cb.like(
                                cb.lower((root.get("idSensor"))),
                                "%" + filter.getSensor().getId() + "%"
                        )
                );
            }

            if (dto.getDateTimeBeginAt() != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("measuredAt"),
                                dto.getDateTimeBeginAt()
                        )
                );
            }

            if (dto.getDateTimeEndAt() != null) {
                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get("measuredAt"),
                                dto.getDateTimeEndAt()
                        )
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

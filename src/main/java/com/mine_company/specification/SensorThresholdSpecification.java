package com.mine_company.specification;

import com.mine_company.entity.SensorThreshold;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SensorThresholdSpecification {

    @Qualifier("industrialAssetMapper")
    private static ModelMapper mapper;

    public static Specification<SensorThreshold> filter(SensorThreshold dto) {


        SensorThreshold filter = mapper.map(dto, SensorThreshold.class);

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            /*if (filter.getName() != null && !filter.getName().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower((root.get("name"))),
                                "%" + filter.getName().toLowerCase() + "%"
                        )
                );
            }

            if (filter.getTypeAsset().getId() != null) {
                predicates.add(
                        cb.like(
                                cb.lower((root.get("idTypeAsset"))),
                                "%" + filter.getTypeAsset().getId() + "%"
                        )
                );
            }

            if (filter.getArea().getId() != null) {
                predicates.add(
                        cb.like(
                                cb.lower((root.get("idArea"))),
                                "%" + filter.getArea().getId() + "%"
                        )
                );
            }

            if (filter.getAssetCode() != null && !filter.getAssetCode().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower((root.get("assetCode"))),
                                "%" + filter.getAssetCode().toLowerCase() + "%"
                        )
                );
            }

            if (filter.getAssetCode() != null && !filter.getAssetCode().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower((root.get("assetCode"))),
                                "%" + filter.getAssetCode().toLowerCase() + "%"
                        )
                );
            }

            if (filter.getSerialNumber() != null && !filter.getSerialNumber().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower((root.get("serialNumber"))),
                                "%" + filter.getSerialNumber().toLowerCase() + "%"
                        )
                );
            }

            if (filter.getDescription() != null && !filter.getDescription().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower((root.get("description"))),
                                "%" + filter.getDescription().toLowerCase() + "%"
                        )
                );
            }

            if (dto.getDateTimeBegin() != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("installedAt"),
                                dto.getDateTimeBegin()
                        )
                );
            }

            if (dto.getDateTimeEnd() != null) {
                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get("installedAt"),
                                dto.getDateTimeEnd()
                        )
                );
            }*/

            if (filter.getStatus() != null) {
                predicates.add(
                        cb.like(
                                cb.lower((root.get("status"))),
                                "%" + filter.getStatus() + "%"
                        )
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

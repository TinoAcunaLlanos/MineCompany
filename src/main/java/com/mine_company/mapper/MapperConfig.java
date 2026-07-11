package com.mine_company.mapper;

import com.mine_company.dto.AreaDTO;
import com.mine_company.dto.IndustrialAssertDTO;
import com.mine_company.dto.PlantDTO;
import com.mine_company.entity.Area;
import com.mine_company.entity.IndustrialAssert;
import com.mine_company.entity.Plant;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("mineMapper")
    public ModelMapper mineMapper(){
        return new ModelMapper();
    }

    @Bean("plantMapper")
    public ModelMapper plantMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<PlantDTO, Plant>typeMap =mapper.createTypeMap(PlantDTO.class, Plant.class);
        typeMap.addMapping(PlantDTO::getIdMine, (dest, v)-> dest.getMine().setId((Integer)v));
        return mapper;
    }

    @Bean("areaMapper")
    public ModelMapper areaMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<AreaDTO, Area>typeMap =mapper.createTypeMap(AreaDTO.class, Area.class);
        typeMap.addMapping(AreaDTO::getIdPlant, (dest, v)-> dest.getPlant().setId((Integer)v));
        return mapper;
    }

    @Bean("typeAssertmapper")
    public ModelMapper typeAssert(){
        return new ModelMapper();
    }

    @Bean("industrialAssertMapper")
    public ModelMapper industrialAssertMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<IndustrialAssertDTO, IndustrialAssert>typeMap =mapper.createTypeMap(IndustrialAssertDTO.class, IndustrialAssert.class);
        typeMap.addMapping(IndustrialAssertDTO::getIdTypeAssert, (dest, v)-> dest.getTypeAssert().setId((Integer) v));
        typeMap.addMapping(IndustrialAssertDTO::getIdArea, (dest, v)-> dest.getArea().setId((Integer) v));
        return mapper;
    }
}

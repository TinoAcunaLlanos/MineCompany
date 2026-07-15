package com.mine_company.mapper;

import com.mine_company.dto.*;
import com.mine_company.entity.*;
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

    @Bean("typeAssertMapper")
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

    @Bean("typeSensorMapper")
    public ModelMapper typeSensor(){
        return new ModelMapper();
    }

    @Bean("sensorMapper")
    public ModelMapper sensorMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<SensorDTO, Sensor>typeMap =mapper.createTypeMap(SensorDTO.class, Sensor.class);
        typeMap.addMapping(SensorDTO::getIdTypeSensor, (dest, v)-> dest.getTypeSensor().setId((Integer) v));
        typeMap.addMapping(SensorDTO::getIdIndustrialAssert, (dest, v)-> dest.getIndustrialAssert().setId((Integer) v));
        return mapper;
    }

    @Bean("SensorThresholdMapper")
    public ModelMapper sensorThresholdMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<SensorThresholdDTO, SensorThreshold>typeMap =mapper.createTypeMap(SensorThresholdDTO.class, SensorThreshold.class);
        typeMap.addMapping(SensorThresholdDTO::getIdSensor, (dest, v)-> dest.getSensor().setId((Integer)v));
        return mapper;
    }

    @Bean("measurementMapper")
    public ModelMapper measurementMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<MeasurementDTO, Measurement>typeMap =mapper.createTypeMap(MeasurementDTO.class, Measurement.class);
        typeMap.addMapping(MeasurementDTO::getIdSensor, (dest, v)-> dest.getSensor().setId((Integer)v));
        return mapper;
    }

    @Bean("alertMapper")
    public ModelMapper alertMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<AlertDTO, Alert>typeMap =mapper.createTypeMap(AlertDTO.class, Alert.class);
        typeMap.addMapping(AlertDTO::getIdMeasurement, (dest, v)-> dest.getMeasurement().setId((Integer)v));
        return mapper;
    }
}

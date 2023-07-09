package com.example.demo.mapstruct;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapStruct {
    EmployeeMapStruct INSTANCE = Mappers.getMapper( EmployeeMapStruct.class );

        EmployeeDTO enitityToDto(Employee e);

        Employee dtoToEntity(EmployeeDTO eDto);
}

package com.sti.facturacion.model.mappers;

import com.sti.facturacion.dto.CustomerDto;
import com.sti.facturacion.model.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer dtoToCustomer(CustomerDto dto);

    CustomerDto custumerToDto(Customer customer);

    List<Customer> dtoToCustomer(List<CustomerDto> dtos);

    List<CustomerDto> custumerToDto(List<Customer> customers);
}

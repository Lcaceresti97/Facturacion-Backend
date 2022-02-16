package com.sti.facturacion.model.mappers;

import com.sti.facturacion.dto.CustomerDto;
import com.sti.facturacion.dto.CustomerDto.CustomerDtoBuilder;
import com.sti.facturacion.model.Customer;
import com.sti.facturacion.model.Customer.CustomerBuilder;
import com.sti.facturacion.model.Invoice;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-16T15:44:19-0600",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer dtoToCustomer(CustomerDto dto) {
        if ( dto == null ) {
            return null;
        }

        CustomerBuilder customer = Customer.builder();

        customer.customerId( dto.getCustomerId() );
        customer.customerName( dto.getCustomerName() );
        customer.customerAddress( dto.getCustomerAddress() );
        customer.customerPhone( dto.getCustomerPhone() );
        customer.customerStatus( dto.getCustomerStatus() );
        List<Invoice> list = dto.getCustomerInvoice();
        if ( list != null ) {
            customer.customerInvoice( new ArrayList<Invoice>( list ) );
        }

        return customer.build();
    }

    @Override
    public CustomerDto custumerToDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDtoBuilder customerDto = CustomerDto.builder();

        customerDto.customerId( customer.getCustomerId() );
        customerDto.customerName( customer.getCustomerName() );
        customerDto.customerAddress( customer.getCustomerAddress() );
        customerDto.customerPhone( customer.getCustomerPhone() );
        customerDto.customerStatus( customer.getCustomerStatus() );
        List<Invoice> list = customer.getCustomerInvoice();
        if ( list != null ) {
            customerDto.customerInvoice( new ArrayList<Invoice>( list ) );
        }

        return customerDto.build();
    }

    @Override
    public List<Customer> dtoToCustomer(List<CustomerDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Customer> list = new ArrayList<Customer>( dtos.size() );
        for ( CustomerDto customerDto : dtos ) {
            list.add( dtoToCustomer( customerDto ) );
        }

        return list;
    }

    @Override
    public List<CustomerDto> custumerToDto(List<Customer> customers) {
        if ( customers == null ) {
            return null;
        }

        List<CustomerDto> list = new ArrayList<CustomerDto>( customers.size() );
        for ( Customer customer : customers ) {
            list.add( custumerToDto( customer ) );
        }

        return list;
    }
}

package com.sti.facturacion.service.implementation;

import com.sti.facturacion.dto.CustomerDto;
import com.sti.facturacion.dto.InvoiceDto;
import com.sti.facturacion.exception.CustomerNotFoundException;
import com.sti.facturacion.model.Customer;
import com.sti.facturacion.model.Invoice;
import com.sti.facturacion.model.mappers.CustomerMapper;
import com.sti.facturacion.model.mappers.InvoiceMapper;
import com.sti.facturacion.model.status.ModelStatus;
import com.sti.facturacion.repository.CustomerRepository;
import com.sti.facturacion.service.CustomerService;
import com.sti.facturacion.service.InvoiceService;
import com.sti.facturacion.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final InvoiceMapper invoiceMapper;

    private final InvoiceService invoiceService;

    private final SortingPagingUtils sortingPagingUtils;


   @Override
   public CustomerDto saveCustomer(final CustomerDto customerDto){
       Customer customer = Customer
               .buildFromDtoCustomer(this.customerMapper.dtoToCustomer(customerDto));
       this.customerRepository.save(customer);
       return customerMapper.custumerToDto(customer);
   }

    @Override
    public Page<CustomerDto> findPaginatedSortedCustomer(String customerId, int page, int size, String[] sort) {

       List<Sort.Order> orders = sortingPagingUtils.getSortOrders(sort);
       Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
       List<CustomerDto> customerDto;

       if(customerId == null){
           customerDto = customerMapper.custumerToDto(customerRepository.findAll(pageable).toList());

       } else {
           customerDto = customerMapper.custumerToDto(customerRepository.findByCustomerIdContaining(customerId,pageable).toList());
       }
       return new PageImpl<>(customerDto);
    }

    @Override
    public CustomerDto findCustomerById(final String customerId)  {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> CustomerNotFoundException
                        .buildCustomerNotFoundExceptionForId(customerId));
        return customerMapper
                .custumerToDto(
                        isActiveCustomer(customer, "customerId", customerId));
    }

    @Override
    public CustomerDto findCustomerByName(final String customerName) throws CustomerNotFoundException {

        Customer customer = this.customerRepository.findByCustomerName(customerName)
                .orElseThrow(() -> CustomerNotFoundException
                        .buildCustomerNotFoundExceptionForField("customerName", customerName));
        return customerMapper
                .custumerToDto(
                        isActiveCustomer(customer, "customerName", customerName));
    }

    @Override
    public void deleteCustomerById(String customerId) {
        Customer customer = customerMapper.dtoToCustomer(findCustomerById(customerId));
        customer.setCustomerStatus(ModelStatus.INACTIVE);
        customerRepository.save(customer);
    }


    /**
     * Return customer if status code is ACTIVE.
     * @param customer Customer
     * @param queryField String
     * @param queryFieldValue String
     * @return Customer
     * @throws CustomerNotFoundException ex
     */
    private Customer isActiveCustomer(Customer customer, String queryField, String queryFieldValue){
        if(customer.getCustomerStatus().getStatusCode() == 0){
            return customer;
        }
        throw CustomerNotFoundException
                .buildCustomerNotFoundExceptionForField(queryField, queryFieldValue);
    }
}

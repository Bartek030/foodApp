package pl.bartek030.foodApp.infrastructure.database.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartek030.foodApp.business.serviceModel.OrderDetails;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.OrderDetailsDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.OrderDetailsJpaRepository;
import pl.bartek030.foodApp.util.OrderDetailsEntityExample;
import pl.bartek030.foodApp.util.OrderDetailsExample;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderDetailsRepositoryTest {

    @InjectMocks
    private OrderDetailsRepository orderDetailsRepository;

    @Mock
    private OrderDetailsJpaRepository orderDetailsJpaRepository;

    @Mock
    private OrderDetailsDaoMapper orderDetailsDaoMapper;

    @Test
    void addAllOrderDetails() {
        // given
        final List<OrderDetails> orderDetails = List.of(
                OrderDetailsExample.someOrderDetails1(),
                OrderDetailsExample.someOrderDetails2(),
                OrderDetailsExample.someOrderDetails3()
        );

        when(orderDetailsDaoMapper.mapOrderDetailsToEntity(any(OrderDetails.class)))
                .thenReturn(OrderDetailsEntityExample.someOrderDetailsEntity1())
                .thenReturn(OrderDetailsEntityExample.someOrderDetailsEntity2())
                .thenReturn(OrderDetailsEntityExample.someOrderDetailsEntity3());
        when(orderDetailsJpaRepository.saveAll(anyList())).thenReturn(List.of());

        // when
        orderDetailsRepository.addAllOrderDetails(orderDetails);

        // then
        Mockito.verify(orderDetailsDaoMapper, times(3)).mapOrderDetailsToEntity(any(OrderDetails.class));
    }

}
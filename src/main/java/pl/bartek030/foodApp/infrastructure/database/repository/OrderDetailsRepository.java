package pl.bartek030.foodApp.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.dao.OrderDetailsDAO;
import pl.bartek030.foodApp.business.serviceModel.OrderDetails;
import pl.bartek030.foodApp.infrastructure.database.entity.OrderDetailsEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.OrderDetailsDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.OrderDetailsJpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class OrderDetailsRepository implements OrderDetailsDAO {

    private final OrderDetailsJpaRepository orderDetailsJpaRepository;
    private final OrderDetailsDaoMapper orderDetailsDaoMapper;

    @Override
    public void addAllOrderDetails(final List<OrderDetails> orderDetailsList) {
        orderDetailsJpaRepository.saveAll(
                orderDetailsList.stream()
                        .map(orderDetailsDaoMapper::mapOrderDetailsToEntity)
                        .toList()
        );
    }
}

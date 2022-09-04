package com.manriquecms.warehouse.service.aggregator;

import com.manriquecms.warehouse.domain.dto.OrderDto;
import com.manriquecms.warehouse.domain.dto.ProductAvailableDto;
import com.manriquecms.warehouse.domain.model.OrderProduct;
import com.manriquecms.warehouse.domain.model.Product;
import com.manriquecms.warehouse.domain.model.WarehouseOrder;
import com.manriquecms.warehouse.infrastructure.repository.ArticleRepository;
import com.manriquecms.warehouse.infrastructure.repository.OrderRepository;
import com.manriquecms.warehouse.infrastructure.repository.ProductRepository;
import com.manriquecms.warehouse.service.command.CreateOrderCommand;
import com.manriquecms.warehouse.service.exception.OrderWithNotEnoughStockException;
import com.manriquecms.warehouse.service.query.ArticleQuery;
import com.manriquecms.warehouse.service.query.ProductsAvailableQuery;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(
        isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.SUPPORTS,
        readOnly = false,
        timeout = 30)
public class OrderAggregator {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductsAvailableQuery productsAvailableQuery;
    @Autowired
    ArticleQuery articleQuery;
    @Autowired
    ArticleAggregator articleAggregator;

    public OrderDto handleCreateOrderCommand(List<CreateOrderCommand> createOrderCommands)
            throws OrderWithNotEnoughStockException {
        WarehouseOrder warehouseOrder = orderRepository.save(new WarehouseOrder());

        createOrderCommands.stream().forEach(createOrderCommand ->
                {
                    Integer productQuantity = createOrderCommand.getQuantity();
                    Product product = productRepository.findById(createOrderCommand.getProductId()).orElseThrow();
                    ProductAvailableDto available = productsAvailableQuery.getAvailableProduct(product.getName());
                    if (available.getQuantity() >= productQuantity){
                        warehouseOrder.addProduct(product,productQuantity);
                        product.reduceStockForArticles(productQuantity);
                        productRepository.save(product);
                        warehouseOrder.sumFee(Money.parse(product.getPrice()).multiply(productQuantity).toString());
                    } else {
                        throw new OrderWithNotEnoughStockException();
                    }
                }
        );

        orderRepository.save(warehouseOrder);

        return OrderDto.fromOrder(warehouseOrder);
    }
}

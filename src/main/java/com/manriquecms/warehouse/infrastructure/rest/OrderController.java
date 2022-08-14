package com.manriquecms.warehouse.infrastructure.rest;

import com.manriquecms.warehouse.domain.model.product.Product;
import com.manriquecms.warehouse.domain.model.product.ProductOrder;
import com.manriquecms.warehouse.service.aggregator.OrderAggregator;
import com.manriquecms.warehouse.service.command.CreateOrderCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    @Autowired
    OrderAggregator orderAggregator;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public List<Product> orderProducts(@RequestBody List<CreateOrderCommand> createOrderCommands){
        return createOrderCommands.stream().map(createOrderCommand -> {
            return orderAggregator.handleCreateOrderCommand(createOrderCommand);
        }).collect(Collectors.toList());
    }
}

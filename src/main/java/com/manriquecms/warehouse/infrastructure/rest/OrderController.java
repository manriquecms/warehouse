package com.manriquecms.warehouse.infrastructure.rest;

import com.manriquecms.warehouse.service.aggregator.OrderAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderAggregator orderAggregator;

    /*@RequestMapping(value = "/order", method = RequestMethod.POST)
    public List<ProductOrder> orderProducts(@RequestBody List<CreateOrderCommand> createOrderCommands)
            throws OrderWithNotEnoughStockException {
        return createOrderCommands.stream().map(createOrderCommand -> {
            return orderAggregator.handleCreateOrderCommand(createOrderCommand);
        }).collect(Collectors.toList());
    }*/
}

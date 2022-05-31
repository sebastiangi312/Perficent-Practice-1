package com.perficient.praxis.gildedrose.handler;

import com.perficient.praxis.gildedrose.business.model.Item;
import com.perficient.praxis.gildedrose.business.services.QualityService;
import com.perficient.praxis.gildedrose.controller.factory.ItemRequestFactory;
import com.perficient.praxis.gildedrose.controller.model.ItemRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QualityServiceHandler {

    private ItemRequestFactory itemRequestFactory;

    private QualityService qualityService;

    public QualityServiceHandler(ItemRequestFactory itemRequestFactory, QualityService qualityService) {
        this.itemRequestFactory = itemRequestFactory;
        this.qualityService = qualityService;
    }

    public List<ItemRequest> updateQuality() {
        return transformListItemIntoListItemRequest(qualityService.updateQuality());
    }

    private List<ItemRequest> transformListItemIntoListItemRequest(List<Item> items){
        return items.stream().map(item -> itemRequestFactory.createItemRequestFromItem(item)).collect(Collectors.toList());
    }
}

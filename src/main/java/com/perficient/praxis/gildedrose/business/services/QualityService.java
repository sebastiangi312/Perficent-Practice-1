package com.perficient.praxis.gildedrose.business.services;

import com.perficient.praxis.gildedrose.handler.RepositoryHandler;
import com.perficient.praxis.gildedrose.business.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QualityService {

    private final RepositoryHandler repositoryHandler;

    public QualityService(RepositoryHandler repositoryHandler) {
        this.repositoryHandler = repositoryHandler;
    }

    public List<Item> updateQuality() {
        var items = repositoryHandler.listItems();
        for(Item item : items){
            item.updateQuality();
        }
        return items;
    }

}
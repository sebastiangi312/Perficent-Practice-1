package com.perficient.praxis.gildedrose.business;

import com.perficient.praxis.gildedrose.controller.model.ItemRequest;
import com.perficient.praxis.gildedrose.handler.QualityServiceHandler;
import com.perficient.praxis.gildedrose.repository.ItemRepository;
import com.perficient.praxis.gildedrose.repository.model.ItemStored;
import com.perficient.praxis.gildedrose.type.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest

public class QualityServiceTest {
    
        @MockBean
        private ItemRepository itemRepository;

        @Autowired
        private QualityServiceHandler qualityServiceHandler;

    @Test
    /**
     * GIVEN a valid normal type item in the database
     * WHEN updateQuality method is called
     * THEN the service should update the quality and sellIn values,
     * both will be decreased by 1
     */
    public void testUpdateQualityOfNormalTypeItemWhenSellInIs10(){

        var item = new ItemStored(0, "Oreo", 10, 30, Type.NORMAL);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<ItemRequest> itemsUpdated = qualityServiceHandler.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(9, itemsUpdated.get(0).sellIn);
        assertEquals(29, itemsUpdated.get(0).quality);
        assertEquals(Type.NORMAL, itemsUpdated.get(0).type);
    }

    @Test
    public void testUpdateQualityOfNormalTypeItemWhenSellInIsZeroAndQualityIsZero(){

        var item = new ItemStored(0, "Oreo", 0, 0, Type.NORMAL);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<ItemRequest> itemsUpdated = qualityServiceHandler.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(-1, itemsUpdated.get(0).sellIn);
        assertEquals(0, itemsUpdated.get(0).quality);
        assertEquals(Type.NORMAL, itemsUpdated.get(0).type);
    }

    @Test
    public void testUpdateQualityOfNormalTypeItemWhenSellLessThanZero(){

        var item = new ItemStored(0, "Oreo", -1, 30, Type.NORMAL);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<ItemRequest> itemsUpdated = qualityServiceHandler.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(-2, itemsUpdated.get(0).sellIn);
        assertEquals(28, itemsUpdated.get(0).quality);
        assertEquals(Type.NORMAL, itemsUpdated.get(0).type);
    }

    @Test
    public void testUpdateQualityOfAgedTypeItemWhenSellInIs10(){

        var item = new ItemStored(0, "Oreo", 10, 30, Type.AGED);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<ItemRequest> itemsUpdated = qualityServiceHandler.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(9, itemsUpdated.get(0).sellIn);
        assertEquals(31, itemsUpdated.get(0).quality);
        assertEquals(Type.AGED, itemsUpdated.get(0).type);
    }

    @Test
    public void testUpdateQualityOfAgedTypeItemWhenSellInLessThanZero(){

        var item = new ItemStored(0, "Oreo", -1, 30, Type.AGED);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<ItemRequest> itemsUpdated = qualityServiceHandler.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(-2, itemsUpdated.get(0).sellIn);
        assertEquals(32, itemsUpdated.get(0).quality);
        assertEquals(Type.AGED, itemsUpdated.get(0).type);
    }

    @Test
    public void testUpdateQualityOfTicketsTypeItemWhenSellInIsTwelve(){

        var item = new ItemStored(0, "Oreo", 12, 30, Type.TICKETS);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<ItemRequest> itemsUpdated = qualityServiceHandler.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(11, itemsUpdated.get(0).sellIn);
        assertEquals(31, itemsUpdated.get(0).quality);
        assertEquals(Type.TICKETS, itemsUpdated.get(0).type);
    }

    @Test
    public void testUpdateQualityOfTicketsTypeItemWhenSellInIsSeven(){

        var item = new ItemStored(0, "Oreo", 7, 30, Type.TICKETS);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<ItemRequest> itemsUpdated = qualityServiceHandler.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(6, itemsUpdated.get(0).sellIn);
        assertEquals(32, itemsUpdated.get(0).quality);
        assertEquals(Type.TICKETS, itemsUpdated.get(0).type);
    }


    @Test
    public void testUpdateQualityOfTicketsTypeItemWhenSellInIsFive(){

        var item = new ItemStored(0, "Oreo", 6, 30, Type.TICKETS);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<ItemRequest> itemsUpdated = qualityServiceHandler.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(5, itemsUpdated.get(0).sellIn);
        assertEquals(33, itemsUpdated.get(0).quality);
        assertEquals(Type.TICKETS, itemsUpdated.get(0).type);
    }

    @Test
    public void testUpdateQualityOfTicketsTypeItemWhenSellInIsZero(){

        var item = new ItemStored(0, "Oreo", 0, 30, Type.TICKETS);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<ItemRequest> itemsUpdated = qualityServiceHandler.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(-1, itemsUpdated.get(0).sellIn);
        assertEquals(0, itemsUpdated.get(0).quality);
        assertEquals(Type.TICKETS, itemsUpdated.get(0).type);
    }

    @Test
    public void testUpdateQualityOfLegendaryTypeItem(){

        var item = new ItemStored(0, "Oreo", 10, 30, Type.LEGENDARY);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<ItemRequest> itemsUpdated = qualityServiceHandler.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(10, itemsUpdated.get(0).sellIn);
        assertEquals(30, itemsUpdated.get(0).quality);
        assertEquals(Type.LEGENDARY, itemsUpdated.get(0).type);
    }

    }

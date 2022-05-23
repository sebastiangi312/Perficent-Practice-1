package com.perficient.praxis.gildedrose.business;

import com.perficient.praxis.gildedrose.factory.ItemFactory;
import com.perficient.praxis.gildedrose.model.Item;
import com.perficient.praxis.gildedrose.repository.ItemRepository;
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
        private ItemFactory itemFactory;

        @Autowired
        private QualityService qualityService;

    @Test
    /**
     * GIVEN a valid normal type item in the database
     * WHEN updateQuality method is called
     * THEN the service should update the quality and sellIn values,
     * both will be decreased by 1
     */
    public void testUpdateQualityOfNormalTypeItemWhenSellInIs10(){

        var item = itemFactory.createItem(0, "Oreo", 10, 30, ItemFactory.Type.NORMAL);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = qualityService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(9, itemsUpdated.get(0).sellIn);
        assertEquals(29, itemsUpdated.get(0).quality);
        assertEquals(ItemFactory.Type.NORMAL, itemsUpdated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }

@Test
    public void testUpdateQualityOfNormalTypeItemWhenSellInIsZeroAndQualityIsZero(){

        var item = itemFactory.createItem(0, "Oreo", 0, 0, ItemFactory.Type.NORMAL);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = qualityService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(-1, itemsUpdated.get(0).sellIn);
        assertEquals(0, itemsUpdated.get(0).quality);
        assertEquals(ItemFactory.Type.NORMAL, itemsUpdated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }

    @Test
    public void testUpdateQualityOfNormalTypeItemWhenSellLessThanZero(){

        var item = itemFactory.createItem(0, "Oreo", -1, 30, ItemFactory.Type.NORMAL);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = qualityService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(-2, itemsUpdated.get(0).sellIn);
        assertEquals(28, itemsUpdated.get(0).quality);
        assertEquals(ItemFactory.Type.NORMAL, itemsUpdated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }

    @Test
    public void testUpdateQualityOfAgedTypeItemWhenSellInIs10(){

        var item = itemFactory.createItem(0, "Oreo", 10, 30, ItemFactory.Type.AGED);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = qualityService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(9, itemsUpdated.get(0).sellIn);
        assertEquals(31, itemsUpdated.get(0).quality);
        assertEquals(ItemFactory.Type.AGED, itemsUpdated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }

    @Test
    public void testUpdateQualityOfAgedTypeItemWhenSellInLessThanZero(){

        var item = itemFactory.createItem(0, "Oreo", -1, 30, ItemFactory.Type.AGED);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = qualityService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(-2, itemsUpdated.get(0).sellIn);
        assertEquals(32, itemsUpdated.get(0).quality);
        assertEquals(ItemFactory.Type.AGED, itemsUpdated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }

    @Test
    public void testUpdateQualityOfTicketsTypeItemWhenSellInIsTwelve(){

        var item = itemFactory.createItem(0, "Oreo", 12, 30, ItemFactory.Type.TICKETS);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = qualityService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(11, itemsUpdated.get(0).sellIn);
        assertEquals(31, itemsUpdated.get(0).quality);
        assertEquals(ItemFactory.Type.TICKETS, itemsUpdated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }

    @Test
    public void testUpdateQualityOfTicketsTypeItemWhenSellInIsSeven(){

        var item = itemFactory.createItem(0, "Oreo", 7, 30, ItemFactory.Type.TICKETS);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = qualityService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(6, itemsUpdated.get(0).sellIn);
        assertEquals(32, itemsUpdated.get(0).quality);
        assertEquals(ItemFactory.Type.TICKETS, itemsUpdated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }


    @Test
    public void testUpdateQualityOfTicketsTypeItemWhenSellInIsFive(){

        var item = itemFactory.createItem(0, "Oreo", 6, 30, ItemFactory.Type.TICKETS);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = qualityService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(5, itemsUpdated.get(0).sellIn);
        assertEquals(33, itemsUpdated.get(0).quality);
        assertEquals(ItemFactory.Type.TICKETS, itemsUpdated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }

    @Test
    public void testUpdateQualityOfTicketsTypeItemWhenSellInIsZero(){

        var item = itemFactory.createItem(0, "Oreo", 0, 30, ItemFactory.Type.TICKETS);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = qualityService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(-1, itemsUpdated.get(0).sellIn);
        assertEquals(0, itemsUpdated.get(0).quality);
        assertEquals(ItemFactory.Type.TICKETS, itemsUpdated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }

    @Test
    public void testUpdateQualityOfLegendaryTypeItem(){

        var item = itemFactory.createItem(0, "Oreo", 10, 30, ItemFactory.Type.LEGENDARY);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = qualityService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(10, itemsUpdated.get(0).sellIn);
        assertEquals(30, itemsUpdated.get(0).quality);
        assertEquals(ItemFactory.Type.LEGENDARY, itemsUpdated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }

    }

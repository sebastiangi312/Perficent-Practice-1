package com.perficient.praxis.gildedrose.business;

import com.perficient.praxis.gildedrose.error.ResourceNotFoundException;
import com.perficient.praxis.gildedrose.model.Item;
import com.perficient.praxis.gildedrose.repository.ItemRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ItemServiceTest {

    @MockBean
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;


    @Test
    public void testGetItemByIdWhenItemWasNotFound(){

        when(itemRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                itemService.findById(0));
    }

    @Test
    public void testGetItemByIdSuccess(){

        var item = new Item(0, "Oreo", 10, 30, Item.Type.NORMAL);
        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));

        Item itemFound = itemService.findById(0);
        assertEquals(item, itemFound);
    }

    @Test
    /**
     * GIVEN a valid normal type item in the database
     * WHEN updateQuality method is called
     * THEN the service should update the quality and sellIn values,
     * both will be decreased by 1
     */
    public void testUpdateQualityOfNormalTypeItem(){

        var item = new Item(0, "Oreo", 10, 30, Item.Type.NORMAL);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = itemService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(9, itemsUpdated.get(0).sellIn);
        assertEquals(29, itemsUpdated.get(0).quality);
        assertEquals(Item.Type.NORMAL, itemsUpdated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }

    @Test
    public void testUpdateQualityOfAgedTypeItem(){

        var item = new Item(0, "Oreo", 10, 30, Item.Type.AGED);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = itemService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(9, itemsUpdated.get(0).sellIn);
        assertEquals(31, itemsUpdated.get(0).quality);
        assertEquals(Item.Type.AGED, itemsUpdated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }

    @Test
    public void testUpdateQualityOfTicketsTypeItemWhenSellInIsFive(){

        var item = new Item(0, "Oreo", 6, 30, Item.Type.TICKETS);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = itemService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(5, itemsUpdated.get(0).sellIn);
        assertEquals(33, itemsUpdated.get(0).quality);
        assertEquals(Item.Type.TICKETS, itemsUpdated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }

    @Test
    public void testUpdateQualityOfTicketsTypeItemWhenSellInIsZero(){

        var item = new Item(0, "Oreo", 0, 30, Item.Type.TICKETS);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = itemService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(-1, itemsUpdated.get(0).sellIn);
        assertEquals(0, itemsUpdated.get(0).quality);
        assertEquals(Item.Type.TICKETS, itemsUpdated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }

    @Test
    public void testUpdateQualityOfLegendaryTypeItem(){

        var item = new Item(0, "Oreo", 10, 30, Item.Type.LEGENDARY);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = itemService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(10, itemsUpdated.get(0).sellIn);
        assertEquals(30, itemsUpdated.get(0).quality);
        assertEquals(Item.Type.LEGENDARY, itemsUpdated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }


    @Test
    public void testCreateItemSuccess(){

        var item = new Item(12,"Wine",15,20, Item.Type.AGED);
        itemService.createItem(item);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        var itemCreated = itemService.listItems();

        assertEquals(12, itemCreated.get(0).getId());
        assertEquals("Wine", itemCreated.get(0).name);
        assertEquals(15, itemCreated.get(0).sellIn);
        assertEquals(20, itemCreated.get(0).quality);
        assertEquals(Item.Type.AGED, itemCreated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }

    @Test
    public void testUpdateItemWhenItemWasNotFound(){

        var item = new Item(12,"Wine",15,20, Item.Type.AGED);
        when(itemRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                itemService.updateItem(item.getId(), item));
    }

    @Test
    public void testUpdateItemWhenItemWasSentFull(){

        var oldItem = new Item(12, "Oreo", 10, 30, Item.Type.NORMAL);
        var item = new Item(12,"Wine",15,20, Item.Type.AGED);
        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(oldItem));

        Item itemUpdated = itemService.updateItem(12,item);

        assertEquals(12, itemUpdated.getId());
        assertEquals("Wine", itemUpdated.name);
        assertEquals(15, itemUpdated.sellIn);
        assertEquals(20, itemUpdated.quality);
        assertEquals(Item.Type.AGED, itemUpdated.type);
    }

    @Test
    public void testUpdateItemWhenItemWasSentWithoutNameAndType(){

        var oldItem = new Item(12, "Oreo", 10, 30, Item.Type.NORMAL);
        var item = new Item(12,null,15,20, null);
        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(oldItem));

        Item itemUpdated = itemService.updateItem(12,item);

        assertEquals(12, itemUpdated.getId());
        assertEquals("Oreo", itemUpdated.name);
        assertEquals(15, itemUpdated.sellIn);
        assertEquals(20, itemUpdated.quality);
        assertEquals(Item.Type.NORMAL, itemUpdated.type);
    }

    @Test
    public void testCreatingItemsWhenIDAreDifferent(){
        var item = new Item(12,"The Batman",15,20, Item.Type.TICKETS);
        var item2 = new Item(13,"Chocorramo",20,50, Item.Type.NORMAL);

        List<Item> items = new LinkedList<>();
        items.add(item);
        items.add(item2);

        when(itemRepository.saveAll(any())).thenReturn(items);
        assertEquals(2, items.size());
        assertEquals(12, item.getId());
        assertEquals("The Batman", item.name);
        assertEquals(15, item.sellIn);
        assertEquals(20, item.quality);
        assertEquals(Item.Type.TICKETS, item.type);

        assertEquals(13, item2.getId());
        assertEquals("Chocorramo", item2.name);
        assertEquals(20, item2.sellIn);
        assertEquals(50, item2.quality);
        assertEquals(Item.Type.NORMAL, item2.type);
    }

    @Test
    public void testCreatingItemsWhithSameID(){
        var item = new Item(12,"The Batman",15,20, Item.Type.TICKETS);
        var item2 = new Item(12,"Chocorramo",20,50, Item.Type.NORMAL);

        List<Item> items = new LinkedList<>();
        items.add(item);
        items.add(item2);

        when(itemRepository.saveAll(any())).thenReturn(items);

        assertThrows(ResourceNotFoundException.class, () ->
                itemService.createItems(items));
    }

}

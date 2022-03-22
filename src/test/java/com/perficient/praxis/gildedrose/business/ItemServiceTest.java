package com.perficient.praxis.gildedrose.business;

import com.perficient.praxis.gildedrose.error.ResourceNotFoundException;
import com.perficient.praxis.gildedrose.model.Item;
import com.perficient.praxis.gildedrose.repository.ItemRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
}

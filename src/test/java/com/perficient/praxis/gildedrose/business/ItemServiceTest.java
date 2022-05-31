package com.perficient.praxis.gildedrose.business;

import com.perficient.praxis.gildedrose.error.DuplicateItemException;
import com.perficient.praxis.gildedrose.error.ResourceNotFoundException;
import com.perficient.praxis.gildedrose.controller.model.ItemRequest;
import com.perficient.praxis.gildedrose.handler.ServiceItemHandler;
import com.perficient.praxis.gildedrose.repository.ItemRepository;

import com.perficient.praxis.gildedrose.repository.model.ItemStored;
import com.perficient.praxis.gildedrose.type.Type;
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
    private ServiceItemHandler serviceItemHandler;


    @Test
    public void testGetItemByIdWhenItemWasNotFound(){

        when(itemRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                serviceItemHandler.findById(0));
    }

    @Test
    public void testGetItemByIdSuccess(){

        var item = new ItemStored(0, "Oreo", 10, 30, Type.NORMAL);
        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));

        ItemRequest itemFound = serviceItemHandler.findById(0);
        assertEquals(0, itemFound.getId());
        assertEquals("Oreo", itemFound.name);
        assertEquals(10, itemFound.sellIn);
        assertEquals(30, itemFound.quality);
        assertEquals(Type.NORMAL, itemFound.type);
    }




    @Test
    public void testCreateItemSuccess(){

        var itemStored = new ItemStored(12,"Wine",15,20, Type.AGED);
        when(itemRepository.save(any())).thenReturn(itemStored);
        when(itemRepository.findAll()).thenReturn(List.of(itemStored));

        var item = new ItemRequest(12,"Wine",15,21, Type.AGED);
        serviceItemHandler.createItem(item);
        var itemCreated = serviceItemHandler.listItems();

        assertEquals(12, itemCreated.get(0).getId());
        assertEquals("Wine", itemCreated.get(0).name);
        assertEquals(15, itemCreated.get(0).sellIn);
        assertEquals(20, itemCreated.get(0).quality);
        assertEquals(Type.AGED, itemCreated.get(0).type);
        verify(itemRepository,times(1)).save(any());
    }

    @Test
    public void testUpdateItemWhenItemWasNotFound(){

        var item = new ItemRequest(12,"Wine",15,20, Type.AGED);
        when(itemRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                serviceItemHandler.updateItem(item.getId(), item));
    }

    @Test
    public void testUpdateItemWhenItemWasSentFull(){

        var oldItem = new ItemStored(12,"Wine",15,20, Type.AGED);
        var item = new ItemRequest(12,"Wine",15,20, Type.AGED);

        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(oldItem));
        when(itemRepository.save(oldItem)).thenReturn(oldItem);

        ItemRequest itemUpdated = serviceItemHandler.updateItem(12,item);

        assertEquals(12, itemUpdated.getId());
        assertEquals("Wine", itemUpdated.name);
        assertEquals(15, itemUpdated.sellIn);
        assertEquals(20, itemUpdated.quality);
        assertEquals(Type.AGED, itemUpdated.type);
    }

    @Test
    public void testUpdateItemWhenItemWasSentWithoutNameAndNullType(){

        var oldItem = new ItemStored(12, "Oreo", 10, 30, Type.NORMAL);
        var item = new ItemRequest(12,null,15,20, null);
        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(oldItem));
        when(itemRepository.save(any())).thenReturn(new ItemStored(12, "Oreo", 15, 20, Type.NORMAL));

        ItemRequest itemUpdated = serviceItemHandler.updateItem(12,item);

        assertEquals(12, itemUpdated.getId());
        assertEquals("Oreo", itemUpdated.name);
        assertEquals(15, itemUpdated.sellIn);
        assertEquals(20, itemUpdated.quality);
        assertEquals(Type.NORMAL, itemUpdated.type);
    }

    @Test
    public void testCreatingItemsWhenIDAreDifferent(){
        var item = new ItemStored(12,"The Batman",15,20, Type.TICKETS);
        var item2 = new ItemStored(13,"Chocorramo",20,50, Type.NORMAL);

        List<ItemStored> items = new LinkedList<>();
        items.add(item);
        items.add(item2);

        when(itemRepository.saveAll(any())).thenReturn(items);
        assertEquals(2, items.size());
        assertEquals(12, item.getId());
        assertEquals("The Batman", item.name);
        assertEquals(15, item.sellIn);
        assertEquals(20, item.quality);
        assertEquals(Type.TICKETS, item.type);

        assertEquals(13, item2.getId());
        assertEquals("Chocorramo", item2.name);
        assertEquals(20, item2.sellIn);
        assertEquals(50, item2.quality);
        assertEquals(Type.NORMAL, item2.type);
    }

    @Test
    public void testCreatingItemsWithSameID(){
        var itemStored = new ItemStored(12,"The Batman",15,20, Type.TICKETS);

        when(itemRepository.findAll()).thenReturn(List.of(itemStored));


        var item = new ItemRequest(12,"The Batman",15,20, Type.TICKETS);
        assertThrows(DuplicateItemException.class, () ->
                serviceItemHandler.createItem(item));
    }

    @Test
    public void testDeletingItemSuccess(){

        var item = new ItemStored(12,"The Batman",15,20, Type.TICKETS);
        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));

        ItemRequest itemDeleted = serviceItemHandler.deleteItem(item.getId());

        assertEquals(itemDeleted.getId(), item.getId());
        assertEquals(itemDeleted.name, item.name);
        assertEquals(itemDeleted.sellIn, item.sellIn);
        assertEquals(itemDeleted.quality, item.quality);
        assertEquals(itemDeleted.type, item.type);
    }

    @Test
    public void testDeletingItemWhenTheItemWasNotFound(){
        var item = new ItemRequest(12,"The Batman",15,20, Type.TICKETS);

        assertThrows(ResourceNotFoundException.class, () ->
                serviceItemHandler.deleteItem(item.getId()));
    }
}

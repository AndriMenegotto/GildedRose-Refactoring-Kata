package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void should_increase_quality_for_anged_brie_when_sellIn_is_less_than_0_and_quality_is_less_than_50() {
        Item[] items = new Item[]{new Item("Aged Brie", -1, 47)};

        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();

        assertEquals("Aged Brie", gildedRose.items[0].name);
        assertEquals(-2, gildedRose.items[0].sellIn);
        assertEquals(49, gildedRose.items[0].quality);
    }

    @Test
    void should_quality_drops_to_0_after_the_concert() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", -1, 4)};

        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", gildedRose.items[0].name);
        assertEquals(-2, gildedRose.items[0].sellIn);
        assertEquals(0, gildedRose.items[0].quality);
    }

    @Test
    void should_decrease_quality_when_is_another_item() {
        Item[] items = new Item[] { new Item("foo", 0, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void should_backstage_passes_increase_quality_when_10_days_remains() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 22)};

        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", gildedRose.items[0].name);
        assertEquals(9, gildedRose.items[0].sellIn);
        assertEquals(24, gildedRose.items[0].quality);
    }

    @Test
    void should_backstage_passes_increase_quality_when_5_days_remains() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 22)};

        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", gildedRose.items[0].name);
        assertEquals(4, gildedRose.items[0].sellIn);
        assertEquals(25, gildedRose.items[0].quality);
    }
}

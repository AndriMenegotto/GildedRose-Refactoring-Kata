package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for(Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES)) {
            if (isQualityGreaterThanZero(item.quality)) {
                decreasesQualityWhenItemIsNotSulfurasHandOfRagnaros(item);
            }
        } else {
            if (isQualityLessThanFifty(item.quality)) {
                incrementQuality(item);

                if (item.name.equals(BACKSTAGE_PASSES)) {
                    incrementQualityDependingOnHowManyDaysAreLeftToSell(item);
                }
            }
        }
        if (!item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
            decreasesSellIn(item);
        }
        if (isSellInLessGreaterThanZero(item.sellIn)) {
            if(item.name.equals(AGED_BRIE)) {
                incrementQualityWhenQualitiIsGreaterThanFifity(item);
            } else {
                if(item.name.equals(BACKSTAGE_PASSES)) {
                    item.quality = resetQuality(item.quality);
                } else {
                    if (isQualityGreaterThanZero(item.quality)) {
                        decreasesQualityWhenItemIsNotSulfurasHandOfRagnaros(item);
                    }
                }
            }
        }
    }

    private void decreasesQualityWhenItemIsNotSulfurasHandOfRagnaros(Item item) {
        if (!item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
            decreasesQuality(item);
        }
    }

    private void incrementQualityDependingOnHowManyDaysAreLeftToSell(Item item) {
        if (isSellInLessThanEleven(item)) {
            incrementQualityWhenQualitiIsGreaterThanFifity(item);
        }

        if (isSellInLessThanSix(item)) {
            incrementQualityWhenQualitiIsGreaterThanFifity(item);
        }
    }

    private void incrementQualityWhenQualitiIsGreaterThanFifity(Item item) {
        if (isQualityLessThanFifty(item.quality)) {
            incrementQuality(item);
        }
    }

    private boolean isSellInLessThanEleven(Item item) {
        return item.sellIn < 11;
    }

    private boolean isSellInLessThanSix(Item item) {
        return item.sellIn < 6;
    }

    private boolean isSellInLessGreaterThanZero(int sellIn) {
        return sellIn < 0;
    }

    private int resetQuality(int quality) {
        quality = 0;

        return quality;
    }

    private boolean isQualityGreaterThanZero(int quality) {
        return quality > 0;
    }

    private boolean isQualityLessThanFifty(int quality) {
        return quality < 50;
    }

    private void decreasesSellIn(Item item) {
        item.sellIn -= 1;
    }

    private void decreasesQuality(Item item) {
        item.quality -= 1;
    }

    private void incrementQuality(Item item) {
        item.quality += 1;
    }
}
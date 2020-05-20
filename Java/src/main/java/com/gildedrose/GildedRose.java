package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    /*
    - All items have a SellIn value which denotes the number of days we have to sell the item
	- All items have a Quality value which denotes how valuable the item is
	- At the end of each day our system lowers both values for every item
     */

    public void updateQuality() {
        // itens que nao sao "Sulfuras, Hand of Ragnaros" diminui a qtd de dias que tem pra vender
        for(Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        if (!item.name.equals("Aged Brie")
            && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (isQualityGreaterThanZero(item.quality)) { // A qualidade de um item nunca é negativa
                if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                    decreasesQuality(item);
                }
            }
            // "Aged Brie" e "Backstage passes" aumentam a qualidade à medida que seu valor SellIn se aproxima (todos os dias)
        } else {
            if (isQualityLessThanFifty(item.quality)) { //A qualidade de um item nunca é superior que 50
                incrementQuality(item);

                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (isSellInLessThanEleven(item)) { // "Backstage passes" aumenta qualidade em 2 quando faltam 10 dias
                        //"Backstage passes" tem qualidade 49 max
                        if (isQualityLessThanFifty(item.quality)) {
                            //nao deveria ser 2?
                            incrementQuality(item);
                        }
                    }

                    if (isSellInLessThanSix(item)) { // "Backstage passes" aumenta qualidade em 3 quando faltam 5 dias
                        if (isQualityLessThanFifty(item.quality)) {
                            //nao deveria ser 3?
                            incrementQuality(item);
                        }
                    }
                }
            }
        }
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            decreasesSellIn(item);
        }
        if (isSellInLessGreaterThanZero(item.sellIn)) {
            //Para itens que não são "Aged Brie"
            if (!item.name.equals("Aged Brie")) {
                //Para itens que não são Backstage passes...
                if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (isQualityGreaterThanZero(item.quality)) {
                        //Para itens que não são ("Sulfuras, Hand of Ragnaros" e tem qualidade maior que 0, degrada a qualidade
                        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                            decreasesQuality(item);
                        }
                    }
                } else {
                    item.quality = resetQuality(item.quality);
                }
                //Para itens que são "Aged Brie"
            } else {
                if (isQualityLessThanFifty(item.quality)) {
                    incrementQuality(item);
                }
            }
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
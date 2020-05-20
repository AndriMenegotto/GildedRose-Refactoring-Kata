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
            if (item.quality > 0) { // A qualidade de um item nunca é negativa
                if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                    decreasesQuality(item);
                }
            }
            // "Aged Brie" e "Backstage passes" aumentam a qualidade à medida que seu valor SellIn se aproxima (todos os dias)
        } else {
            if (item.quality < 50) { //A qualidade de um item nunca é superior que 50
                incrementQuality(item);

                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.sellIn < 11) { // "Backstage passes" aumenta qualidade em 2 quando faltam 10 dias
                        //"Backstage passes" tem qualidade 49 max
                        if (item.quality < 50) {
                            //nao deveria ser 2?
                            incrementQuality(item);
                        }
                    }

                    if (item.sellIn < 6) { // "Backstage passes" aumenta qualidade em 3 quando faltam 5 dias
                        if (item.quality < 50) {
                            //nao deveria ser 3?
                            incrementQuality(item);
                        }
                    }
                }
            }
        }
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn -= 1;
        }
        if (item.sellIn < 0) {
            //Para itens que não são "Aged Brie"
            if (!item.name.equals("Aged Brie")) {
                //Para itens que não são Backstage passes...
                if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.quality > 0) {
                        //Para itens que não são ("Sulfuras, Hand of Ragnaros" e tem qualidade maior que 0, degrada a qualidade
                        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                            decreasesQuality(item);
                        }
                    }
                } else {
                    // Qualidade cai pra 0 após o show
                    item.quality = 0;
                }
                //Para itens que são "Aged Brie"
            } else {
                // Se a qualidade do ítem for menor que 50 degrada a qualidade
                if (item.quality < 50) {
                    incrementQuality(item);
                }
            }
        }
    }

    private void decreasesQuality(Item item) {
        item.quality -= 1;
    }

    private void incrementQuality(Item item) {
        item.quality += 1;
    }
}
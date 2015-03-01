package ru.nordwest.nord.common.items;

import net.minecraft.item.Item;
import ru.nordwest.nord.client.lib.tabs.TabsNord;

public class ItemBase extends Item {
        public ItemBase() {
                this.setCreativeTab(TabsNord.tabGeneral);
        }
}

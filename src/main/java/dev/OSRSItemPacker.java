package dev;

import net.runelite.cache.ItemManager;
import net.runelite.cache.definitions.ItemDefinition;
import net.runelite.cache.fs.Store;
import store.FileStore;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by Home on 8/30/2023
 */
public class OSRSItemPacker {

    public static final int getItemDefinitionsSize(FileStore cache) {
        int lastArchiveId = cache.getIndexes()[19].getLastArchiveId();
        return (lastArchiveId * 256 + cache.getIndexes()[19].getValidFilesCount(lastArchiveId));
    }

    private static final String CACHE_DIR = "718_LIVES_HERE";
    private static final String OSRS_CACHE_DIR = "OSRS_LIVES_HERE";

    public static void main(String[] args) throws IOException {
        Store store1 = new Store(new File(OSRS_CACHE_DIR));
        store1.load();
        ItemManager im = new ItemManager(store1);
        im.load();

        FileStore cache = new FileStore(CACHE_DIR);


        Collection<ItemDefinition> obj = im.getItems();
        //todo get the item cap of the 718 items and add 1 to start new items from
        int index = getItemDefinitionsSize(cache) + 1;

        for(ItemDefinition i : obj) {
            store.codec.ItemDefinition def =  new store.codec.ItemDefinition(index++);
            copyValues(i, def);
            def.save(cache);
        }
    }

    private static void copyValues(ItemDefinition i, store.codec.ItemDefinition def) {
        def.name = i.getName();
        def.cost = i.getCost();
        def.team = i.getTeam();
        def.stackable = i.stackable;
        def.notedID = i.notedID;
        def.noteTemplate = i.notedTemplate;

//TODO not in OSRS?
//        def.lendedItemId = Integer.parseInt(lent_item.getText());
//        def.switchLendItemId = Integer.parseInt(lend_item.getText());
        def.members = i.members;
        def.unnoted = i.isTradeable;
        def.rotation_x = i.xan2d;
        def.rotation_y = i.yan2d;
        def.inventory_model = i.inventoryModel;
        def.zoom = i.zoom2d;
        def.offset_x = i.xOffset2d;
        def.offset_y = i.yOffset2d;
        def.equipType = i.wearPos1;
        def.equipSlot = i.wearPos2;
        def.maleModel0 = i.maleModel0;
        def.maleModel1 = i.maleModel1;
        def.maleModel2 = i.maleModel2;
        def.femaleModel0 = i.femaleModel0;
        def.femaleModel1 = i.femaleModel1;
        def.femaleModel2 = i.femaleModel2;

//TODO THE FUCK? Maybe see how onyx calc?
//        def.maleModelOffsetX = Integer.parseInt(maleOffsetX.getText());
//        def.maleModelOffsetY = Integer.parseInt(maleOffsetY.getText());
//        def.maleModelOffsetZ = Integer.parseInt(maleOffsetZ.getText());
//        def.femaleModelOffsetX = Integer.parseInt(femaleOffsetX.getText());
//        def.femaleModelOffsetY = Integer.parseInt(femaleOffsetY.getText());
//        def.femaleModelOffsetZ = Integer.parseInt(femaleOffsetZ.getText());
        def.resizeX = i.resizeX;
        def.resizeY = i.resizeY;
        def.resizeZ = i.resizeZ;

        def.stackIds = i.countObj;
        def.stackAmounts = i.countCo;
        def.inventory_options = i.interfaceOptions;
        def.ground_options = i.options;

        if (i.colorFind != null && i.colorReplace != null) {
            def.colorsToFind = new int[i.colorFind.length];
            def.colorsToReplace = new int[i.colorReplace.length];
            for (int index = 0; index < i.colorFind.length; index++) {
                def.colorsToFind[index] =  i.colorFind[index];
                def.colorsToReplace[index] = i.colorReplace[index];
            }
        } else {
            def.colorsToFind = null;
            def.colorsToReplace = null;
        }

        def.retexturesToFind = i.textureFind;
        def.retexturesToReplace = i.textureReplace;

        def.params = i.params;
    }
}

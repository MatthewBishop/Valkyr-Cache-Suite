package dev;

import net.runelite.cache.fs.Store;

import java.io.File;
import java.io.IOException;

/**
 * Created by Home on 9/2/2023
 */
public class OSRSDefDumper {
    private static final String OSRS_CACHE_DIR = "K:\\documents\\GitHub\\eternalscape-server3\\data\\cache\\";

    public static void main(String[] args) throws IOException {
        Store store1 = new Store(new File(OSRS_CACHE_DIR));
        store1.load();
        dumpSequences(store1, new File("./anims/"));
    }


    private static void dumpSequences(Store store, File dir) throws IOException
    {
        SequenceManager dumper = new SequenceManager(store);
        dumper.load();
        dumper.dump(dir);
    }

}
package dev;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.runelite.cache.ConfigType;
import net.runelite.cache.IndexType;
import net.runelite.cache.definitions.SequenceDefinition;
import net.runelite.cache.definitions.loaders.SequenceLoader;
import net.runelite.cache.fs.Archive;
import net.runelite.cache.fs.ArchiveFiles;
import net.runelite.cache.fs.FSFile;
import net.runelite.cache.fs.Index;
import net.runelite.cache.fs.Storage;
import net.runelite.cache.fs.Store;

public class SequenceManager
{
	private final Store store;
	private final Map<Integer, SequenceDefinition> seqs = new HashMap<>();

	public SequenceManager(Store store)
	{
		this.store = store;
	}

	public void load() throws IOException
	{
		SequenceLoader loader = new SequenceLoader();

		Storage storage = store.getStorage();
		Index index = store.getIndex(IndexType.CONFIGS);
		Archive archive = index.getArchive(ConfigType.SEQUENCE.getId());
				byte[] archiveData = storage.loadArchive(archive);
		ArchiveFiles files = archive.getFiles(archiveData);

		for (FSFile f : files.getFiles())
		{
			SequenceDefinition def = loader.load(f.getFileId(), f.getContents());
			seqs.put(f.getFileId(), def);
		}
	}

	public Collection<SequenceDefinition> getSeqs()
	{
		return Collections.unmodifiableCollection(seqs.values());
	}

	public SequenceDefinition get(int id)
	{
		return seqs.get(id);
	}

	public void dump(File out) throws IOException
	{
		out.mkdirs();

		for (SequenceDefinition def : seqs.values())
		{
			SequenceExporter exporter = new SequenceExporter(def);

			File targ = new File(out, def.getId() + ".json");
			exporter.exportTo(targ);
		}
	}
}

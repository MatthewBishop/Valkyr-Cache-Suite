package dev;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.runelite.cache.definitions.SequenceDefinition;
import net.runelite.cache.definitions.loaders.SequenceLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SequenceExporter
{
	private final SequenceDefinition seq;
	private final Gson gson;

	public SequenceExporter(SequenceDefinition seq)
	{
		this.seq = seq;

		GsonBuilder builder = new GsonBuilder()
			.setPrettyPrinting();
		gson = builder.create();
	}

	public String export()
	{
		return gson.toJson(seq);
	}

	public void exportTo(File file) throws IOException
	{
		try (FileWriter fw = new FileWriter(file))
		{
			fw.write(export());
		}
	}
}

package com.dolthhaven.easeldoesit.data.server.tags;

import com.dolthhaven.easeldoesit.core.EaselDoesIt;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.tags.PoiTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EaselModPoiTags extends PoiTypeTagsProvider {
    public EaselModPoiTags(GatherDataEvent e) {
        super(e.getGenerator().getPackOutput(), e.getLookupProvider(), EaselDoesIt.MOD_ID, e.getExistingFileHelper());
    }

    @Override
    public void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(PoiTypeTags.ACQUIRABLE_JOB_SITE).addOptional(
              EaselDoesIt.rl("artist_poi")
        );
    }
}

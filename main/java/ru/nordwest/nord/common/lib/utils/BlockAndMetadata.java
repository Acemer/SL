package ru.nordwest.nord.common.lib.utils;

import net.minecraft.block.Block;

public class BlockAndMetadata {
    private final Block block;
    private final int metadata;

    public BlockAndMetadata(Block block, int metadata) {
        this.block = block;
        this.metadata = metadata!=-1? metadata & 15 : -1;
    }
    public BlockAndMetadata(Block block) {
        this.block = block;
        this.metadata = 0;
    }

    public Block getBlock() {
        return block;
    }

    public int getMetadata() {
        return metadata;
    }
}

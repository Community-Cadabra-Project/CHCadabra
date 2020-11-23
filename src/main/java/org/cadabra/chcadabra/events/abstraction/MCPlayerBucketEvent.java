package org.cadabra.chcadabra.events.abstraction;

import com.laytonsmith.abstraction.MCItemStack;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.blocks.MCBlockFace;
import com.laytonsmith.abstraction.blocks.MCMaterial;
import com.laytonsmith.core.events.BindableEvent;

public interface MCPlayerBucketEvent extends BindableEvent {

    MCBlock getBlock();
    MCBlock getBlockClicked();
    MCBlockFace getBlockFace();
    MCMaterial getBucket();
    MCItemStack getItemStack();
    void setMCItemStack(MCItemStack itemStack);
}

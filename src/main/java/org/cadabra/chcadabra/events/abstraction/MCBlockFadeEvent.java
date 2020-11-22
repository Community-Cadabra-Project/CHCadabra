package org.cadabra.chcadabra.events.abstraction;

import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.blocks.MCBlockState;
import com.laytonsmith.core.events.BindableEvent;

public interface MCBlockFadeEvent extends BindableEvent {

    MCBlock getBlock();
    MCBlockState getNewState();
}

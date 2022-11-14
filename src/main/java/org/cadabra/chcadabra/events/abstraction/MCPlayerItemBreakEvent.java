package org.cadabra.chcadabra.events.abstraction;

import com.laytonsmith.abstraction.MCItemStack;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.events.MCPlayerEvent;
import com.laytonsmith.core.events.BindableEvent;

public interface MCPlayerItemBreakEvent extends MCPlayerEvent {
     MCItemStack getBrokenItem();
}

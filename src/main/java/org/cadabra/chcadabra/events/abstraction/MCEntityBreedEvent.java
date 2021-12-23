package org.cadabra.chcadabra.events.abstraction;

import com.laytonsmith.abstraction.MCItemStack;
import com.laytonsmith.abstraction.MCLivingEntity;
import com.laytonsmith.core.events.BindableEvent;

import java.util.Optional;

public interface MCEntityBreedEvent extends BindableEvent {
    MCLivingEntity getChild();
    MCLivingEntity getMother();
    MCLivingEntity getFather();
    Optional<MCLivingEntity> getBreeder();
    Optional<MCItemStack> getBredWith();
    int getExperience();
    void setExperience(int experience);
}

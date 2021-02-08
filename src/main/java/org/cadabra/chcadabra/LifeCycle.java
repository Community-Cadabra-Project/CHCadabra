package org.cadabra.chcadabra;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.Implementation;
import com.laytonsmith.core.Globals;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import org.cadabra.chcadabra.events.abstraction.bukkit.BukkitListener;

@MSExtension("CHCadabra")
public class LifeCycle extends AbstractExtension {
    public Version getVersion() {
        return new SimpleVersion(0, 0, 7);
    }

    @Override
    public void onStartup() {
        if(!Implementation.GetServerType().equals(Implementation.Type.SHELL)) {
            System.out.println("CHCadabra " + getVersion() + " loaded.");
        }
        BukkitListener.register();
    }

    @Override
    public void onShutdown() {
        if(!Implementation.GetServerType().equals(Implementation.Type.SHELL)) {
            System.out.println("CHCadabra " + getVersion() + " unloaded.");
        }
        Globals.GetGlobalConstruct()
        BukkitListener.unregister();
    }
}

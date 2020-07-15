package org.cadabra.chcadabra;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.Implementation;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;

@MSExtension("CHCadabra")
public class LifeCycle extends AbstractExtension {
    public Version getVersion() {
        return new SimpleVersion(0, 0, 1);
    }

    @Override
    public void onStartup() {
        if(!Implementation.GetServerType().equals(Implementation.Type.SHELL)) {
            System.out.println("CHCadabra " + getVersion() + " loaded.");
        }
    }

    @Override
    public void onShutdown() {
        if(!Implementation.GetServerType().equals(Implementation.Type.SHELL)) {
            System.out.println("CHCadabra " + getVersion() + " unloaded.");
        }
    }
}

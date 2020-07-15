package org.cadabra.chcadabra.functions;

import com.laytonsmith.annotations.api;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.environments.GlobalEnv;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.natives.interfaces.Mixed;

import java.util.HashMap;
import java.util.Map;

public class Env {

    private static final Map<String, GlobalEnv> envs = new HashMap<>();

    public static String docs() {
        return "A set of functions for manipulate the environment: export, import, clear.";
    }

    public static abstract class FileFunction extends AbstractFunction {
        @Override
        public MSVersion since() {
            return MSVersion.V3_3_4;
        }

        @Override
        public Boolean runAsync() {
            return null;
        }

        @Override
        public boolean isRestricted() {
            return true;
        }

        @Override
        public Class<? extends CREThrowable>[] thrown() {
            return null;
        }
    }

    @api
    public static class x_export_env extends FileFunction {

        @Override
        public String getName() {
            return "x_export_env";
        }

        @Override
        public Integer[] numArgs() {
            return new Integer[]{1};
        }

        @Override
        public String docs() {
            return "boolean {string key} Exports the current key-bound environment.";
        }

        @Override
        public Mixed exec(final Target t, final Environment environment, Mixed... args) throws ConfigRuntimeException {
            String id = args[0].val();
            GlobalEnv e = null;
            try {
                e = (GlobalEnv) environment.getEnv(GlobalEnv.class).clone();
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
            if (e == null) {
                return CBoolean.FALSE;
            } else {
                envs.put(id, e);
                return CBoolean.TRUE;
            }
        }

        @Override
        public Class<? extends CREThrowable>[] thrown() {
            return null;
        }
    }

    @api
    public static class x_import_env extends FileFunction {

        @Override
        public String getName() {
            return "x_import_env";
        }

        @Override
        public Integer[] numArgs() {
            return new Integer[]{1};
        }

        @Override
        public String docs() {
            return "boolean {string key} Imports the environment associated with this key."
                    + " Can overwrite current procedures and variables!";
        }

        @Override
        public Mixed exec(final Target t, final Environment env, final Mixed... args) throws ConfigRuntimeException {
            String id = args[0].val();
            GlobalEnv imp = envs.get(id);
            if (imp == null) {
                return CBoolean.FALSE;
            }
            GlobalEnv e = env.getEnv(GlobalEnv.class);

            //procedures
            e.GetProcs().putAll(imp.GetProcs());

            //variables
            IVariableList eList = e.GetVarList();
            IVariableList impList = imp.GetVarList();
            for (String name :
                    impList.keySet()) {
                eList.set(impList.get(name, t, env));
            }
            return CBoolean.TRUE;
        }

        @Override
        public Class<? extends CREThrowable>[] thrown() {
            return null;
        }
    }

    @api
    public static class x_remove_env extends FileFunction {

        @Override
        public String getName() {
            return "x_remove_env";
        }

        @Override
        public Integer[] numArgs() {
            return new Integer[]{1};
        }

        @Override
        public String docs() {
            return "boolean {string key} Deletes the exported environment by key.";
        }

        @Override
        public Mixed exec(final Target t, final Environment env, final Mixed... args) throws ConfigRuntimeException {
            String id = args[0].val();
            return envs.remove(id) == null ? CBoolean.FALSE : CBoolean.TRUE;
        }

        @Override
        public Class<? extends CREThrowable>[] thrown() {
            return null;
        }
    }

    @api
    public static class x_clear_env extends FileFunction {

        @Override
        public String getName() {
            return "x_clear_env";
        }

        @Override
        public Integer[] numArgs() {
            return new Integer[]{0};
        }

        @Override
        public String docs() {
            return "void {} Removes all variables and procedures from the environment."
                    + " Convenient before initializing the environment to be exported.";
        }

        @Override
        public Mixed exec(final Target t, final Environment env, final Mixed... args) throws ConfigRuntimeException {
            GlobalEnv e = env.getEnv(GlobalEnv.class);
            e.GetVarList().clear();
            e.GetProcs().clear();
            return CVoid.VOID;
        }

        @Override
        public Class<? extends CREThrowable>[] thrown() {
            return null;
        }
    }

    @api
    public static class x_keys_env extends FileFunction {

        @Override
        public String getName() {
            return "x_keys_env";
        }

        @Override
        public Integer[] numArgs() {
            return new Integer[]{0};
        }

        @Override
        public String docs() {
            return "array {} Returns all keys associated with environments.";
        }

        @Override
        public Mixed exec(final Target t, final Environment env, final Mixed... args) throws ConfigRuntimeException {
            CArray arr = new CArray(t);
            for (String s :
                    envs.keySet()) {
                CString cs = new CString(s, t);
                arr.push(cs, t);
            }
            return arr;
        }

        @Override
        public Class<? extends CREThrowable>[] thrown() {
            return null;
        }
    }
}

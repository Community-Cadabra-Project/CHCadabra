# CHCadabra

Extension used for Cadabra modules

## Functions

- **boolean x_export_env(string key)**

  Exports the current key-bound environment.

- **boolean x_import_env(string key)**

  Imports the environment associated with this key. Can overwrite current procedures and variables!

- **boolean x_remove_env(string key)**

  Deletes the exported environment by key.

- **void x_clear_env()**

  Removes all variables and procedures from the environment. Convenient before initializing the environment to be exported.

- **array x_keys_env()**

  Returns all keys associated with environments.

- **array dump_keys_threads()**

  Returns an array of all threads keys that are currently running in the JVM.

- **void x_stop_thread(string id)**

  Stopping tracked thread named 'id'. If successful, returns true, else false. If the thread performs a x_safe_execute(\[values...\], closure) function, the interrupting thread will wait for execution.

- **void x_safe_execute(\[values...\], closure)**

  Executes the given closure. You can also send arguments to the closure, which it may or may not use, depending on the particular closure's definition. Unlike closure, it returns only void and will be executed even if the thread was stopped by a function x_stop_thread(string id).
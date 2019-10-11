package de.mcmdev.troll;

import de.mcmdev.troll.trolls.CrashTroll;
import de.mcmdev.troll.trolls.FakeblocksTroll;
import de.mcmdev.troll.trolls.api.SimpleTroll;
import de.mcmdev.troll.trolls.StalTroll;
import de.mcmdev.troll.trolls.api.Troll;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrollManager {

    private List<Troll> registry = new ArrayList<>();
    private TrollPlugin trollPlugin;
    private File trollsFolder;

    public TrollManager(TrollPlugin trollPlugin) {
        this.trollPlugin = trollPlugin;
        trollsFolder = new File( trollPlugin.getDataFolder() + "/trolls" );
        register(new CrashTroll(trollPlugin));
        register(new FakeblocksTroll(trollPlugin));
        register(new StalTroll(trollPlugin));

        load();
    }

    public void load() {
        registry.clear();
        if(!trollsFolder.exists()) {
            trollsFolder.mkdirs();
        }
        for(String list : Objects.requireNonNull(trollsFolder.list()))   {
            register(loadFromFile(list.replace(".class", "")));
        }
    }

    private void register(Troll troll)  {
        if(!registry.contains(troll))    {
            if(troll instanceof Listener) {
                trollPlugin.getServer().getPluginManager().registerEvents((Listener) troll, trollPlugin);
            }
            registry.add(troll);
            trollPlugin.getServer().getConsoleSender().sendMessage(TrollPlugin.PREFIX + "§fRegistering Troll: §c" + troll.getName());
        }
    }

    public void execute(Class<? extends SimpleTroll> troll, TrollPlugin plugin, Player sender, Player target)  {
        registry.stream().filter(found -> found.getClass().equals(troll)).findFirst().ifPresent(found -> found.execute(plugin, sender, target));
    }

    public Troll loadFromFile(String file) {
        try {
            URL url = trollsFolder.toURI().toURL();
            URLClassLoader cl = URLClassLoader.newInstance( new URL[] { url.toURI().toURL() }, Troll.class.getClassLoader());

            Class<?> cw = cl.loadClass(file);

            Class<? extends Troll> cww = cw.asSubclass(Troll.class);

            Constructor<? extends Troll> c = cww.getConstructor(TrollPlugin.class);

            Troll troll = (Troll) c.newInstance(trollPlugin);
            trollPlugin.getServer().getConsoleSender().sendMessage(TrollPlugin.PREFIX + "§fFound Troll §c" + troll.getName() + "§f in file " + file);
            return troll;
        }   catch (Exception ex)    {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Troll> getRegistry() {
        return registry;
    }
}

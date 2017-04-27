package pcl.opensecurity;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import pcl.opensecurity.client.ClientProxy;
import pcl.opensecurity.common.CommonProxy;
import pcl.opensecurity.common.SoundHandler;
import pcl.opensecurity.client.CreativeTab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = OpenSecurity.MODID, name = "OpenSecurity", version = BuildInfo.versionNumber + "." + BuildInfo.buildNumber, dependencies = "required-after:OpenComputers")
public class OpenSecurity {
	public static final String MODID = "opensecurity";

	@Instance(value = MODID)
	public static OpenSecurity instance;
	
	@SidedProxy(clientSide = "pcl.opensecurity.client.ClientProxy", serverSide = "pcl.opensecurity.common.CommonProxy")
	public static CommonProxy proxy;
	public static Config cfg = null;

	public static boolean debug = false;
	public static int rfidRange;
	public static boolean enableplaySoundAt = false;
	public static boolean ignoreUUIDs = false;
	public static boolean registerBlockBreakEvent = true;

	public static final Logger logger = LogManager.getFormatterLogger(MODID);

	public static SimpleNetworkWrapper network;
	
	public static CreativeTabs CreativeTab = new CreativeTab("OpenSecurity");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		long time = System.nanoTime();

		ContentRegistry.preInit();
		proxy.registerSounds();
		SoundHandler.registerSounds();
	    network = NetworkRegistry.INSTANCE.newSimpleChannel("OpenSecurity");
	    int packetID = 0;
	    
	    logger.info("Registered " + packetID + " packets");
	    logger.info("Finished pre-init in %d ms", (System.nanoTime() - time) / 1000000);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		long time = System.nanoTime();
		
		logger.info("Finished init in %d ms", (System.nanoTime() - time) / 1000000);
	}
}

/*
 * THis class registers information about your mod and creates a method that can automatically register the registry of your items and
 * assign them an unlocalized name.
 */

package org.educraft.mymod;

public class Reference {

	public static final String MOD_ID = "doommod";
	public static final String NAME = "Doom Mod";
	public static final String VERSION = "1.0";
	public static final String ACCEPTED_VERSIONS = "[1.11],[1.11.1],[1.11.2]";
	
	public static final String CLIENT_PROXY_CLASS = "org.educraft.mymod.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "org.educraft.mymod.proxy.ServerProxy";
}
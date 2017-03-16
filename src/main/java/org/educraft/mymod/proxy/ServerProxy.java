/*
 * This class gives you an override command when you are running on a server to not load textures because it will crash the server.
 */

package org.educraft.mymod.proxy;

public class ServerProxy implements CommonProxy {
	@Override
	public void init() {}
}
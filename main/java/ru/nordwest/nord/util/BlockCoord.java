package ru.nordwest.nord.util;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

/**
 * Простой класс для хранения координат блока
 *
 */
public class BlockCoord {
	public int x;
	public int y;
	public int z;
	public int ID;
	
	public BlockCoord(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.ID = 0;
	}
	
	public BlockCoord(int x, int y, int z, int ID)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.ID = ID;
	}
	
	@Override
	public int hashCode() {
		String tmp = Integer.valueOf(x).toString().concat(Integer.valueOf(y).toString()).concat(Integer.valueOf(z).toString());
		int hash = tmp.hashCode();
		return hash;
	}
	
	@Override
	public boolean equals(Object arg0) {
		return arg0.hashCode() == this.hashCode();
	}
}

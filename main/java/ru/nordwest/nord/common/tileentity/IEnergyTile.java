package ru.nordwest.nord.common.tileentity;

public interface IEnergyTile {
	/**
	 * Установить значение энергию
	 * 
	 * @param energy
	 *            новое значение энергии
	 * @return новое значение энергии
	 */
	public int setEnergy(int energy);
	/**
	 * Получить значение энергии
	 * 
	 * @return значение энергии
	 */
	public int getEnergy();
	/**
	 * Добавить энергию, учитывая максимальное значение
	 * 
	 * @param energy
	 *            сколько добавить
	 * @return новое значение энергии
	 */
	public int addEnergy(int energy);
	/**
	 * Убавить энегию, учитывая минимальное значение (ноль)
	 * 
	 * @param energy
	 *            сколько убавить
	 * @return новое значение энергии
	 */
	public int subEnergy(int energy);
	/**
	 * Получить максимальное значение энергии
	 * 
	 * @return максимальное значение энергии
	 */
	public int getMaxEnergy();
	/**
	 * Принимает ли энергию
	 * 
	 * @return
	 */
	public boolean isAcepter();
	/**
	 * Раздаёт ли энергию
	 * 
	 * @return
	 */
	public boolean isDispenser();

	/**
	 * Можем ли убавить энергию
	 * 
	 * @param energy
	 *            на сколько пробуем убавить
	 * @return
	 */
	public boolean hasSubEnergy(int energy);
	/**
	 * Можем ли прибавить энергию
	 * 
	 * @param energy
	 *            на сколько хотим прибавить
	 * @return
	 */
	public boolean hasAddEnergy(int energy);
}
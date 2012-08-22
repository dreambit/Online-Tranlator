package modules;

public abstract class Module {
	/**
	 * In this method, the plug-in starts its work.
	 */
	public abstract void run();
	/**
	 * When calling this method, the plugin should complete its work, to free all allocated resources.
	 */
	public abstract void destroy();
	/**
	 * This method is called when the event occurred copy data to the clipboard
	 * @param data - Data from the clipboard
	 * @return - Возвращает любой объект(в зависимости от цели работы плагина), который должен обязательно переопределить метод 
	 * toString(), так как именно данные возвращаемые этим методом будут использоваться в программе.
	 * В случае если плагин не должен ничего возвращать, необходимо вернуть null.
	 * В случае если будет возвращено не null, программа не будет осуществлять перевод буфера обмена.
	 */
	public abstract Object copied(String data);
	/**
	 * Этот метод вызывается когда в программе был осуществлен перевод.
	 * @param data - Переведенные данные
	 */
	public abstract void translated(String data);
}

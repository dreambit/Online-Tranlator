package messagers;
public interface Messager {
	/**
	 * Show message with translation
	 * @param title - title of message
	 * @param message - message is displayed on the screen
	 */
	public void showMessage(String title, String message);
}

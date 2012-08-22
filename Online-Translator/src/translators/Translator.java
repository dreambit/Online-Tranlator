package translators;

public abstract class Translator {
	// From language
	protected String sl;
	// To language
	protected String tl;

	public Translator(String sl, String tl) {
		this.sl = sl;
		this.tl = tl;
	}

	/**
	 * Method of translation of the text
	 * 
	 * @param text
	 *            - text for translation
	 * @return translation of the text
	 */
	public abstract String translate(String text);

	/**
	 * The method that checks the correctness of the text
	 * 
	 * @param text
	 * @return true if text is correct
	 */
	public abstract boolean isCorrect(String text);
}

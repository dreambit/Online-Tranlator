package translators;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.swing.JOptionPane;

import net.sourceforge.javajson.JsonObject;

public class MymemoryTranslator extends Translator {

	public MymemoryTranslator(String sl, String tl) {
		super(sl, tl);
	}

	@Override
	public String translate(String text) {
		if (!isCorrect(text))
			return "TEXT IS NOT CORRECT";

		String serviceUrl = "http://mymemory.translated.net/api/get?q=%s&langpair=%s|%s";
		String resultUrl = "";
		String translate = "";

		try {
			resultUrl = String.format(serviceUrl,
					URLEncoder.encode(text, "UTF-8"), sl, tl);

			URL url = new URL(resultUrl);
			URLConnection conn = url.openConnection();

			InputStream is = conn.getInputStream();
			JsonObject json = JsonObject.parse(is);
			translate = json.getJsonObject("responseData").getString(
					"translatedText");
			is.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getStackTrace(),
					"Translation error", JOptionPane.ERROR_MESSAGE);
			translate = "TRANSLATION ERROR";
		}
		return translate;
	}

	@Override
	public boolean isCorrect(String text) {
		return text.length() > 1;
	}

	public static String about() {
		return "MymemoryTranslator by (Rezvan aka Dreambitc aka John_Pa9JIbHuK)";
	}
}

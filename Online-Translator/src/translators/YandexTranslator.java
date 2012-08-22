package translators;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;


public class YandexTranslator extends Translator {
	

	public YandexTranslator(String sl, String tl) {
		super(sl, tl);
	}

	@Override
	public String translate(String text) {
		if (!isCorrect(text))
			return "TEXT IS NOT CORRECT";

		String serviceUrl = "http://translate.yandex.net/api/v1/tr/translate?lang=%s-%s&text=%s";
		String resultUrl = "";
		String translate = "";

		try {
			resultUrl = String.format(serviceUrl, sl, tl,
					URLEncoder.encode(text, "UTF-8"));

			URL url = new URL(resultUrl);
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(is);
			translate = ((CharacterData)document.getElementsByTagName("text").item(0).getFirstChild()).getData();
			
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
		return "YandexTranslator by (Rezvan aka Dreambitc aka John_Pa9JIbHuK)";
	}

}

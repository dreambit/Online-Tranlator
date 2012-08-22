package translators;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;

public class AbbyyTranslator extends Translator {

	private static final int WORDS_COUNT = 20;
	
	public AbbyyTranslator(String sl, String tl) {
		super(sl, tl);
	}

	@Override
	public String translate(String text) {
		if (!isCorrect(text))
			return "TEXT IS NOT CORRECT";

		String serviceUrl = "http://lingvopro.abbyyonline.com/ru/Translate/%s-%s/%s";
		String resultUrl = "";
		String translate = "";

		try {
			resultUrl = String.format(serviceUrl, sl, tl,
					URLEncoder.encode(text, "UTF-8"));

			System.out.println(resultUrl);
			URL url = new URL(resultUrl);
			URLConnection conn = url.openConnection();
			InputStreamReader is = new InputStreamReader(conn.getInputStream(), "UTF-8");
			StringBuffer sb = new StringBuffer();

			char[] buffer = new char[256];

			while (true) {
				int byteRead = is.read(buffer);
				if (byteRead == -1)
					break;
				for (int i = 0; i < byteRead; i++) {
					sb.append((char) buffer[i]);
				}
			}
			String responseString = sb.toString();
			//System.out.println(responseString);
			Pattern pattern = Pattern
					.compile("<span class=\"translation\">.[^<span]{3,}</span>");
			Matcher matcher = pattern.matcher(responseString);
			for (int i = 0; i < WORDS_COUNT; i++) {
				if (matcher.find()){
					String group = matcher.group();
					translate += "[" + group.substring(group.indexOf(">") + 1, group.indexOf("/") - 1) + "] ";
				}
				else {
					break;
				}
			}
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

}

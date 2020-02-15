import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrasedLogLine {

	public long time;
	public String serviceName;
	public Integer serviceId;
	public String type;

	public PrasedLogLine(String line) throws ParseException {
		String timePatternRegex = "2015-10-(\\d+)T(\\d+):(\\d+):(\\d+),(\\d+)";
		String entryExitRegex = "entry|exit";
		String clientIdRegex = "(([a-zA-Z]+):(\\d+))";

		this.time = ParseTimeStamp(returnPatternMatches(timePatternRegex, line));
		this.serviceName = returnPatternMatches(clientIdRegex, line).split(":")[0];
		this.serviceId = (Integer.parseInt(returnPatternMatches(clientIdRegex, line).split(":")[1]));
		this.type = (returnPatternMatches(entryExitRegex, line));

	}

	private static String returnPatternMatches(String regex, String line) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			return matcher.group();
		}
		return "";
	}

	private long ParseTimeStamp(String timeStamp) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss','SSS");
		Date dateFormat = sdf.parse(timeStamp);
		return dateFormat.getTime();
	}
}

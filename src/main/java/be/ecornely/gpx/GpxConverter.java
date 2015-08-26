package be.ecornely.gpx;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DateTool;

import be.ecornely.gpx.data.Geocache;

public class GpxConverter {
	
	public static String generateGpx(Geocache g) throws IOException{
		VelocityEngine engine = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		context.put("g", g);
		context.put("date", new DateTool());
		StringWriter sw = new StringWriter();
		String tpl = IOUtils.toString(GpxConverter.class.getResourceAsStream("/gpx.vm"), Charset.forName("UTF-8"));
		//TODO HTML escape & to &amp; in <groundspeak:finder>
		engine.evaluate(context, sw, "gpx", tpl);
		return sw.toString();
	}

}

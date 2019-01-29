/**
 * 
 */
package com.voice.resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.voice.common.AppConfig;
import com.voice.common.Payload;
import com.voice.common.Text;

/**
 * @author yizhangyin
 *
 */
@Path("/voice")
public class VoiceControlResource {
	private static final String PARAM_ID = "id";

	@GET
	@Path("/v1/txt")
	@Produces({ MediaType.APPLICATION_JSON })
	public Payload<Text> getText(@QueryParam(PARAM_ID) String textId) throws IOException {
		Payload<Text> payload = new Payload<>();
		String command = "python " + AppConfig.getInstance().getPythonExeFilePath();
		Process p = Runtime.getRuntime().exec(command + " " + textId);
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String ret = in.readLine();
		Text data = new Text();
		data.setContent(ret);
		data.setId(textId);
		payload.setData(data);
		payload.setStatus(Response.Status.OK.getStatusCode());
		return payload;
	}

	@POST
	@Path("/v1/save")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Payload<String> save(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
		Payload<String> payload = new Payload<>();
		String fileId = UUID.randomUUID().toString();
		String fileName = AppConfig.getInstance().getVoiceFieFolderlPath() + "/" + fileId + ".wav";
		this.writeToFile(uploadedInputStream, fileName);

		payload.setData(fileId);
		payload.setStatus(Response.Status.OK.getStatusCode());
		return payload;
	}

	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws IOException {

		OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
		int read = 0;
		byte[] bytes = new byte[1024];

		out = new FileOutputStream(new File(uploadedFileLocation));
		while ((read = uploadedInputStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
	}
}

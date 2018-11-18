package br.com.ftpintegration.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.ftpintegration.business.FtpBusiness;
import br.com.ftpintegration.constant.Const;
import br.com.ftpintegration.domain.Filmes;

@Controller
public class HomeController {
	
	private static final String INTERNAL_FILE="irregular-verbs-list.pdf";
    private static final String EXTERNAL_FILE_PATH="C:/mytemp/SpringMVCHibernateManyToManyCRUDExample.zip";
    
	@RequestMapping(value = "/hello")
	public ModelAndView hello(@RequestParam(required = false, defaultValue = "World") String name) {
		ModelAndView ret = new ModelAndView("home");
		// Adds an objet to be used in home.jsp
		ret.addObject("name", name);
		return ret;
	}

	@RequestMapping(value = "/")
	public ModelAndView listaFilmes() {

		ModelAndView mv = new ModelAndView("filmes");
		String ftpUrl = "";
		//String ftpUrl_2 = "";
		
		ftpUrl = String.format(Const.FTP_URL_FILMES, Const.USER, Const.PASS, Const.HOST, Const.DIR_PATH);
		//ftpUrl_2 = String.format(Const.FTP_URL_SERIADOS, Const.USER, Const.PASS, Const.HOST, Const.DIR_PATH);
		System.out.println("URL: " + ftpUrl);

		try {
			
			ArrayList<Filmes> listaNomes = new ArrayList<>();
			ArrayList<String> nameList = FtpBusiness.listFilesFromFtp(ftpUrl);		
			
			for (String s : nameList) {
				Filmes f = new Filmes();
				f.setNome(s);
				listaNomes.add(f);
			}
			
			mv.addObject("listaFilmes", listaNomes);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return mv;
	}

	/*
	 * Download a file from - inside project, located in resources folder. - outside
	 * project, located in File system somewhere.
	 */
	@RequestMapping(value = "/download/{type}", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, @PathVariable("type") String type) throws IOException {	
	    
		File file = null;

		if (type.equalsIgnoreCase("internal")) {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			file = new File(classloader.getResource(INTERNAL_FILE).getFile());
		} else {
			file = new File(EXTERNAL_FILE_PATH);
		}

		if (!file.exists()) {
			String errorMessage = "Sorry. The file you are looking for does not exist";
			System.out.println(errorMessage);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return;
		}

		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			System.out.println("mimetype is not detectable, will take default");
			mimeType = "application/octet-stream";
		}

		System.out.println("mimetype : " + mimeType);

		response.setContentType(mimeType);

		/*
		 * "Content-Disposition : inline" will show viewable types [like
		 * images/text/pdf/anything viewable by browser] right on browser while
		 * others(zip e.g) will be directly downloaded [may provide save as popup, based
		 * on your browser setting.]
		 */
		response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

		/*
		 * "Content-Disposition : attachment" will be directly download, may provide
		 * save as popup, based on your browser setting
		 */
		// response.setHeader("Content-Disposition", String.format("attachment;
		// filename=\"%s\"", file.getName()));

		response.setContentLength((int) file.length());

		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

		// Copy bytes from source to destination(outputstream in this example), closes
		// both streams.
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
}

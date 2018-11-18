package br.com.ftpintegration.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import br.com.ftpintegration.constant.Const;

public class FtpBusiness {
	
	public static BufferedReader getFtpConteudo(String ftpUrl) {
		
		
		InputStream inputStream = null;
		try {
			URL url = new URL(ftpUrl);
			URLConnection conn = url.openConnection();
			inputStream = conn.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));	
		return reader;
	}
	
	public static ArrayList<String> listFilesFromFtp(String url) {
		
		FTPClient ftpClient = new FTPClient();
		ArrayList<String> lista = new ArrayList<>();
		
		try {
			
			ftpClient.connect(Const.HOST_2, 82);      
			ftpClient.login(Const.USER, Const.PASS);
						
			FTPFile[] files = ftpClient.listFiles(Const.DIR_PATH);
			for (FTPFile f : files) {
				lista.add(f.getName());
			}			
            
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ftpClient.isConnected()) {
				try {
					ftpClient.logout();
					ftpClient.disconnect();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return lista;
	}
}

package com.sftp.java;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.util.Properties;

public class SFTP{
public static void main(String[] args){
	try{
		String user = "user";
		String pass = "123";
		Properties config = new Properties();
		config.put("StrictHostKeyChecking","no");
		String host = "10.3.102.89";
		
		JSch jSch = new JSch();
		Session session = jSch.getSession(user,host);
		session.setPassword(pass);
		session.setConfig(config);
		session.connect();
		
		ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
		channelSftp.connect();
		channelSftp.put("C:\\Users\\dmaciel\\eclipse-workspace\\SFTP_JAVA\\TESTE.TXT", "/home/user/ssh");
		System.out.println("Session connnected"+session.isConnected());
		
		channelSftp.disconnect();
		session.disconnect();
	} catch (Exception e){
		e.printStackTrace();
		}
}
}
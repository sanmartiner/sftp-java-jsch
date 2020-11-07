package com.sftp.java;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;

public class clienteSftp {

	public static void main(String[] args) {
		
		sftpMelhorado sftp = new sftpMelhorado("ts.bvsnet.com.br", "pgemt", "pgeMT@2020",9022);
		sftp.conectar();
		
		//sftp.enviarArquivo("C:\\SSH\\new_teste.txt", "/home/user/ssh/");
		
		//sftp.baixarArquivo("/negativacao/retorno/PGEMT_2809200006.RET", "C:\\SSH\\BOA_VISTA\\");
		
		//sftp.renomearArquivo("/home/user/ssh/new_teste_bak.txt", "/home/user/ssh/updateTeste.txt");
		
		//sftp.removerArquivo("/home/user/ssh/TESTE.TXT");
		
//		sftp.listarArquivo("/negativacao/");
//					for(String arquivo: sftp.listarArquivo("/negativacao/")) {
//						System.out.println(arquivo);
//				}
		
		
		
		sftp.desconectar();
	
	}

}

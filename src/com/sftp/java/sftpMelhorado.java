package com.sftp.java;
import com.jcraft.jsch.*;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import java.lang.reflect.Array;
import java.util.*;

public class sftpMelhorado {
    private String host;
    private String usuario;
    private String senha;
    private Properties config = new Properties();
    private JSch jSch;
    private Session session;
    private int porta;
    ChannelSftp channelSftp;
    
    
    public sftpMelhorado(String host, String usuario, String senha, int porta){
        this.host = host;
        this.usuario = usuario;
        this.senha = senha;
        this.porta = porta;
        this.jSch = new JSch();
        config.put("StrictHostKeyChecking","no");
    }
    public sftpMelhorado(String host, String usuario, String senha) {
    	new sftpMelhorado(host, usuario, senha, 22);
    }
   
    
    /**
     * Conectar 
     *@param parametro
     *@return qualquer coisa
     */
    
    public boolean conectar(){
        try{
            session = jSch.getSession(usuario,host, porta);
            session.setPassword(senha);
            session.setConfig(config);
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
        }catch (JSchException e){
            return false;
        }
        return true;
    }

    /**
     * Enviar aquivo
     *@param parametro
     *@return qualquer coisa
     */
     
    public boolean enviarArquivo(String caminhoOrigem, String caminhoDestino){
        try {
            channelSftp.put(caminhoOrigem, caminhoDestino);
        } catch (SftpException ex) {
            return false;
        }
        return true;        
    }
    
    /**
     * Download do arquivo do servidor
     *@param parametro
     *@return qualquer coisa
     */
    
    public boolean baixarArquivo(String caminhoOrigem, String caminhoDestino){
        try {
            channelSftp.get(caminhoOrigem, caminhoDestino);
        } catch (SftpException ex) {
            return false;
        }
        return true;        
    }   
   
    /**
     * Funcao de Teste
     *@param parametro
     *@return qualquer coisa
     */ 
    
    public boolean renomearArquivo(String nomeAntigo, String nomeNovo){
        try {
            channelSftp.rename(nomeAntigo, nomeNovo);
        } catch (SftpException ex) {
            return false;
        }
        return true;        
    }   
    
/**
* Funcao de Teste
*@param parametro
*@return qualquer coisa
*/
    
    public ArrayList<String> listarArquivo(String nomePasta){
        try {
        	ArrayList<String> retorno = new ArrayList<String>();
        	Vector<LsEntry> vector =  channelSftp.ls(nomePasta);
        	for (LsEntry arquivo: vector){
        		if (arquivo.getAttrs().isDir()) {
        			retorno.addAll(listarArquivo(nomePasta+arquivo.getFilename()));
        		} else {
        			retorno.add(arquivo.getFilename());
        		} 
        	}return retorno;
        	
        } catch (SftpException ex) {
            return null;
        }        
    }
    
    public boolean removerArquivo(String nomeArquivo){
        try {
            channelSftp.rm(nomeArquivo);
        } catch (SftpException ex) {
            return false;
        }
        return true;        
    }   
    
    
    public void desconectar(){
        channelSftp.disconnect();
        session.disconnect();
    }
}

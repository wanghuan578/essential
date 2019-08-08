/*************************************************

Description: SL_RPC_DataEncapsulater

Author: wanghuan. 2013-01-20 

Boxin Technology Corporated Corporation. All Rights Reserved.

**************************************************/

package com.spirit.essential.thrift.socketserver.rpc.minicore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class SL_RPC_DataEncapsulater {

	private static final int MAX_DECRYPT_BLOCK = 16;

	private static final int MAX_ENCRYPT_BLOCK = 16;
   
	public byte[] encrypt(byte[] content,int iOffset,int len, String password) throws IOException 
	{   
	   try 
	   {
		   
	    SecretKeySpec key = new SecretKeySpec(password.getBytes("UTF-8"), "AES");
	    
	    Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤�?  
	   // byte[] byteContent = content.getBytes("utf-8");  
	    cipher.init(Cipher.ENCRYPT_MODE, key);// 锟斤拷始锟斤�?  
	    
		 int inputLen = len;
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
		 int offSet = iOffset;
		 byte[] cache = null;
		 int i = 0;
		 
		 // 锟斤拷锟斤拷莘侄谓锟斤拷锟�
		 while (inputLen - offSet > 0) 
		 {
			 if (inputLen - offSet >= MAX_ENCRYPT_BLOCK) 
			 {
		         cache = cipher.doFinal(content, offSet, MAX_ENCRYPT_BLOCK);
		     } 
			 else 
			 {
				 byte [] bTmp = new byte[MAX_ENCRYPT_BLOCK]; 
				 System.arraycopy(content, offSet,bTmp, 0,  inputLen - offSet);
		         cache = cipher.doFinal(bTmp, 0, MAX_ENCRYPT_BLOCK);
		     }
		     out.write(cache, 0, cache.length);
		     i++;
		     offSet += MAX_ENCRYPT_BLOCK;
		 }
		 
		 byte[] decryptedData = out.toByteArray();
		 out.close();
	
	    //byte[] result = cipher.doFinal(byteContent);  
	    return decryptedData; // 锟斤拷锟斤拷          
	   } 
	   catch (NoSuchAlgorithmException e) 
	   {  
	        e.printStackTrace();  
	   } 
	   catch (NoSuchPaddingException e) 
	   {  
	        e.printStackTrace();  
	   } 
	   catch (InvalidKeyException e) 
	   {  
	        e.printStackTrace();  
	   } 
	   catch (UnsupportedEncodingException e) 
	   {  
	        e.printStackTrace();  
	   } catch (IllegalBlockSizeException e) 
	   {  
	        e.printStackTrace();  
	   } 
	   catch (BadPaddingException e) 
	   {  
	        e.printStackTrace();  
	   }  
	   return null;  
	}  
	    
	    
	public byte[] decrypt(byte[] content,int iOffset,int len, String password) throws IOException 
	{  
		try 
		{
			
			SecretKeySpec key = new SecretKeySpec(password.getBytes("UTF-8"), "AES");
	
			
		     Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤�?  
		     cipher.init(Cipher.DECRYPT_MODE, key);// 锟斤拷始锟斤�?  
		     //byte[] result = cipher.doFinal(content);
		     
		     
		     int inputLen = len;
		     ByteArrayOutputStream out = new ByteArrayOutputStream();
		     int offSet = iOffset;
		     byte[] cache = null;
		     int i = 0;
		     
		     // 锟斤拷锟斤拷莘侄谓锟斤拷锟�
		     while (inputLen - offSet > 0) 
		     {
		    	 if (inputLen - offSet >= MAX_DECRYPT_BLOCK) 
		    	 {
		             cache = cipher.doFinal(content, offSet, MAX_DECRYPT_BLOCK);
		         } 
		    	 else 
		    	 {
		    		 byte [] bTmp = new byte[MAX_DECRYPT_BLOCK]; 
	   	    		 System.arraycopy(content, offSet,bTmp, 0,  inputLen - offSet);
	   	             cache = cipher.doFinal(bTmp, 0, MAX_DECRYPT_BLOCK);
		         }
		    	 
		         out.write(cache, 0, cache.length);
		         
		         i++;
		         
		         //offSet = i * MAX_DECRYPT_BLOCK ;
		         offSet  += MAX_DECRYPT_BLOCK ;
		     }
		     
		     byte[] decryptedData = out.toByteArray();
		     
		     out.close();
	
		     return decryptedData; // 锟斤拷锟斤拷   
		} 
		catch (NoSuchAlgorithmException e) 
		{  
		     e.printStackTrace();  
		} 
		catch (NoSuchPaddingException e) 
		{  
		     e.printStackTrace();  
		} 
		catch (InvalidKeyException e) 
		{  
		     e.printStackTrace();  
		} 
		catch (IllegalBlockSizeException e) 
		{  
		     e.printStackTrace();  
		} 
		catch (BadPaddingException e) 
		{  
		     e.printStackTrace();  
		}
		
		return null;  
	}
	
	/**  
     * 十锟斤拷锟斤拷锟阶拷锟斤拷�?���?
     * @param String str Byte锟街凤拷(Byte之锟斤拷锟睫分革拷锟�锟斤拷:[616C6B]) 
     * @return String 锟斤拷应锟斤拷锟街凤�?
     */    
    public String hexStr2Str(String hexStr)  
    {    
        String str = "0123456789ABCDEF";    
        char[] hexs = hexStr.toCharArray();    
        byte[] bytes = new byte[hexStr.length() / 2];    
        int n;    
      
        for (int i = 0; i < bytes.length; i++)  
        {    
            n = str.indexOf(hexs[2 * i]) * 16;    
            n += str.indexOf(hexs[2 * i + 1]);    
            bytes[i] = (byte) (n & 0xff);    
        }    
        return new String(bytes);    
     } 
    
    public byte [] hexStr2Str(byte [] hexStr)  
    {    
        String str = "0123456789ABCDEF";    
        byte[] hexs = hexStr;    
        byte[] bytes = new byte[hexStr.length / 2];    
        int n;    
      
        for (int i = 0; i < bytes.length; i++)  
        {    
            n = str.indexOf(hexs[2 * i]) * 16;    
            n += str.indexOf(hexs[2 * i + 1]);    
            bytes[i] = (byte) (n & 0xff);    
        }    
        return (bytes);    
     } 
    
    public byte [] hexStr2Str(byte [] hexStr, int iLen)  
    {    
        String str = "0123456789ABCDEF";    
        byte[] hexs = hexStr;    
        byte[] bytes = new byte[iLen / 2];    
        int n;    
      
        for (int i = 0; i < bytes.length; i++)  
        {    
            n = str.indexOf(hexs[2 * i]) * 16;    
            n += str.indexOf(hexs[2 * i + 1]);    
            bytes[i] = (byte) (n & 0xff);    
        }    
        return (bytes);    
     }
}

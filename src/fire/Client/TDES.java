package fire.Client;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;


public class TDES {  
	private SecretKey deskey;			//对称密钥
	private Cipher c;					//完成加解密
	public TDES() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException{
		// 生成密钥
		String s="93459353356462321334234324";
		byte[] by=s.getBytes();
		DESedeKeySpec dks = new DESedeKeySpec(by);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
		deskey = keyfactory.generateSecret(dks);

		c = Cipher.getInstance("DESede");
	}

	//加密
	public String Encrytor(String s) throws InvalidKeyException,IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {  
		// 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式  
		c.init(Cipher.ENCRYPT_MODE, deskey); 
		byte[] by=c.doFinal(s.getBytes());
		return Base64.encode(by);  
	}

	//解密
	public String Decryptor(String s) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {  
		// 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示解密模式  
		c.init(Cipher.DECRYPT_MODE, deskey);
		byte[] by =c.doFinal(Base64.decode(s));
		return new String(by);  
	}
}


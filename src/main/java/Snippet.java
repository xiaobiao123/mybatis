import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Snippet {
	
	public static void main(String[] args) {
		Snippet st=new Snippet();
		st.sign("1231231".getBytes());
    }
	/** 加签 **/
	public String sign(byte[] srcData) {
		byte[] privateKey = null;
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;

		try {
			fis = new FileInputStream(new File("private.cr.key"));

			baos = new ByteArrayOutputStream();
			int ch = -1;
			while ((ch = fis.read()) != -1)
				baos.write((byte) ch);

			privateKey = baos.toByteArray();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("数字签名组件@加载私钥文件失败！");
		} finally {
			try {
				fis.close();
				baos.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("数字签名组件@关闭文件异常！");
			}
		}

		PrivateKey priKey = null;
		// 生成PrivateKey对象
		try {
			KeyFactory factory = KeyFactory.getInstance("RSA");
			priKey = factory.generatePrivate(new PKCS8EncodedKeySpec(privateKey));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数字签名组件@生成私钥对象失败！");
		}

		// 签名
		byte[] signData = null;
		try {
			System.out.println("数字签名组件@开始签名........");
			Signature sig = Signature.getInstance("SHA1WithRSA");
			sig.initSign(priKey);
			sig.update(srcData);
			signData = sig.sign();

			System.out.println("数字签名组件@签名成功！");

			BASE64Encoder b64enc = new BASE64Encoder();
			return b64enc.encode(signData);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数字签名组件@签名失败！");
		}
		return "";
	}

	/** 验签 **/
	public boolean verify(String signed, byte[] srcData) {
		byte[] publicKey = null;
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		boolean isOk = false;

		try {
			fis = new FileInputStream(new File("public.cr.key"));

			baos = new ByteArrayOutputStream();
			int ch = -1;
			while ((ch = fis.read()) != -1)
				baos.write((byte) ch);

			publicKey = baos.toByteArray();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("数字签名组件@加载私钥文件失败！");
		} finally {
			try {
				fis.close();
				baos.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("数字签名组件@关闭文件异常！");
			}
		}

		PublicKey pubKey = null;
		// 生成PrivateKey对象
		try {
			X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(publicKey);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			pubKey = kf.generatePublic(pubKeySpec);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数字签名组件@生成私钥对象失败！");
		}

		// 签名
		byte[] signData = null;
		try {
			System.out.println("数字签名组件@开始验签........");
			Signature sig = Signature.getInstance("SHA1WithRSA");
			sig.initVerify(pubKey);
			sig.update(srcData);
			// base64解密
			BASE64Decoder b64dec = new BASE64Decoder();
			byte[] sigData = b64dec.decodeBuffer(signed);

			// 验签
			isOk = sig.verify(sigData);

			System.out.println("数字签名组件@验签成功！");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数字签名组件@验签失败！");
		}
		return isOk;
	}
}

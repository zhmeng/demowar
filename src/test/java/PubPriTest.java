import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.RSAPrivateKeyStructure;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;

/**
 * Created by zhangmeng on 16-7-4.
 */
public class PubPriTest {

    public static String getPriStr() throws Exception{
        BufferedReader priReader = null;
        try{
            priReader = new BufferedReader(new FileReader("/tmp/pubpri/rsa_private_key.pem"));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = priReader.readLine()) != null) {
                sb.append(readLine);
            }
            return sb.toString();
        }finally {
            priReader.close();
        }
    }

    public static RSAPrivateKey loadPrivateKey(String privateKeyStr) throws Exception{
        try {
            BASE64Decoder base64Decoder= new BASE64Decoder();
            byte[] buffer= base64Decoder.decodeBuffer(privateKeyStr);
            RSAPrivateKeyStructure asn1PrivKey = new RSAPrivateKeyStructure((ASN1Sequence) ASN1Sequence.fromByteArray(buffer));
            RSAPrivateKeySpec rsaPrivKeySpec = new RSAPrivateKeySpec(asn1PrivKey.getModulus(), asn1PrivKey.getPrivateExponent());
            KeyFactory keyFactory= KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(rsaPrivKeySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (IOException e) {
            throw new Exception("私钥数据内容读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    public static void main(String[] args) throws Exception{
        System.out.println(getPriStr());
        String finalStr = getPriStr().replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("-----END RSA PRIVATE KEY-----", "");
        System.out.println(finalStr);
        RSAPrivateKey rsaPrivateKey = loadPrivateKey(finalStr);
    }
}

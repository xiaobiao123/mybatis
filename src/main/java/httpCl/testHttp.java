package httpCl;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.goujia.goujiabao.model.GJWAnsAffirm;
import com.goujia.goujiabao.util.Base64;
import com.goujia.goujiabao.util.XMLUtil;

public class testHttp {
	/**
	 * 保证金确认支付
	 * 
	 * @param orderId
	 * @param repayId
	 * @return
	 * @throws Exception
	 */
	public GJWAnsAffirm verifyCashDeposit(String xmlStr,String signature) throws Exception {
		GJWAnsAffirm order = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			//http://www.webxml.com.cn/WebServices/TranslatorWebService.asmx?disco
			HttpPost httppost = new HttpPost("http://localhost:8089/api/goujiabao/confirmPaymentNotice?reqdata="+xmlStr+"&signature="+signature+"&charset=utf-8" );
			CloseableHttpResponse responseBody = httpclient.execute(httppost);
			String stringXml=EntityUtils.toString(responseBody.getEntity());
			order=	(GJWAnsAffirm) XMLUtil.parseXML(Base64.decode(stringXml), new GJWAnsAffirm());
		} finally {
			httpclient.close();
			return order;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		testHttp tp=new testHttp();
		String xmlstr="PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iR0JLIj8+PHBhY2thZ2U+PHB1Yj48b3JnY29kZT5HSlc8L29yZ2NvZGU+PHR4Y29kZT4yMDI4MDwvdHhjb2RlPjxjbXBkYXRlPjIwMTUwNzAyPC9jbXBkYXRlPjxjbXB0aW1lPjExMzU1NTwvY21wdGltZT48Y21wdHhzbm8gPjE0MzU4MDgxNTU0MzY8L2NtcHR4c25vPjxvcmRlcnN0YXR1cyA+bnVsbDwvb3JkZXJzdGF0dXM+PC9wdWI+PHJlcT48cGF5dHlwZT4wPC9wYXl0eXBlPjxjb250cmFjdG5vPjQyPC9jb250cmFjdG5vPjxzdWJzZWxsZXJpZD50ZXN0PC9zdWJzZWxsZXJpZD48b3JkZXJubz4xNDM0MDk2MDk5MDg1PC9vcmRlcm5vPjxhbW91bnQ+MC4zMDAwPC9hbW91bnQ+PG1vYmlsZT5udWxsPC9tb2JpbGU+PC9yZXE+PC9wYWNrYWdlPg==";
		xmlstr.replace("+", "7777777777");
		System.out.println();
		tp.verifyCashDeposit(xmlstr, null);
    }
}

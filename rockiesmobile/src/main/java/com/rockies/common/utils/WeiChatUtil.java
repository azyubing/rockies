package com.rockies.common.utils;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * 微信接口类
 * 
 */
public class WeiChatUtil {

	private String getOAuth2UserInfoLink = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope=snsapi_userinfo&state=123&connect_redirect=1#wechat_redirect";

	private String getOAuth2BaseLink = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect";

	/**
	 * 通过code换取网页授权access_token ()
	 */
	private String getOauth2Url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";

	private String getUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang=zh_CN";
	/**
	 * 检验授权凭证（access_token）是否有效 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 */
	private String validUserTokenUrl = "https://api.weixin.qq.com/sns/auth?access_token={0}&openid={1}";

	private HttpClient webClient;

	private Log log = LogFactory.getLog(getClass());

	public WeiChatUtil(String proxyHost, int proxyPort) {
		this();
		if (webClient != null && !StringUtils.isEmpty(proxyHost)) {
			HttpHost proxy = new HttpHost(proxyHost, proxyPort);
			webClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		}
	}

	/**
	 * @desc 初始化创建 WebClient
	 */
	public WeiChatUtil() {
		log.info("initWebClient start....");
		try {
			PoolingClientConnectionManager tcm = new PoolingClientConnectionManager();
			tcm.setMaxTotal(10);
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {

				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

				}

				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new X509TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			Scheme sch = new Scheme("https", 443, ssf);
			tcm.getSchemeRegistry().register(sch);
			webClient = new DefaultHttpClient(tcm);
		} catch (Exception ex) {
			log.error("initWebClient exception", ex);
		} finally {
			log.info("initWebClient end....");
		}
	}

	public String getOAuth2UserInfoURL(String appid, String callbackURL) {
		if (log.isInfoEnabled()) {
			log.info("begin to generate the OAuth2 userinfo link...");
		}
		return MessageFormat.format(this.getOAuth2UserInfoLink, appid, callbackURL);
	}

	public String getOAuth2BaseURL(String appid, String callbackURL) {
		if (log.isInfoEnabled()) {
			log.info("begin to generate the OAuth2 user base link...");
		}
		return MessageFormat.format(this.getOAuth2BaseLink, appid, callbackURL);
	}

	/**
	 * 通过code换取网页授权
	 * 
	 * @param appid
	 * @param secret
	 * @param code
	 * @return {"access_token":"ACCESS_TOKEN","expires_in":7200,"refresh_token":
	 *         "REFRESH_TOKEN","openid":"OPENID","scope":"SCOPE"}
	 */
	public Map<String, Object> getOauth2Info(String appid, String secret, String code) {
		try {
			log.info("getOauth2Info start.{code:" + code + "}");
			String url = MessageFormat.format(this.getOauth2Url, appid, secret, code);
			String response = executeHttpGet(url);
			Map<String, Object> map = doJson2Map(response);
			return map;
		} catch (Exception e) {
			log.error("getOauth2Info exception", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 用户信息
	 * 
	 * @param appid
	 * @param secret
	 * @param code
	 * @return { "openid":"OPENID", "nickname":"NICKNAME", "sex":1,
	 *         "province":"PROVINCE", "city":"CITY", "country":"COUNTRY",
	 *         "headimgurl":
	 *         "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0"
	 *         , "privilege":[ "PRIVILEGE1", "PRIVILEGE2" ], "unionid":
	 *         " o6_bmasdasdsad6_2sgVt7hMZOPfL"
	 * 
	 *         }
	 */
	public Map<String, Object> getUserInfo(String accessToken, String openId) {
		try {
			log.info("getUserInfo start.{openId:" + openId + "}");
			String url = MessageFormat.format(this.getUserInfo, accessToken, openId);
			String response = executeHttpGet(url);
			Map<String, Object> map = doJson2Map(response);

			return map;
		} catch (Exception e) {
			log.error("getOauth2Info exception", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * @desc 发起HTTP GET请求返回数据
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public String executeHttpGet(String url) throws IOException, ClientProtocolException {
		ResponseHandler<?> responseHandler = new BasicResponseHandler();
		String response = (String) this.webClient.execute(new HttpGet(url), responseHandler);
		if (log.isInfoEnabled()) {
			log.info("return response=====start======");
			log.info(response);
			log.info("return response=====end======");
		}
		return response;
	}

	/**
	 * 检验授权凭证（access_token）是否有效 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 * 
	 * @param openid
	 * @param token
	 * @return
	 */
	public Map<String, Object> validUserToken(String openid, String token) {
		try {
			log.info("validUserToken start.{openid:" + openid + ",access_token:" + token + "}");
			String url = MessageFormat.format(this.validUserTokenUrl, token, openid);
			String response = executeHttpGet(url);
			Map<String, Object> map = doJson2Map(response);

			return map;
		} catch (Exception e) {
			log.error("get user info exception", e);
		}
		return null;
	}

	/**
	 * 转换json到Map
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 */
	private Map<String, Object> doJson2Map(String response)
			throws IOException, JsonParseException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(response, new TypeReference<Map<String, Object>>() {
		});
		return map;
	}

	public String saveToFile(String destUrl) {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		try {
			url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());

			String path = CommonUtils.getIPConfigBykey("imagePath");
			String filename = UUID.randomUUID().toString() + ".jpg";
			fos = new FileOutputStream(path + filename);
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.flush();
			return CommonUtils.getIPConfigBykey("imageShowPath") + filename;
		} catch (IOException e) {
		} catch (ClassCastException e) {
		} finally {
			try {
				fos.close();
				bis.close();
				httpUrl.disconnect();
			} catch (IOException e) {
			} catch (NullPointerException e) {
			}
		}
		return null;
	}

	/**
	 * 获取token接口
	 */
	private String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";

	/**
	 * 获取授权token
	 * 
	 * @param appid
	 * @param secret
	 * @return 成功：{"access_token":"ACCESS_TOKEN","expires_in":7200} 失败：null
	 */
	public Map<String, Object> getAccessToken(String appid, String secret) {
		Map<String, Object> map = null;
		try {
			log.info("getAccessToken start.{appid=" + appid + ",secret:" + secret + "}");
			String url = MessageFormat.format(this.getTokenUrl, appid, secret);
			String response = executeHttpGet(url);

			map = doJson2Map(response);

		} catch (Exception e) {
			log.error("getAccessToken exception", e);
		}
		return map;
	}

	public static void main(String[] args) {
		WeiChatUtil w = new WeiChatUtil();
		w.getAccessToken("wxa4a9620e264e143f", "d4624c36b6795d1d99dcf0547af5443d");
	}
}

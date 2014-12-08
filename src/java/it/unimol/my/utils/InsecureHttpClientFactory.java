package it.unimol.my.utils;

import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * <p>
 * Sample factory for building a HttpClient that configures a HttpClient
 * instance to store cookies and to accept SSLcertificates without HostName
 * validation.
 * </p>
 * <p>
 * You obviously should not use this class in production, but it may come handy
 * when developing with internal Servers using self-signed certificates.
 * </p>
 */
@SuppressWarnings("deprecation")
public class InsecureHttpClientFactory {

	private static HttpClient INSECURE_CLIENT;

	/**
	 * Restituisce una istanza di client http che non considera l'hostname per
	 * la validazione del certificato ssl.
	 * 
	 * @return Una istanza di <code>HttpClient</code>
	 */
	public static HttpClient getInsecureClient() {
		if (InsecureHttpClientFactory.INSECURE_CLIENT == null)
			InsecureHttpClientFactory.INSECURE_CLIENT = InsecureHttpClientFactory
					.build();
		return InsecureHttpClientFactory.INSECURE_CLIENT;
	}

	private static HttpClient build() {
		HttpClient hc = new DefaultHttpClient();
		// configureProxy(hc);
		// configureCookieStore(hc);
		configureSSLHandling(hc);
		return hc;
	}

	private void configureProxy(HttpClient hc) {
		HttpHost proxy = new HttpHost("proxy.example.org", 3182);
		hc.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
	}

	private void configureCookieStore(HttpClient hc) {
		CookieStore cStore = new BasicCookieStore();
		((DefaultHttpClient) hc).setCookieStore(cStore);
	}

	private static void configureSSLHandling(HttpClient hc) {
		Scheme http = new Scheme("http", 80,
				PlainSocketFactory.getSocketFactory());
		SSLSocketFactory sf = buildSSLSocketFactory();
		Scheme https = new Scheme("https", 443, sf);
		SchemeRegistry sr = hc.getConnectionManager().getSchemeRegistry();
		sr.register(http);
		sr.register(https);
	}

	private static SSLSocketFactory buildSSLSocketFactory() {
		TrustStrategy ts = new TrustStrategy() {
			@Override
			public boolean isTrusted(X509Certificate[] x509Certificates,
					String s) throws CertificateException {
				return true; // heck yea!
			}
		};

		SSLSocketFactory sf = null;

		try {
			/* build socket factory with hostname verification turned off. */
			sf = new SSLSocketFactory(ts,
					SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			// log.error("Failed to initialize SSL handling.", e);
		} catch (KeyManagementException e) {
			e.printStackTrace();
			// log.error("Failed to initialize SSL handling.", e);
		} catch (KeyStoreException e) {
			e.printStackTrace();
			// log.error("Failed to initialize SSL handling.", e);
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
			// log.error("Failed to initialize SSL handling.", e);
		}

		return sf;
	}

}
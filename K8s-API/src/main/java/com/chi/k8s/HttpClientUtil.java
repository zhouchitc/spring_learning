package org.apache.flink.yarn.cli;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** HttpClientUtil. */
public class HttpClientUtil {
    private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
    private static PoolingHttpClientConnectionManager connMgr =
            new PoolingHttpClientConnectionManager();
    private static RequestConfig requestConfig;
    private static final int MAX_TOTAL = 100;
    private static final int MAX_TIMEOUT = 7000;
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int SOCKET_TIMEOUT = 40000;
    private static final String CHARSET = "UTF-8";

    public HttpClientUtil() {}

    public static String doGet(String url) throws Exception {
        return doGet(url, new HashMap());
    }

    public static String doGet(String url, Map<String, Object> params) throws Exception {
        String result = null;
        if (StringUtils.isEmpty(url)) {
            log.info("warn:doGet url is null or '' ");
            return result;
        } else {
            List<NameValuePair> pairList = new ArrayList(params.size());
            Iterator var4 = params.entrySet().iterator();

            while (var4.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry) var4.next();
                NameValuePair pair =
                        new BasicNameValuePair(
                                (String) entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }

            log.info("http请求地址:" + url + "?" + params);
            CloseableHttpResponse response = null;
            InputStream instream = null;
            CloseableHttpClient httpclient = HttpClients.createDefault();

            try {
                URIBuilder uriBuilder = new URIBuilder(url);
                uriBuilder.addParameters(pairList);
                URI uri = uriBuilder.build();
                HttpGet httpGet = new HttpGet(uri);
                response = httpclient.execute(httpGet);
                int statusCode = response.getStatusLine().getStatusCode();
                log.info("doGet statusCode:{}", statusCode);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    instream = entity.getContent();
                    result = IOUtils.toString(instream, "UTF-8");
                    log.info("doGet Result:{}", result);
                }
            } catch (IOException var16) {
                log.error("doGet  IO ERROR :{}", var16.getMessage());
            } catch (URISyntaxException var17) {
                log.error("doGet URISyntaxException :{}", var17.getMessage());
            } finally {
                if (null != instream) {
                    instream.close();
                }

                if (null != response) {
                    response.close();
                }

                if (null != httpclient) {
                    httpclient.close();
                }

                log.info("close  instream response httpClient  connection succ");
            }

            return result;
        }
    }

    public static String doGet(String url, Map<String, Object> params, String charset)
            throws Exception {
        String result = null;
        if (StringUtils.isEmpty(url)) {
            log.info("warn:doGet url is null or '' ");
            return result;
        } else {
            List<NameValuePair> pairList = new ArrayList(params.size());
            Iterator var5 = params.entrySet().iterator();

            while (var5.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry) var5.next();
                NameValuePair pair =
                        new BasicNameValuePair(
                                (String) entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }

            log.info("http请求地址:" + url + "?" + params);
            CloseableHttpResponse response = null;
            InputStream instream = null;
            CloseableHttpClient httpclient = HttpClients.createDefault();

            try {
                URIBuilder uriBuilder = new URIBuilder(url);
                uriBuilder.addParameters(pairList);
                URI uri = uriBuilder.build();
                HttpGet httpGet = new HttpGet(uri);
                response = httpclient.execute(httpGet);
                int statusCode = response.getStatusLine().getStatusCode();
                log.info("doGet statusCode:{}", statusCode);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    instream = entity.getContent();
                    result = IOUtils.toString(instream, charset);
                    log.info("doGet Result:{}", result);
                }
            } catch (IOException var17) {
                log.error("doGet  IO ERROR :{}", var17.getMessage());
            } catch (URISyntaxException var18) {
                log.error("doGet URISyntaxException :{}", var18.getMessage());
            } finally {
                if (null != instream) {
                    instream.close();
                }

                if (null != response) {
                    response.close();
                }

                if (null != httpclient) {
                    httpclient.close();
                }

                log.info("close  instream response httpClient  connection succ");
            }

            return result;
        }
    }
    public static String doPut(String url, Map<String, Object> params) throws Exception {
        String result = null;
        String param = "";

        if (StringUtils.isEmpty(url)) {
            log.info("warn:doPut url is null or '' ");
            return result;
        } else {
            List<NameValuePair> pairList = new ArrayList(params.size());
            Iterator var5 = params.entrySet().iterator();

            while (var5.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry) var5.next();
                NameValuePair pair =
                        new BasicNameValuePair(
                                (String) entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
                if (param.equals("")) {
                    param = (String) entry.getKey() + "=" + entry.getValue();
                } else {
                    param = param + "&" + (String) entry.getKey() + "=" + entry.getValue();
                }
            }

            log.info("http请求地址:" + url + "?" + param);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(url);
            CloseableHttpResponse response = null;
            InputStream instream = null;

            try {
                httpPut.setConfig(requestConfig);
                httpPut.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
                response = httpClient.execute(httpPut);
                int statusCode = response.getStatusLine().getStatusCode();
                log.info("doPut statusCode:{}", statusCode);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    instream = entity.getContent();
                    result = IOUtils.toString(instream, "UTF-8");
                    log.info("doPut Result:{}", result);
                }
            } catch (IOException var14) {
                log.error("doPut  ERROR :{}", var14.getMessage());
            } finally {
                if (null != instream) {
                    instream.close();
                }

                if (null != response) {
                    response.close();
                }

                if (null != httpClient) {
                    httpClient.close();
                }

                log.info("close  instream response httpClient  connection succ");
            }

            return result;
        }
    }

    public static String doPost(String apiUrl) throws Exception {
        return doPost(apiUrl, (Map) (new HashMap()));
    }

    public static String doPost(String url, Map<String, Object> params) throws Exception {
        String result = null;
        String param = "";
        if (StringUtils.isEmpty(url)) {
            log.info("warn:doPost url is null or '' ");
            return result;
        } else {
            List<NameValuePair> pairList = new ArrayList(params.size());
            Iterator var5 = params.entrySet().iterator();

            while (var5.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry) var5.next();
                NameValuePair pair =
                        new BasicNameValuePair(
                                (String) entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
                if (param.equals("")) {
                    param = (String) entry.getKey() + "=" + entry.getValue();
                } else {
                    param = param + "&" + (String) entry.getKey() + "=" + entry.getValue();
                }
            }

            log.info("http请求地址:" + url + "?" + param);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            CloseableHttpResponse response = null;
            InputStream instream = null;

            try {
                httpPost.setConfig(requestConfig);
                httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
                response = httpClient.execute(httpPost);
                int statusCode = response.getStatusLine().getStatusCode();
                log.info("doPost statusCode:{}", statusCode);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    instream = entity.getContent();
                    result = IOUtils.toString(instream, "UTF-8");
                    log.info("doPost Result:{}", result);
                }
            } catch (IOException var14) {
                log.error("doPost  ERROR :{}", var14.getMessage());
            } finally {
                if (null != instream) {
                    instream.close();
                }

                if (null != response) {
                    response.close();
                }

                if (null != httpClient) {
                    httpClient.close();
                }

                log.info("close  instream response httpClient  connection succ");
            }

            return result;
        }
    }

    public static String doPost(String url, String xml) throws Exception {
        String result = null;
        if (StringUtils.isEmpty(url)) {
            log.info("warn:doPost url is null or '' ");
            return result;
        } else {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            CloseableHttpResponse response = null;
            InputStream instream = null;

            try {
                log.info("短信请求服务器地址:" + url + "?" + xml);
                httpPost.setConfig(requestConfig);
                httpPost.setEntity(new StringEntity(xml, "GBK"));
                response = httpClient.execute(httpPost);
                int statusCode = response.getStatusLine().getStatusCode();
                log.info("doPost statusCode:{}", statusCode);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    instream = entity.getContent();
                    result = IOUtils.toString(instream, "UTF-8");
                }
            } catch (IOException var12) {
                log.error("doPost  ERROR :{}", var12.getMessage());
            } finally {
                if (null != instream) {
                    instream.close();
                }

                if (null != response) {
                    response.close();
                }

                if (null != httpClient) {
                    httpClient.close();
                }

                log.info("close  instream response httpClient  connection succ");
            }

            return result;
        }
    }

    public static String doPost(String url, Object json) throws Exception {
        String result = null;
        if (StringUtils.isEmpty(url)) {
            log.info("warn:doPostByJson url is null or '' ");
            return result;
        } else {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            CloseableHttpResponse response = null;
            InputStream instream = null;

            try {
                httpPost.setConfig(requestConfig);
                StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
                stringEntity.setContentEncoding("UTF-8");
                stringEntity.setContentType("application/json");
                httpPost.setEntity(stringEntity);
                response = httpClient.execute(httpPost);
                int statusCode = response.getStatusLine().getStatusCode();
                log.info("doPost statusCode:{}", statusCode);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    instream = entity.getContent();
                    result = IOUtils.toString(instream, "UTF-8");
                }
            } catch (IOException var13) {
                log.error("doPost BY JSON ERROR :{}", var13.getMessage());
            } finally {
                if (null != instream) {
                    instream.close();
                }

                if (null != response) {
                    response.close();
                }

                if (null != httpClient) {
                    httpClient.close();
                }
            }

            return result;
        }
    }

    public static String doPostPay(String url, Object json) throws Exception {
        String result = null;
        if (StringUtils.isEmpty(url)) {
            log.info("warn:doPostByJson url is null or '' ");
            return result;
        } else {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            CloseableHttpResponse response = null;
            InputStream instream = null;

            try {
                httpPost.setConfig(requestConfig);
                StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
                httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
                httpPost.setHeader("Accept", "application/json");
                stringEntity.setContentEncoding("UTF-8");
                stringEntity.setContentType("application/json");
                httpPost.setEntity(stringEntity);
                response = httpClient.execute(httpPost);
                int statusCode = response.getStatusLine().getStatusCode();
                log.info("doPost statusCode:{}", statusCode);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    instream = entity.getContent();
                    result = IOUtils.toString(instream, "UTF-8");
                }
            } catch (IOException var13) {
                log.error("doPost BY JSON ERROR :{}", var13.getMessage());
            } finally {
                if (null != instream) {
                    instream.close();
                }

                if (null != response) {
                    response.close();
                }

                if (null != httpClient) {
                    httpClient.close();
                }
            }

            return result;
        }
    }

    public static String doPostSSL(String apiUrl, Map<String, Object> params) throws Exception {
        String result = null;
        if (StringUtils.isEmpty(apiUrl)) {
            log.info("warn:doPostSSL url is null or '' ");
            return result;
        } else {
            CloseableHttpClient httpClient =
                    HttpClients.custom()
                            .setSSLSocketFactory(createSSLConnSocketFactory())
                            .setConnectionManager(connMgr)
                            .setDefaultRequestConfig(requestConfig)
                            .build();
            HttpPost httpPost = new HttpPost(apiUrl);
            CloseableHttpResponse response = null;
            InputStream instream = null;

            try {
                httpPost.setConfig(requestConfig);
                List<NameValuePair> pairList = new ArrayList(params.size());
                Iterator var8 = params.entrySet().iterator();

                Map.Entry entry;
                while (var8.hasNext()) {
                    entry = (Map.Entry) var8.next();
                    NameValuePair pair =
                            new BasicNameValuePair(
                                    (String) entry.getKey(), entry.getValue().toString());
                    pairList.add(pair);
                }

                httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
                response = httpClient.execute(httpPost);
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    log.info("doPostSSL statusCode:{}", statusCode);
                    entry = null;
                    return String.valueOf(entry);
                }

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    instream = entity.getContent();
                    result = IOUtils.toString(instream, "UTF-8");
                }
            } catch (Exception var14) {
                log.error("doPostSSL ERROR :{}", var14.getMessage());
            } finally {
                if (null != instream) {
                    instream.close();
                }

                if (null != response) {
                    response.close();
                }

                if (null != httpClient) {
                    httpClient.close();
                }

                log.info("close  instream response httpClient  connection succ");
            }

            return result;
        }
    }

    public static String doPostSSL(String apiUrl, Object json) throws Exception {
        String result = null;
        if (StringUtils.isEmpty(apiUrl)) {
            log.info("warn:doPostSSL By Json url is null or '' ");
            return result;
        } else {
            CloseableHttpClient httpClient =
                    HttpClients.custom()
                            .setSSLSocketFactory(createSSLConnSocketFactory())
                            .setConnectionManager(connMgr)
                            .setDefaultRequestConfig(requestConfig)
                            .build();
            HttpPost httpPost = new HttpPost(apiUrl);
            CloseableHttpResponse response = null;
            InputStream instream = null;

            HttpEntity entity;
            try {
                httpPost.setConfig(requestConfig);
                StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
                stringEntity.setContentEncoding("UTF-8");
                stringEntity.setContentType("application/json");
                httpPost.setEntity(stringEntity);
                response = httpClient.execute(httpPost);
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    entity = response.getEntity();
                    if (entity != null) {
                        instream = entity.getContent();
                        result = IOUtils.toString(instream, "UTF-8");
                    }

                    return result;
                }

                log.info("doPostSSL by json statusCode:{}", statusCode);
                entity = null;
            } catch (Exception var13) {
                log.error("doPostSSL BY JSON ERROR :{}", var13.getMessage());
                return result;
            } finally {
                if (null != instream) {
                    instream.close();
                }

                if (null != response) {
                    response.close();
                }

                if (null != httpClient) {
                    httpClient.close();
                }

                log.info("close  instream response httpClient  connection succ");
            }

            return String.valueOf(entity);
        }
    }

    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;

        try {
            SSLContext sslContext =
                    (new SSLContextBuilder())
                            .loadTrustMaterial(
                                    (KeyStore) null,
                                    new TrustStrategy() {
                                        public boolean isTrusted(
                                                X509Certificate[] chain, String authType)
                                                throws CertificateException {
                                            return true;
                                        }
                                    })
                            .build();
            sslsf =
                    new SSLConnectionSocketFactory(
                            sslContext,
                            new X509HostnameVerifier() {
                                public boolean verify(String arg0, SSLSession arg1) {
                                    return true;
                                }

                                public void verify(String host, SSLSocket ssl) throws IOException {}

                                public void verify(String host, X509Certificate cert)
                                        throws SSLException {}

                                public void verify(String host, String[] cns, String[] subjectAlts)
                                        throws SSLException {}
                            });
        } catch (GeneralSecurityException var2) {
            log.error("createSSLConnSocketFactory ERROR :{}", var2.getMessage());
        }

        return sslsf;
    }

    public static String doPostPay(String url, Object json, String authorization) throws Exception {
        String result = null;
        if (StringUtils.isEmpty(url)) {
            log.info("warn:doPostByJson url is null or '' ");
            return result;
        } else {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            CloseableHttpResponse response = null;
            InputStream instream = null;

            try {
                httpPost.setConfig(requestConfig);
                StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
                httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Authorization", authorization);
                stringEntity.setContentEncoding("UTF-8");
                stringEntity.setContentType("application/json");
                httpPost.setEntity(stringEntity);
                response = httpClient.execute(httpPost);
                int statusCode = response.getStatusLine().getStatusCode();
                log.info("doPost statusCode:{}", statusCode);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    instream = entity.getContent();
                    result = IOUtils.toString(instream, "UTF-8");
                }
            } catch (IOException var14) {
                log.error("doPost BY JSON ERROR :{}", var14.getMessage());
            } finally {
                if (null != instream) {
                    instream.close();
                }

                if (null != response) {
                    response.close();
                }

                if (null != httpClient) {
                    httpClient.close();
                }
            }

            return result;
        }
    }

    public static String doPostPayUpgraded(String url, Object json, String authorization)
            throws Exception {
        String result = null;
        if (StringUtils.isEmpty(url)) {
            log.info("新支付接口url不能为空！");
            return result;
        } else {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            CloseableHttpResponse response = null;
            InputStream instream = null;

            try {
                httpPost.setConfig(requestConfig);
                StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
                httpPost.setHeader("Authorization", authorization);
                stringEntity.setContentEncoding("UTF-8");
                stringEntity.setContentType("application/json");
                httpPost.setEntity(stringEntity);
                response = httpClient.execute(httpPost);
                int statusCode = response.getStatusLine().getStatusCode();
                log.info("新支付请求状态 statusCode:{}", statusCode);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    instream = entity.getContent();
                    result = IOUtils.toString(instream, "UTF-8");
                }
            } catch (IOException var14) {
                log.error("新支付接口发送异常:{}", var14.getMessage());
            } finally {
                if (null != instream) {
                    instream.close();
                }

                if (null != response) {
                    response.close();
                }

                if (null != httpClient) {
                    httpClient.close();
                }
            }

            return result;
        }
    }

    static {
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(100);
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectTimeout(10000);
        configBuilder.setSocketTimeout(40000);
        configBuilder.setConnectionRequestTimeout(7000);
        configBuilder.setStaleConnectionCheckEnabled(true);
        requestConfig = configBuilder.build();
    }
}

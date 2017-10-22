package com.alia.testchat.network;

import android.content.Context;

import com.alia.testchat.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alyona on 20.10.2017.
 */

public class RetrofitService {
    private static RetrofitService instance;
    private ChatAPI serviceImpl;

    public RetrofitService(OkHttpClient httpClient) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build();

        serviceImpl = retrofit.create(ChatAPI.class);
    }

    public static void initInstance(Context context) {
        try {
            instance = new RetrofitService(buildHttpClient(context));
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public static RetrofitService getInstance() {
        return instance;
    }

    public ChatAPI getApi() {
        return serviceImpl;
    }

    private static OkHttpClient buildHttpClient(Context context) throws CertificateException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        // Load CAs from a resource
        CertificateFactory cf;

        cf = CertificateFactory.getInstance("X.509");

        InputStream caInput = context.getResources().openRawResource(R.raw.db2dev_certificate);
        Certificate ca;
        try {
            ca = cf.generateCertificate(caInput);
            System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
        } finally {
            caInput.close();
        }

        // Create a KeyStore containing trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);

        // Create a TrustManager that trusts the CAs in KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm);
        trustManagerFactory.init(keyStore);

        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        }
        X509TrustManager trustManager = (X509TrustManager) trustManagers[0];

        // Create an SSLContext that uses our TrustManager
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{trustManager}, null);

        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustManager)
                .addInterceptor(new AuthInterceptor("iostest", "iostest2k17!"))
                .addInterceptor(logging)
                .build();

        return httpClient;
    }

}

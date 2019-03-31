package com.koitoer.rx.chapter8;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.jayway.jsonpath.JsonPath;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;
import rx.functions.Func2;

/**
 * Created by mmena on 3/15/18.
 */
public class AkiraUnitConfig {

    public static void main(String[] args) {
        Observable<String> argOlbConfig = new ArgCommand("http://opas-rates-engine-production.us-east-1-vpc-d9087bbe.slb-internal.prod.aws.away.black","db6020f8-2b3f-4420-925f-a611cdcf938c").construct();
        Observable<String> jqsOlbConfig = new JqsCommand("http://akira-prod-vip.homeaway.live:82","db6020f8-2b3f-4420-925f-a611cdcf938c").construct();

        List<String> configs = new ArrayList<>();

        Observable.zip(argOlbConfig, jqsOlbConfig, new Func2<String, String, Void>() {

            @Override public Void call(String argConfig, String jqsConfig) {
                configs.add("JQS - " + jqsConfig.trim().replaceAll("\r", "").replaceAll("\n", ""));
                configs.add("ARG - " + JsonPath.read(argConfig, "$.LodgingOlbConfigCache.primaryPricingSource"));
                configs.add("OLBCONFIG - " + JsonPath.read(argConfig, "$.LodgingOlbConfigCache.lodgingOlbConfigUrl"));
                return null;
            }
        }).toBlocking().subscribe(aVoid -> System.out.println(configs));

    }



    private static class JqsCommand extends HystrixObservableCommand<String> {

        private String jqsBaseUrl;

        private String uuid;

        public JqsCommand(String jqsBaseUrl, String uuid) {
            super(HystrixCommandGroupKey.Factory.asKey("arg-group"));
            this.jqsBaseUrl = jqsBaseUrl;
            this.uuid = uuid;
        }

        @Override protected Observable<String> construct() {
            try {
                final URL url = new URL(String.format("%s/api/aggregatorDebug/UnitRateAlgorithm/%s", jqsBaseUrl, uuid));
                try (InputStream inputStream = url.openStream()) {
                    return Observable.from(Arrays.asList(IOUtils.toString(inputStream, StandardCharsets.UTF_8)));
                }
            } catch (Exception e) {
                return Observable.from(Arrays.asList(e.getMessage()));
            }
        }
    }


    private static class ArgCommand extends HystrixObservableCommand<String> {

        private String argBaseUrl;

        private String uuid;

        public ArgCommand(String argBaseUrl, String uuid) {
            super(HystrixCommandGroupKey.Factory.asKey("arg-group"));
            this.argBaseUrl = argBaseUrl;
            this.uuid = uuid;
        }

        @Override protected Observable<String> construct() {
            try {
                final URL url = new URL(String.format("%s/arg/v1/debug/getUUIDData/%s", argBaseUrl, uuid));
                try (InputStream inputStream = url.openStream()) {
                    return Observable.from(Arrays.asList(IOUtils.toString(inputStream, StandardCharsets.UTF_8)));
                }
            } catch (Exception e) {
                return Observable.from(Arrays.asList(e.getMessage()));
            }
        }
    }
}

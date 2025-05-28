package ng.com.ninepsb.nip_outward.config;

import ng.com.ninepsb.nip_outward.utils.Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.List;

import static ng.com.ninepsb.nip_outward.utils.Constants.*;

@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private boolean isTest = false;
    private int nameEnquiryValidityTime = 2880;
    private List<String> blockedChannels = new ArrayList<>();
    private List<String> cevaPrefixes = new ArrayList<>();

    private List<String> mobilePrefixes = new ArrayList<>();

    @Cacheable(key = CACHE_KEY_IS_TEST, cacheNames = CACHE_KEY_IS_TEST)
    public boolean isTest() {
        return isTest;
    }

    @Cacheable(key = Constants.CACHE_KEY_CEVA_PREFIXES, cacheNames = Constants.CACHE_KEY_CEVA_PREFIXES)
    public List<String> getCevaPrefixes() {
        return cevaPrefixes;
    }

    @Cacheable(key = CACHE_KEY_MOBILE_PREFIX, cacheNames = CACHE_KEY_MOBILE_PREFIX)
    public List<String> getMobilePrefixes() {
        return mobilePrefixes;
    }

    @Cacheable(key = CACHE_KEY_NAME_ENQ_VALIDITY_TIME, cacheNames = CACHE_KEY_NAME_ENQ_VALIDITY_TIME)
    public int getNameEnquiryValidityTime() {
        return nameEnquiryValidityTime;
    }

    @Cacheable(key = CACHE_KEY_BLOCKED_CHANNELS, cacheNames = CACHE_KEY_BLOCKED_CHANNELS)
    public List<String> getBlockedChannels() {
        return blockedChannels;
    }
}

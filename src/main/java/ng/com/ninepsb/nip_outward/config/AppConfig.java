package ng.com.ninepsb.nip_outward.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private boolean isTest = false;
    private int nameEnquiryValidityTime = 2880;
    private List<String> blockedChannels = new ArrayList<>();

    public boolean isTest() {
        return isTest;
    }

    public int getNameEnquiryValidityTime() {
        return nameEnquiryValidityTime;
    }
}

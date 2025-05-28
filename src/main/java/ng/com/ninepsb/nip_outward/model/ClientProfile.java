package ng.com.ninepsb.nip_outward.model;

import ng.com.ninepsb.nibss_nip_lib.enums.NibssChannelCode;

public class ClientProfile {
    private Long id;
    private String name;
    private NibssChannelCode channel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NibssChannelCode getChannel() {
        return channel;
    }
    public void setChannel(NibssChannelCode channel) {
        this.channel = channel;
    }

}

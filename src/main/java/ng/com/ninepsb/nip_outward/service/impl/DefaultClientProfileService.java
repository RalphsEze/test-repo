package ng.com.ninepsb.nip_outward.service.impl;

import ng.com.ninepsb.nibss_nip_lib.enums.NibssChannelCode;
import ng.com.ninepsb.nip_outward.model.ClientProfile;
import ng.com.ninepsb.nip_outward.service.ClientProfileService;
import org.springframework.stereotype.Service;

@Service
class DefaultClientProfileService implements ClientProfileService {

    @Override
    public ClientProfile getClientProfile(String clientId) {
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setChannel(NibssChannelCode.BANK_TELLER);
        return clientProfile;
    }
}

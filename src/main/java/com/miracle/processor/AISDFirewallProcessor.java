package com.miracle.processor;

import com.miracle.model.AISDFirewall;
import com.miracle.model.CBReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
@Slf4j
public class AISDFirewallProcessor implements ItemProcessor<AISDFirewall, AISDFirewall> {
    @Override
    public AISDFirewall process(AISDFirewall aisdFirewall1) throws Exception {

        AISDFirewall aisdFirewall=new AISDFirewall();
        aisdFirewall.setDomain(aisdFirewall1.getDomain());
        aisdFirewall.setReceive_Time(aisdFirewall1.getReceive_Time());
        aisdFirewall.setSerial(aisdFirewall1.getSerial());
        aisdFirewall.setType(aisdFirewall1.getType());
        aisdFirewall.setThreat_Content_Type(aisdFirewall1.getThreat_Content_Type());
        aisdFirewall.setConfig_Version(aisdFirewall1.getConfig_Version());
        aisdFirewall.setGenerate_Time(aisdFirewall1.getGenerate_Time());
        aisdFirewall.setVirtual_system(aisdFirewall1.getVirtual_system());
        aisdFirewall.setEvent_id(aisdFirewall1.getEvent_id());
        aisdFirewall.setStage(aisdFirewall1.getStage());
        aisdFirewall.setAuth_method(aisdFirewall1.getAuth_method());
        aisdFirewall.setTunnel_type(aisdFirewall1.getTunnel_type());
        aisdFirewall.setSource_user(aisdFirewall1.getSource_user());
        aisdFirewall.setSrc_region(aisdFirewall1.getSrc_region());
        aisdFirewall.setMachine_name(aisdFirewall1.getMachine_name());
        aisdFirewall.setPublic_ip(aisdFirewall1.getPublic_ip());
        aisdFirewall.setPublic_ipv6(aisdFirewall1.getPublic_ipv6());
        aisdFirewall.setPrivate_ip(aisdFirewall1.getPrivate_ip());
        aisdFirewall.setPrivate_ipv6(aisdFirewall1.getPrivate_ipv6());
        aisdFirewall.setHost_id(aisdFirewall1.getHost_id());
        aisdFirewall.setSerial_number(aisdFirewall1.getSerial_number());
        aisdFirewall.setClient_ver(aisdFirewall1.getClient_ver());
        aisdFirewall.setClient_os(aisdFirewall1.getClient_os());
        aisdFirewall.setClient_os_ver(aisdFirewall1.getClient_os_ver());
        aisdFirewall.setRepeat_count(aisdFirewall1.getRepeat_count());
        aisdFirewall.setReason(aisdFirewall1.getReason());
        aisdFirewall.setReason(aisdFirewall1.getReason());
        aisdFirewall.setError(aisdFirewall1.getError());
        aisdFirewall.setDescription(aisdFirewall1.getDescription());
        aisdFirewall.setStatus(aisdFirewall1.getStatus());
        aisdFirewall.setLocation(aisdFirewall1.getLocation());
        aisdFirewall.setLogin_duration(aisdFirewall1.getLogin_duration());
        aisdFirewall.setConnect_method(aisdFirewall1.getConnect_method());
        aisdFirewall.setError_code(aisdFirewall1.getError_code());
        aisdFirewall.setPortal(aisdFirewall1.getPortal());
        aisdFirewall.setSequence_number(aisdFirewall1.getSequence_number());
        aisdFirewall.setAction_flags(aisdFirewall1.getAction_flags());
        return aisdFirewall;
    }
}

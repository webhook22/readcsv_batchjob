package com.miracle.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AISDFirewall implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String domain;
	private String receive_Time;
	private String serial;
	private String type;
	private String threat_Content_Type;
	private String config_Version;
	private String generate_Time;
	private String virtual_system;
	private String event_id;
	private String stage;
	private String auth_method;
	private String tunnel_type;
	private String source_user;
	private String src_region;
	private String machine_name;
	private String public_ip;
	private String public_ipv6;
	private String private_ip;
	private String private_ipv6;
	private String host_id;
	private String serial_number;
	private String client_ver;
	private String client_os;
	private String client_os_ver;
	private String repeat_count;
	private String reason;
	private String error;
	private String Description;
	private String status;
	private String location;
	private String login_duration;
	private String connect_method;
	private String error_code;
	private String portal;
	private String sequence_number;
	private String action_flags;
}

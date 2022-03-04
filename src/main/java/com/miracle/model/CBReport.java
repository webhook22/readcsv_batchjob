package com.miracle.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CBReport  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String deviceID;
	private String  serialNumber;
	private String annotatedAssetID;
	private String model;
	private String macAddress;
	private String mostRecentUser;
	private String lastSync;
	private String majorChromeVersion;
	
}

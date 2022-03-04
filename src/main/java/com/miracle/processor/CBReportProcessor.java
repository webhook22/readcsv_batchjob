package com.miracle.processor;

import com.miracle.model.CBReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
@Slf4j
public class CBReportProcessor implements ItemProcessor<CBReport, CBReport>{
	@Override
	public CBReport process(final CBReport cbReport) throws Exception {

		final CBReport cbReportDto = new CBReport();
		cbReportDto.setAnnotatedAssetID(cbReport.getAnnotatedAssetID());
		cbReportDto.setDeviceID(cbReport.getDeviceID());
		cbReportDto.setLastSync(cbReport.getLastSync());
		cbReportDto.setMacAddress(cbReport.getMacAddress());
		cbReportDto.setMajorChromeVersion(cbReport.getMajorChromeVersion());
		cbReportDto.setModel(cbReport.getModel());
		cbReportDto.setMostRecentUser(cbReport.getMostRecentUser());
		cbReportDto.setSerialNumber(cbReport.getSerialNumber());
		return cbReportDto;
	}

}

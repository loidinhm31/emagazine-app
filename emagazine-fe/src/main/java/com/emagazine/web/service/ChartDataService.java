package com.emagazine.web.service;

import java.util.List;
import java.util.Map;

import com.emagazine.web.model.DataPieChart;

public interface ChartDataService {
	List<DataPieChart> getPieChartArtical();
	
	List<DataPieChart> getPieChartArticalHalfYear();
	
	Map<String, Integer> getMapDataColumnChart();
}

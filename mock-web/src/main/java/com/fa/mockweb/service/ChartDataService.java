package com.fa.mockweb.service;

import java.util.List;
import java.util.Map;

import com.fa.mockweb.model.DataPieChart;

public interface ChartDataService {
	List<DataPieChart> getPieChartArtical();
	
	List<DataPieChart> getPieChartArticalHalfYear();
	
	Map<String, Integer> getMapDataColumnChart();
}

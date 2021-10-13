package com.fa.mockweb.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fa.mockweb.config.RestAPI;
import com.fa.mockweb.model.DataPieChart;
import com.fa.mockweb.service.ChartDataService;

@Service
public class ChartDataServiceImpl implements ChartDataService {

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public List<DataPieChart> getPieChartArtical() {
		String url = RestAPI.URL + "/posts/piechart/data/month";

		try {
			// Call API
			ResponseEntity<DataPieChart[]> result = restTemplate.getForEntity(url, DataPieChart[].class);
			List<DataPieChart> listData = Arrays.asList(result.getBody());
			return listData;
		} catch (HttpClientErrorException e) {
			return null;
		}
	}

	@Override
	public List<DataPieChart> getPieChartArticalHalfYear() {
		String url = RestAPI.URL + "/posts/piechart/data/half-year";

		try {
			// Call API
			ResponseEntity<DataPieChart[]> result = restTemplate.getForEntity(url, DataPieChart[].class);
			List<DataPieChart> listData = Arrays.asList(result.getBody());
			return listData;
		} catch (HttpClientErrorException e) {
			return null;
		}
	}

	@Override
	public Map<String, Integer> getMapDataColumnChart() {
		String url = RestAPI.URL + "/posts/column-chart/data/half-year";

		try {
			// Call API
			ResponseEntity<?> result = restTemplate.getForEntity(url, LinkedHashMap.class);
			Map<String, Integer> map = (Map<String, Integer>) result.getBody();
			return map;
		} catch (HttpClientErrorException e) {
			return null;
		}
	}

}

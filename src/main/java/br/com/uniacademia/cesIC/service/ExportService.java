package br.com.uniacademia.cesIC.service;

import java.util.List;

public interface ExportService {

	
	<T> void exportarCSV(List<T> params, String path);
}

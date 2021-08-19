package br.com.uniacademia.cesIC.service.implementation;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import br.com.uniacademia.cesIC.service.ExportService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExportServiceImplementation implements ExportService {
	@Override

	public <T> void exportarCSV(List<T> params, String path) {
		try {
			Writer writer = Files.newBufferedWriter(Paths.get(path));
			StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer).build();
			beanToCsv.write(params);
			writer.flush();
			writer.close();
		} catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			log.error("Erro ao exportar", e);
		}

	}

}

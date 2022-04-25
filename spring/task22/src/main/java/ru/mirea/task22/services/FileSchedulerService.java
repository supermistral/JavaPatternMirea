package ru.mirea.task22.services;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.task22.repositories.MarketRepository;
import ru.mirea.task22.repositories.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@ManagedResource
public class FileSchedulerService {

    @Value("${logging.database.dump.path}")
    private String dumpPath;

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private ProductRepository productRepository;

    @Scheduled(cron = "0 0/30 * * * ?")
    @Transactional
    @ManagedOperation
    public void updateDumpData() throws IOException {
        log.info("Starting dump models to json...");

        File dumpDir = new File(dumpPath);

        if (!dumpDir.exists()) {
            dumpDir.mkdirs();
        } else {
            FileUtils.cleanDirectory(dumpDir);
        }

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("  ", "\n"));
        mapper.writer(printer);

        writeModelToJsonFile(mapper, productRepository, "products.json");
        writeModelToJsonFile(mapper, marketRepository, "markets.json");
    }

    public void writeModelToJsonFile(ObjectMapper objectMapper, JpaRepository repository, String filename) {
        File file = new File(dumpPath + "/" + filename);
        List objects = repository.findAll();

        try {
            objectMapper.writeValue(file, objects);

            log.info(objects.size() + " objects have been written to the file " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

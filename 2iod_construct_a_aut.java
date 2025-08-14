import java.util.ArrayList;
import java.util.List;

public class AutomatedDataPipelineController {
    private List<DataPipeline> pipelines;

    public AutomatedDataPipelineController() {
        this.pipelines = new ArrayList<>();
    }

    public void addPipeline(DataPipeline pipeline) {
        this.pipelines.add(pipeline);
    }

    public void startPipelines() {
        for (DataPipeline pipeline : pipelines) {
            pipeline.start();
        }
    }

    public static class DataPipeline {
        private String name;
        private DataIngestion ingestion;
        private DataTransformation transformation;
        private DataStorage storage;

        public DataPipeline(String name, DataIngestion ingestion, DataTransformation transformation, DataStorage storage) {
            this.name = name;
            this.ingestion = ingestion;
            this.transformation = transformation;
            this.storage = storage;
        }

        public void start() {
            ingestion.ingest();
            transformation.transform();
            storage.store();
        }
    }

    public static interface DataIngestion {
        void ingest();
    }

    public static interface DataTransformation {
        void transform();
    }

    public static interface DataStorage {
        void store();
    }

    public static class CSVIngestion implements DataIngestion {
        @Override
        public void ingest() {
            System.out.println("Ingesting data from CSV file...");
        }
    }

    public static class JSONTransformation implements DataTransformation {
        @Override
        public void transform() {
            System.out.println("Transforming data to JSON...");
        }
    }

    public static class DatabaseStorage implements DataStorage {
        @Override
        public void store() {
            System.out.println("Storing data in database...");
        }
    }
}
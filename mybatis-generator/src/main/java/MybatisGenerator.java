import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class MybatisGenerator {

	public static void main(String[] args) {


		deleteAllLastGeneratedFiles();
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File(MybatisGenerator.class.getClassLoader().getResource("generatorConfig.xml").getFile());
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = null;
		try {
			config = cp.parseConfiguration(configFile);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);

			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);

		} catch (IOException | XMLParserException | InvalidConfigurationException | InterruptedException | SQLException e) {
			e.printStackTrace();
		}
	}

	private static void deleteAllLastGeneratedFiles() {

		deleteEverythingInDir(System.getProperty("user.dir") + "/mybatis-generator/src/main/java/org/shop");
		deleteEverythingInDir(System.getProperty("user.dir") + "/mybatis-generator/src/main/resources/org/shop");
	}
	private static void deleteEverythingInDir(String dir){
		File file = new File(dir);
		File[] files = file.listFiles();
		assert files != null;
		Stream<File> fileStream = Arrays.stream(files).flatMap(file2 -> Arrays.stream(Objects.requireNonNull(file2.listFiles())));
		fileStream.forEach((f) -> {
			if (f.isDirectory()) {
				for (File listFile : Objects.requireNonNull(f.listFiles())) {
					listFile.delete();
				}
			} else {
				f.delete();
			}
		});
	}
}

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class MybatisGenerator {

	public static void main(String[] args) {


		deleteAllLastGeneratedFiles();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		System.out.println(simpleDateFormat.format(new Date()));
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

		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLParserException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	private static void deleteAllLastGeneratedFiles() {
		File file = new File(System.getProperty("user.dir") + "/mybatis-generator/src/main/java/org/shop");
		File[] files = file.listFiles();
		Stream<File> fileStream = Arrays.stream(files).flatMap(file2 -> Arrays.stream(file2.listFiles()));
		fileStream.forEach((f) -> {
			if (f.isDirectory()) {
				for (File listFile : f.listFiles()) {
					listFile.delete();
				}
			} else {
				f.delete();
			}
		});
	}
}

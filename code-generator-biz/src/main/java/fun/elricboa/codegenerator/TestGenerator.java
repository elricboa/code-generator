package fun.elricboa.codegenerator;

import java.io.*;

/**
 * @author shentongzhou on 2019-09-03
 */
public class TestGenerator {
    public static void main(String[] args) throws IOException {
//************设置需要生成代码的工程模块**************
        String moduleName = "code-generator-biz";
        String sqlMapPath = "/src/main/resources/config/sqlmap/entity/";
        String daoConfigPath = "/src/main/resources/config/spring/local/appcontext-dao.xml";

//************处理目录**********
        String root = System.getProperty("user.dir");
        StringBuilder javaPathBuilder = new StringBuilder();
        javaPathBuilder.append(root).append("/").append(moduleName);
        String moduleDir = javaPathBuilder.toString();
        File f = new File(moduleDir);
        if (!(f.exists() && f.isDirectory())) {
            System.out.println(root + "下找不到模块" + moduleName);
            return;
        }
// 基本包名
        String basePackage = "fun.elricboa.codegenerator";
        String javaPath = javaPathBuilder + "/src/%s/java/" + basePackage.replaceAll("\\.", "/") + "/%s";
        String testPath = javaPathBuilder + "/src/%s/java/" + basePackage.replaceAll("\\.", "/")+"/" + "/%s";
        String sqlPath = javaPathBuilder + sqlMapPath;

        String DaoGenTest = "package fun.elricboa.codegenerator.dao;\n" +
                "\n" +
                "import fun.elricboa.codegenerator.entity.DaoGen;\n" +
                "import fun.elricboa.codegenerator.dal.dao.test.AbstractDAOTest;\n" +
                "import org.junit.Before;\n" +
                "import org.junit.Test;\n" +
                "import java.util.Date;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "\n" +
                "import java.util.List;\n" +
                "import fun.elricboa.codegenerator.entity.DaoGen;\n" +
                "\n" +
                "import static org.assertj.core.api.Assertions.assertThat;\n" +
                "\n" +
                "/**\n" +
                "*\n" +
                "* Create by elricboa\n" +
                "*\n" +
                "*/\n" +
                "public class CodeGeneratorTest extends AbstractDAOTest {\n" +
                "\n" +
                "	@Autowired\n" +
                "	private CodeGenerator codeGenerator;\n" +
                "\n" +
                "	private DaoGen daoGen;\n" +
                "\n" +
                "	@Before\n" +
                "	public void setup() {\n" +
                "        daoGen = new DaoGen();\n" +
                "        daoGen.setId(10);\n" +
                "        daoGen.setName(\"Apollo DaoGen guagua\");\n" +
                "        daoGen.setEnName(\"Apollo DaoGen guagua\");\n" +
                "	}\n" +
                "\n" +
                "    @org.junit.Ignore\n" +
                "    @Test\n" +
                "    public void testInsert() {\n" +
                "        codeGenerator.insert(daoGen);\n" +
                "    }\n" +
                "\n" +
                "    @Test\n" +
                "    public void testLoadById() {\n" +
                "        codeGenerator.loadById(10);\n" +
                "    }\n" +
                "\n" +
                "    @Test\n" +
                "    public void testFindByIds() {\n" +
                "        List<Integer> ids = new java.util.ArrayList<Integer>();\n" +
                "        ids.add(10);\n" +
                "        List<DaoGen> daoGens = codeGenerator.findByIds(ids);\n" +
                "        assertThat(daoGens).isNotNull();\n" +
                "    }\n" +
                "\n" +
                "}\n" +
                "\n";
        writeFile(formatPath(javaPath, "test", "dao"), "CodeGeneratorTest.java", DaoGenTest);

        String DaoGenSpringConfig = "<bean name=\"codeGenerator\" parent=\"parentDao\">\n" +
                "    <property name=\"proxyInterfaces\"\n" +
                "              value=\"fun.elricboa.codegenerator.dao.CodeGenerator\" />\n" +
                "    <property name=\"target\">\n" +
                "        <bean parent=\"daoRealizeTarget\">\n" +
                "            <constructor-arg value=\"DaoGen\" />\n" +
                "        </bean>\n" +
                "    </property>\n" +
                "</bean>\n" +
                "\n";
        writeDaoConfigFile(javaPathBuilder + daoConfigPath, DaoGenSpringConfig);
    }

    private static String formatPath(String basePath, String pathType, String genType) {
        return String.format(basePath, pathType, genType);
    }

    private static void writeFile(String basePath, String genName, String content) throws IOException {
        forceMkdirs(basePath);
        String srcPath = basePath + genName;
        File targetFile = new File(srcPath);
        if (targetFile.exists() && targetFile.isFile()) {
            System.out.println(srcPath + "已存在, 覆盖? y/n");
            char override = (char) System.in.read();
            if (override == 'n' || override == 'N') {
                System.out.println("跳过");
                return;
            }
        }
        write(targetFile, content);
    }

    private static void write(File targetFile, String content) throws IOException {
        FileWriter fileWriter = new FileWriter(targetFile);
        fileWriter.write(content);
        fileWriter.close();
        System.out.println("创建成功: " + targetFile.getAbsolutePath());
    }

    private static void forceMkdirs(String path) {
        File dir = new File(path);
        if (!(dir.exists() && dir.isDirectory()))
            dir.mkdirs();
    }

    private static void writeDaoConfigFile(String filePath, String content) throws IOException {
        forceMkdirs(filePath.substring(0, filePath.lastIndexOf("/")));

        String head = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.1.xsd\">\n\n";
        String tail = "</beans>";

        File targetFile = new File(filePath);
        if (!(targetFile.exists() && targetFile.isFile())) {
            write(targetFile, head + content + tail);
        } else {
            StringBuilder read = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(targetFile), "UTF-8");//考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(reader);
            String lineTxt;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                if (lineTxt.contains(tail))
                    read.append(content);
                read.append(lineTxt).append("\n");
            }
            reader.close();
            write(targetFile, read.toString());
        }
    }
}

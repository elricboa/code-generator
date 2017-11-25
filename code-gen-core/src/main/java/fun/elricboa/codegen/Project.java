package fun.elricboa.codegen;

import lombok.Data;

/**
 * @author elricboa on 2017/11/25.
 */
@Data
public class Project {

    private String outputDir;
    private String sqlMapPath;
    private String basePackage;
    private String daoPackage;
    private String entityPackage;
    private String testPackage;
    private String springDir;
    private String springFIleName;
    private String daoRealizeTarget;
    private String sqlConfigFileName;
}

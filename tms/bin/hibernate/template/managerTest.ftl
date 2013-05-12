${pojo.getPackageDeclaration()}
<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign entityName = declarationName?uncap_first>
public class ${declarationName}ManagerTest extends ${pojo.importType("org.springside.modules.test.spring.SpringTxTestCase")} {

	@${pojo.importType("org.springframework.beans.factory.annotation.Autowired")}
	prvate ${declarationName}Manager ${entityName}Manager;
}
</#assign>

${pojo.generateImports()}
import ${pojo.getQualifiedDeclarationName()};

${classbody}

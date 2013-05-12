<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign entityName = declarationName?uncap_first>
public class ${declarationName}DaoTest extends ${pojo.importType("org.springside.modules.test.spring.SpringTxTestCase")}  {
	@${pojo.importType("org.springframework.beans.factory.annotation.Autowired")}
	private ${declarationName}Dao entityDao;

	@${pojo.importType("org.junit.Test")}
	public void crudEntity() {
		//new entity and save it. 
		${declarationName} entity = new ${declarationName}(); 
		entityDao.save(entity);
		flush();

		//find entity.	
		${declarationName} entityFromDB = entityDao.findUniqueBy("id", entity.getId());
		assertReflectionEquals(entity, entityFromDB);
			
		//delete entity.
		entityDao.delete(entity.getId());
		flush();
		entity = entityDao.findUniqueBy("id", entity.getId());
		assertNull(entity);
	}
}
</#assign>

${pojo.generateImports()}
import ${pojo.getQualifiedDeclarationName()};

${classbody}

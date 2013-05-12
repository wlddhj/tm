<#--${pojo.getPackageDeclaration()}-->
package com.powerlong.org.pd.dao.res;
<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign entityName = declarationName?uncap_first>
@${pojo.importType("org.springframework.stereotype.Repository")}
public class ${declarationName}Dao extends ${pojo.importType("org.springside.modules.orm.hibernate.HibernateDao")}<${declarationName}, ${pojo.getJavaTypeName(clazz.identifierProperty, true)}> {
}
</#assign>

${pojo.generateImports()}
import ${pojo.getQualifiedDeclarationName()};

${classbody}

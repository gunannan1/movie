添加到本地maven仓库
mvn install:install-file -DgroupId=org.mengyun -DartifactId=tcc-transaction-api -Dversion=1.2.4.23 -Dpackaging=jar -Dfile=/Users/gunannan/Downloads/tcc/tcc-transaction-api-1.2.4.23.jar
mvn install:install-file -DgroupId=org.mengyun -DartifactId=tcc-transaction-core -Dversion=1.2.4.23 -Dpackaging=jar -Dfile=/Users/gunannan/Downloads/tcc/tcc-transaction-core-1.2.4.23.jar
mvn install:install-file -DgroupId=org.mengyun -DartifactId=tcc-transaction-dubbo -Dversion=1.2.4.23 -Dpackaging=jar -Dfile=/Users/gunannan/Downloads/tcc/tcc-transaction-dubbo-1.2.4.23.jar
mvn install:install-file -DgroupId=org.mengyun -DartifactId=tcc-transaction-spring -Dversion=1.2.4.23 -Dpackaging=jar -Dfile=/Users/gunannan/Downloads/tcc/tcc-transaction-spring-1.2.4.23.jar
Setting up the environment
1. If maven is not yet installed please add this to System Path
	<path to project>/Cart/apache-maven-3.5.0-bin\apache-maven-3.5.0\bin
2. In Environment Variables set
	JAVA_HOME = <path to jdk> e.g. C:\Program Files\Java\jdk1.8.0_131
3. In Cart/exam/pom.xml please change configuration source and target of maven based on jdk version. If user has jdk1.8.0_131, please set source and configuration to 1.8.
	    ...
	    <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration> 
	    ...

Running test
1. In Cart/exam execute "mvn test-compile"
2. Execute "mvn test" to run tests